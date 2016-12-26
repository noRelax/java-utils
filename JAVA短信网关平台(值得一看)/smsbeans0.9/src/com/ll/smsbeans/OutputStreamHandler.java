package com.ll.smsbeans;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ll.smsbeans.log.LogCommon;

/**
 *	����һ������̡߳�Ϊ���͵����ݽ���һ��֧���̵߳Ķ��У�ʹ���ݰ��ķ��Ϳ���֧�ֶ��̡߳�
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 */

public final class OutputStreamHandler extends Thread
{
	/** ����� */
	private OutputStream out;

	/** �����ӣ������ڰ����ͳɹ��󷢳��ɹ��¼� */
	private ConnectionBean.OutputStreamInterface osi;

	/** ���Ͱ����� */
	private final LockingQueue output = new LockingQueue();

	/** �Ƿ�������ֹ�̵߳����� */
	private boolean keepRunning = true;

	static Logger _log;
	/**
	 *����һ���µ� <code>OutputStreamHandler</code> ʵ����
	 *
	 * @param out  <code>OutputStream</code> �����
	 */
	public OutputStreamHandler(ConnectionBean.OutputStreamInterface osi)
	{
		this.osi = osi;
	}

	/**
	*�����������
	*
	* @param out  <code>OutputStream</code> �����
	*/
	public void setOutputStream(OutputStream out)
	{
		this.out = out;
	}

	/**
	 * ����Ҫ���Ͱ����뷢�Ͷ��С�
	 *
	 * @param p Packet ���Ͱ���
	 */
	public final void send(Packet p)
	{
		if (keepRunning)
			output.put(p);
		else
			osi.sendFailed(p);
	}

	/**
	 * <code>shutdown</code> ֪ͨ�߳̽������С�
	 */
	public void shutdown()
	{
		_log.fine("OutputStream: shutdown");
		keepRunning = false;
		output.putLast(null);

		_log.fine("OutputStream: closing queue");
	}

	/**
	 * �߳���ֹ��������
	 * @param ex <code>Exception</code> �����ֹ���쳣��
	 * @param p <code>Packet</code> ��ǰ���͵İ���
	 */

	public void handleThreadDeath(Exception ex, Packet p)
	{
		_log.warning("OutputStream: thread death");
		if (p != null)
			osi.sendFailed(p);
		Object pkt = output.getLast();
		while (pkt != null)
		{
			osi.sendFailed((Packet) pkt);
			pkt = output.getLast();
		}
		osi.unexpectedThreadDeath(ex);
	}

	/**
	 * �߳���ֹ��������
	 * @param ex <code>Exception</code> �����ֹ���쳣��
	 */
	public void handleThreadDeath(Exception ex)
	{
		handleThreadDeath(ex, null);
	}

	/**
	 * �߳�ѭ�����ͷ��Ͷ����е����ݰ���
	 */
	public final void run()
	{
		if (out == null)
			throw new RuntimeException("starting output thread without any IO set up to use");
		Packet p = null;
		while (keepRunning)
		{
			try
			{
				p = (Packet) output.get();

			} catch (final InterruptedException e)
			{
				_log.severe("OutputStream: interrupted");
			}

			if (p != null)
			{
				try
				{
					byte[] data = p.getContent();
					int len = data.length + 4;
					byte[] mybytes = new byte[4];

					mybytes[3] = (byte) (0xff & len);
					mybytes[2] = (byte) ((0xff00 & len) >> 8);
					mybytes[1] = (byte) ((0xff0000 & len) >> 16);
					mybytes[0] = (byte) ((0xff000000 & len) >> 24);

					out.write(mybytes);
					out.write(data);
					out.flush();
					if (_log.isLoggable(LogCommon.DEBUG_LEVEL))
						_log.log(
							LogCommon.DEBUG_LEVEL,
							LogCommon.getLogBin("Sent Data", mybytes, data));
				} catch (IOException e)
				{

					//������
					handleThreadDeath(e, p);
					return;
				}
				//���ͳɹ���Ϣ
				osi.sent(p);
			}
		}

		_log.warning("OutputStream: stopped");
	}

	/**
	*�̰߳�ȫ�ķ��Ͷ��У��������û�д�Сû�����ơ� 
	*
	* @author  listlike <a href="mailto:listlike@hotmail.com">
	*                      <i>&lt;listlike@hotmail.com&gt;</i></a>
	*
	*/
	private final class LockingQueue
	{
		private LinkedList m_queue = new LinkedList();
		private boolean m_closed = false;
		private boolean m_reject = false;
		private int m_waiting = 0;

		/**
		* �������
		* @param item <code>Object</code> ������еĶ���
		*/
		public synchronized final void put(Object item)
		{
			if (m_closed || m_reject)
				return;
			m_queue.addLast(item);
			notify(); //#notify
		}

		/**
		* �������һ�����Ͷ��󣬼��������Զ��رգ������ټ������
		* 
		* @param item <code>Object</code> ������еĶ���
		*/

		public synchronized final void putLast(Object item)
		{
			put(item);
			m_reject = true;
		}

		/**
		 * ��ȡ����
		 *  
		 * @param timeout ��ʱ���á�
		 * @return  <code>Object</code> ��ȡ�Ķ���
		 * @throws InterruptedException 
		 */
		public synchronized final Object get(long timeout)
			throws InterruptedException
		{
			long _expire = System.currentTimeMillis() + timeout;
			if (m_closed)
				return null;
			try
			{
				if (m_queue.size() <= 0)
				{
					++m_waiting;
					while (m_queue.size() <= 0)
					{
						wait(timeout);

						if (timeout > 0
							&& System.currentTimeMillis() > _expire)
						{
							--m_waiting;
							throw new InterruptedException("LockingQueue : timeout to dequeue");
						}

						if (m_closed)
						{
							--m_waiting;
							return null;
						}
					}
					--m_waiting;
				}

				Object head = m_queue.removeFirst();

				if (m_queue.size() == 0 && m_reject)
					close(); // �Ƴ����һ�����󣬹رն��С�

				return head;
			} catch (NoSuchElementException e) // �ᷢ����
			{
				throw new Error("LockingQueue: internal error");
			}
		}
		/**
		 * ���������
		 * @return ������
		 */
		public final Object getLast()
		{
			if (m_queue.size() <= 0)
				return null;
			else
				return m_queue.removeFirst();
		}

		/**
		 * ��ȡ����
		 *  
		 * @return  <code>Object</code> ��ȡ�Ķ���
		 * @throws InterruptedException 
		 */
		public synchronized final Object get() throws InterruptedException
		{
			return get(0);
		}
		/**
		 * �����Ƿ�Ϊ��
		 * @return �����Ƿ�Ϊ��
		 */
		public final boolean isEmpty()
		{
			return m_queue.size() <= 0;
		}

		/**
		* �ȴ����߳�����
		* @return ���еĴ�С
		*/
		public final int size()
		{
			return m_waiting;
		}

		/**
		 * �رն���
		 */
		public synchronized void close()
		{
			m_closed = true;
			m_queue = null;
			notifyAll();
		}
	}
	/* ���� Javadoc��
	 * @see java.lang.Thread#start()
	 */
	public synchronized void start()
	{
		// TODO �Զ����ɷ������
		keepRunning = true;
		super.start();
	}

	static {
		_log = Logger.getLogger("com.ll.smsbeans.OutputStreamHandler");
	}

}