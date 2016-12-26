package com.ll.smsbeans;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ll.smsbeans.cmpp3.CmppPacketParser;
import com.ll.smsbeans.log.LogCommon;

/**
 * ����һ�������̣߳����ҽ�������߳��н����յ������ݰ�ͨ�� <code>CmppPacketParser</code>
 * �����
 * 
 * ��ʵ����һ��������߳��в������ConnetionBean�����������Ҳ�ǿ��Եģ��ô������ܹ��������Ĳ���¼���
 * ��������ConnetionBean���������������ˣ��Ǻǡ�
 */
public final class InputStreamHandler extends Thread
{
	/** ������ */
	private InputStream in = null;

	/** ������ ��ConnectionBean object��, ���ڴ�����Ϣ�ص� */
	private ConnectionBean.InputStreamInterface isi = null;
	private CmppPacketParser cpp;

	private boolean parsingDone = false;

	static Logger _log;

	/**
	 * ���� <code>InputStreamHandler</code> ʵ����
	 *
	 * @param in  <code>InputStream</code> ���������ӵ�������
	 */
	public InputStreamHandler(ConnectionBean.InputStreamInterface isi)
	{
		this.isi = isi;
		cpp = new CmppPacketParser();

	}

	/**
	* ���������ӵ���������
	*
	* @param in  <code>InputStream</code> ���������ӵ�������
	*/
	public void setInputStream(InputStream in)
	{
		this.in = in;
	}

	/**
	 * �̵߳���Ҫ���֣��������ݣ��������
	 */
	public final void run()
	{

		if (in == null)
			throw new RuntimeException(
				"attempting to start InputStreamHandler without input to "
					+ "monitor");

		while (!parsingDone)
		{
			try
			{

				byte[] mybytes = new byte[4];

				in.read(mybytes);
				int packetlen =
					(0xff & mybytes[0])
						<< 24 | (0xff & mybytes[1])
						<< 16 | (0xff & mybytes[2])
						<< 8 | 0xff & mybytes[3];

				if (packetlen <= 0)
					throw new IOException("connect error!");

				byte[] databytes = new byte[packetlen - 4];

				int readLen = in.read(databytes);
				if (readLen != packetlen - 4)
					throw new IOException("connect error!");

				if(_log.isLoggable(LogCommon.DEBUG_LEVEL))
					_log.log(LogCommon.DEBUG_LEVEL,LogCommon.getLogBin("Received Data",mybytes, databytes));
				Packet p = cpp.parser(databytes);
				if (p != null)
					received(p);

			} catch (IOException e)
			{
				if (parsingDone == true)
					return;

				isi.unexpectedThreadDeath(e);
			} catch (Exception e)
			{
				_log.severe(e.getMessage());
				if (parsingDone == true)
					return;
				isi.unexpectedThreadDeath(e);
			}

		}

		_log.warning("InputStream: parsing done");
		parsingDone = true;
	}

	/**
	 * <code>received</code> ��InputStreamHandler���յ����ݰ�����������ص�ConnetionBean����������㲥�����¼���
	 *
	 * @param p a <code>Packet</code> which was just received and
	 * recognised.
	 */
	public void received(Packet p)
	{

		isi.received(p);
	}

	/**
	 * ��ֹ���С�
	 *
	 */
	public void shutdown()
	{
		_log.fine("InputStream: shutdown");
		parsingDone = true;
		this.interrupt();
		_log.fine("InputStream: interrupted");
	}

	static {
		_log = Logger.getLogger("com.ll.smsbeans.InputStreamHandler");
	}

}
