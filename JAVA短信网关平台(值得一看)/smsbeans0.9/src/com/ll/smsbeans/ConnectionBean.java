package com.ll.smsbeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;

import com.ll.smsbeans.cmpp3.CmppActiveTestBuilder;
import com.ll.smsbeans.cmpp3.CmppActiveTestRespBuilder;
import com.ll.smsbeans.cmpp3.CmppConnectBuilder;
import com.ll.smsbeans.cmpp3.CmppConnectResp;
import com.ll.smsbeans.cmpp3.CmppPacket;
import com.ll.smsbeans.cmpp3.CmppPacketCommon;
import com.ll.smsbeans.cmpp3.CmppTerminateBuilder;
import com.ll.smsbeans.cmpp3.CmppTerminateRespBuilder;
import com.ll.smsbeans.log.LogCommon;

/**
 * ConnectionBean ��smsbeans����Ҫ��bean.
 * <p>
 * ConnectionBean ��Ҫ�Ǻͷ�����������֤���ӣ�����������bean����ͨ�����ͷ�����ͨ�ŵġ�
 * <p>
 * ConnectionBean�ṩһЩ����׼�����ڷ�����ͨ�ŵĽӿڣ�����send������ͬʱ���ڲ��Զ�����һЩ����
 * ���˵���Ϣ�Ļط�����ActiveTest�ȣ����ң������й����д������ݰ��ķ���ʧ�ܣ�������·���ᡣ
 * <p>
 * ����������beansͨ��ConnectionBean�������ݣ���ͨ��������ConnectionBean������received��Ϣ���չ�������
 
 * <p>
 * @see MessengerBean
 * @see IQBean
 * @see RosterBean
 * 
 
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 
 * @version $Revision: 1.0 $
 */
public class ConnectionBean implements Serializable, PacketListenerRegistrar
{
	/**
	*
	* TimerΪ��ʱ����
	* ΪConnectBean�ṩ��ʱ����ͨ����ʱ���� ��Ҫ���ڻظ�����ʱ��������Լ�������·���԰��Ķ�ʱ���͡� 
	* ��������ʱ����
	* 1 �붨ʱ��	
	* 	���ڻظ�����ʱ�������ÿ�뼤��һ��ConnectionBean.onTimer.
	* 2 ������·���԰���ʱ��
	* 	���ڱ�����·���԰��ļ�ʱ�����ݳ���ACTIVETIME��ֵ����ConnectBeantion.onActiveConnect��
	* 
	* @see com.ll.smsbeans.ConnectionBean#onTimer()
	* @see ConnectionBean#onActiveConnect()
	* @see ConnectionBean#ACTIVETIME
	*<p>
	* @author  listlike <a href="mailto:listlike@hotmail.com">
	*                      <i>&lt;listlike@hotmail.com&gt;</i></a>
	*
	 */
	public class Timer extends Thread
	{
		// ������·���԰��ļ�ʱ
		private long activeSec = 0;
		//��ʱ���Ƿ���Ч
		private boolean enableTimer = true;
		//������·���ԵĶ�ʱ���Ƿ���Ч
		private boolean enableActiveTimer = true;

		private boolean timerRun = true;

		/**
		 * ������·���ԵĶ�ʱ����λ
		 */
		public void resetActiveTime()
		{
			activeSec = System.currentTimeMillis();
		}

		/**
		* @see java.lang.Runnable#run()
		*/
		public void run()
		{

			while (timerRun)
			{
				try
				{
					sleep(1000);
					if (enableTimer)
						ConnectionBean.this.onTimer();

					if (System.currentTimeMillis() - activeSec
						>= ACTIVETIME * 1000)
					{
						resetActiveTime();
						if (enableActiveTimer)
							ConnectionBean.this.onActiveConnect();

					}
				} catch (InterruptedException e)
				{
					return;

				} catch (Exception e)
				{
					return;
				}
			}
		}

		/**
		 * ��ʱ��״̬
		 * @return ��ʱ��״̬
		 */
		public boolean isEnableActiveTimer()
		{
			return enableActiveTimer;
		}

		/**
		 * ������·���ԵĶ�ʱ��״̬
		 * @return ������·���ԵĶ�ʱ��״̬
		 */
		public boolean isEnableTimer()
		{
			return enableTimer;
		}

		/**
		 * ���ñ�����·���ԵĶ�ʱ��״̬
		 * @param enable ���ñ�����·���ԵĶ�ʱ��״̬
		 */
		public void setEnableActiveTimer(boolean enable)
		{
			enableActiveTimer = enable;
		}

		/**
		 * ���ö�ʱ��״̬
		 * @param enable ���ö�ʱ��״̬
		 */
		public void setEnableTimer(boolean enable)
		{
			enableTimer = enable;
		}

		/* ���� Javadoc��
		 * @see java.lang.Thread#interrupt()
		 */
		public void interrupt()
		{

			timerRun = false;
			super.interrupt();
		}

		/* ���� Javadoc��
		 * @see java.lang.Thread#start()
		 */
		public synchronized void start()
		{
			reset();
			super.start();
		}

		private void reset()
		{

			enableTimer = true;
			enableActiveTimer = true;
			timerRun = true;
			resetActiveTime();
		}

	}
	/**
	*PacketStreamMonitor ͨ����Ӧ���ݱ����ͺͽ����¼���ά��ConnectionBean�����ӡ�
	*  
	*
	* @author  listlike <a href="mailto:listlike@hotmail.com">
	*                      <i>&lt;listlike@hotmail.com&gt;</i></a>
	*
	 */
	public class PacketStreamMonitor implements PacketListener
	{

		/**
		 * ��Ҫ������
		 * 1�� �����յ��Ļظ����ӷ��Ͷ����Ƴ���
		 * 2�� �ж��Ƿ��½�ɹ���
		 * 3�� �Զ������жϰ����жϻظ�����
		 * 4�� ������·���԰���  
		 * @see com.ll.smsbeans.PacketListener#receivedPacket(com.ll.smsbeans.PacketEvent)
		 */
		public void receivedPacket(PacketEvent pe)
		{
			_log.info(pe.getPacket().toString());

			if (((CmppPacket) pe.getPacket()).isResponse())
			{

				SendPacketState sps = sendPacketList.get(pe.getPacket());
				if (sps != null)
				{
					sps.setState(SendPacketState.STATE_RESPONSED);
					sps.setRespPacket(pe.getPacket());
				}
				//sendPacketList.remove( pe.getPacket());

			}

			if (status.getValue() == ConnectionEvent.EState.CONNECTING)
			{

				Packet p = pe.getPacket();
				if (p != null)
				{

					if (p instanceof CmppPacket)
					{
						CmppPacket cp = (CmppPacket) p;

						if (cp.getCommandId()
							== CmppPacketCommon.ID_CMPP_CONNECT_RESP)
							//  0x80000001
						{

							sendPacketList.remove(cp);
							CmppConnectResp ccr = (CmppConnectResp) cp;
							if (ccr.getStatus() == 0)
							{

								setConnectionState(
									ConnectionEvent.STATE_CONNECTED,
									ConnectionEvent.REASON_CLIENT_INITIATED);

								mTimer = new Timer();
								mTimer.start();

							} else
							{
								disconnected(
									ConnectionEvent.REASON_SERVER_INITIATED);
							}

						}
					}

				}

				return;

			}

			if (status.getValue() == ConnectionEvent.EState.CONNECTED)
			{
				Packet p = pe.getPacket();
				if (p != null)
				{
					if (p instanceof CmppPacket)
					{
						CmppPacket cp = (CmppPacket) p;
						switch (cp.getCommandId())
						{
							case CmppPacketCommon.ID_CMPP_TERMINATE_RESP :
								//0x80000002
								sendPacketList.remove(pe.getPacket());
								disconnected(
									ConnectionEvent.REASON_CLIENT_INITIATED);
								break;

							case CmppPacketCommon.ID_CMPP_TERMINATE :
								//0x00000002

								CmppTerminateRespBuilder ctrb =
									new CmppTerminateRespBuilder();
								ctrb.setSequenceId(cp.getSequenceId());
								send(ctrb.builder());

								break;
							case CmppPacketCommon.ID_CMPP_ACTIVE_TEST :
								//0x00000008

								CmppActiveTestRespBuilder catrb =
									new CmppActiveTestRespBuilder();
								catrb.setSequenceId(cp.getSequenceId());
								send(catrb.builder());
								mTimer.resetActiveTime();
								break;
							case CmppPacketCommon.ID_CMPP_ACTIVE_TEST_RESP :
								//0x80000008
								sendPacketList.remove(cp);
								break;
							default :
								break;
						}

					}

				}

			}

			_log.log(
				LogCommon.DEBUG_LEVEL,
				"sendPacketList=" + sendPacketList.size());
		}

		/**
		 * ��Ҫ����������������˷��������жϰ���
		 *  
		 * @see com.ll.smsbeans.PacketListener#sentPacket(com.ll.smsbeans.PacketEvent)
		 */
		public void sentPacket(PacketEvent pe)
		{

			_log.info(pe.getPacket().toString());

			if (status.getValue() == ConnectionEvent.EState.CONNECTED)
			{
				Packet p = pe.getPacket();
				if (p != null)
				{
					if (p instanceof CmppPacket)
					{
						CmppPacket cp = (CmppPacket) p;

						if (cp.getCommandId()
							== CmppPacketCommon.ID_CMPP_TERMINATE_RESP)
							//0x80000002
							disconnected(
								ConnectionEvent.REASON_SERVER_INITIATED);
					}

				}

			}
		}

		/**
		 * �����жϰ�����ʧ��
		 * @see com.ll.smsbeans.PacketListener#sendFailed(com.ll.smsbeans.PacketEvent)
		 */
		public void sendFailed(PacketEvent pe)
		{

			_log.info(pe.getPacket().toString());

			if (!((CmppPacket) pe.getPacket()).isResponse())
			{

				SendPacketState sps = sendPacketList.get(pe.getPacket());
				if (sps != null)
				{
					sps.setState(SendPacketState.STATE_FAILED);

				}
				//sendPacketList.remove( pe.getPacket());

			}

			if ((((CmppPacket) pe.getPacket()).getCommandId()
				== CmppPacketCommon.ID_CMPP_ACTIVE_TEST) // 0x00000008
				| (((CmppPacket) pe.getPacket()).getCommandId()
					== CmppPacketCommon.ID_CMPP_TERMINATE)) // 0x00000002
			{
				sendPacketList.remove(pe.getPacket());
				disconnected(ConnectionEvent.REASON_IO_ERROR);

			}
		}

	}

	/**
	 * ��·���Ե�ʱ����Ϊ3���ӣ�180�룩��
	 */
	public final static int ACTIVETIME = 30;

	/**
	 * ���Ͱ��ĳ�ʱʱ��Ϊ60�롣
	 */
	public final static int OVERTIME = 10;
	/**
	 * �ظ������Ĵ���
	 */
	private final static int RESENDCOUNT = 3;

	private String sourceAddr;
	private String password;

	private IdBuilder idBuilder;

	/**
	 * ��ʱ��
	 */
	private Timer mTimer;

	/**
	 * ����û�н��յ��ظ����ķ��Ͱ������Ҽ�¼ÿ�����ķ���ʱ��ͷ�������
	 */
	private SendPackStateList sendPacketList = SendPackStateList.getInstance();

	/** ��ǰ���ӵ�״̬*/
	private ConnectionEvent.EState status;

	/** ����ʵ�������¼������ӿڵĶ����б� */
	private final Vector connectionListeners = new Vector();

	/** ����ʵ�����ݰ��Ķ����б� */
	private final Vector packetListeners = new Vector();

	/** �������ӵ�Socket */
	private Socket socket = null;

	private InputStreamInterface isi = new InputStreamInterface();

	private OutputStreamInterface osi = new OutputStreamInterface();

	/** �����������̣߳����ڽ������ݰ������ҷ��ͽ������ݰ��¼� */
	private InputStreamHandler input = null;

	/** ����������̣߳����ڷ������ݰ������ҷ��ͷ������ݰ��¼� */
	private OutputStreamHandler output = null;

	/**
	 * Ĭ�Ϸ������˿�
	 */
	private int DEFAULT_PORT = 7890;

	static Logger _log;

	/**
	*��������̻߳ص�ConnectionBean�ķ����Ľӿ���
	*  
	*
	* @author  listlike <a href="mailto:listlike@hotmail.com">
	*                      <i>&lt;listlike@hotmail.com&gt;</i></a>
	*
	 */
	public class OutputStreamInterface
	{
		public void sent(Packet p)
		{

			ConnectionBean.this.sent(p);
		}

		public void sendFailed(Packet p)
		{
			ConnectionBean.this.sendFailed(p);
		}

		public void unexpectedThreadDeath(Exception e)
		{
			ConnectionBean.this.onOutputDeath(e);
		}
	}

	/**
	*���������̻߳ص�ConnectionBean�ķ����Ľӿ���
	*  
	*
	* @author  listlike <a href="mailto:listlike@hotmail.com">
	*                      <i>&lt;listlike@hotmail.com&gt;</i></a>
	*
	 */

	public class InputStreamInterface
	{
		public void received(Packet p)
		{

			ConnectionBean.this.received(p);
		}

		public void unexpectedThreadDeath(Exception e)
		{
			ConnectionBean.this.onInputDeath(e);
		}
	}

	/**
	 * ���캯��
	 */
	public ConnectionBean(String sourceAddr, String password)
	{
		this.sourceAddr = sourceAddr;
		this.password = password;

		idBuilder = IdBuilder.getInstance();
		status = ConnectionEvent.STATE_DISCONNECTED;
		output = null;
		input = null;

		mTimer = null;

		addPacketListener(new ConnectionBean.PacketStreamMonitor());
	}

	/**
	 * ʹ��Ĭ�϶˿���ָ���ķ������������ӡ�
	 *
	 * @param host <code>InetAddress</code>������ַ
	 * @exception java.io.IOException socket error in backend
	 */
	public void connect(InetAddress host) throws IOException
	{
		connect(host, host.getHostName(), DEFAULT_PORT);
	}

	/**
	 * ��ָ���ķ������˿ڽ������ӡ�
	 *
	 * @param host <code>InetAddress</code> ������ַ
	 * @param port an <code>int</code> �˿ں�
	 * @exception java.io.IOException socket error in backend
	 */
	public synchronized void connect(InetAddress host, int port)
		throws IOException
	{
		//setConnecting();
		connect(host, host.getHostName(), port);
	}

	/**
	 * ��ָ���ķ������˿ڽ������ӡ�
	 *
	 * @param host <code>InetAddress</code> ������ַ
	 * @param name <code>String</code> ������
	 * @param port an <code>int</code> �˿ں�
	 * @exception java.io.IOException socket error in backend
	 */
	public synchronized void connect(InetAddress host, String name, int port)
		throws IOException
	{
		setConnecting();
		Socket s = new Socket(host, port);
		connect(s);

	}

	/** 
	 * ��ʼ�ͷ�����ͨ��,������֤����
	 *
	 * @param s <code>Socket</code> �Ѿ���������socket��
	 */
	public synchronized void connect(Socket s) throws IOException
	{

		if (socket != null)
			disconnect();
		setConnecting();
		setSocket(s);
		connect(s.getInputStream(), s.getOutputStream());
	}

	/**
	 *��ʼ�ͷ�����ͨ��,������֤����
	 *
	 * @param is <code>InputStream</code> �ӷ�����������������������
	 * @param os <code>OutputStream</code> ���͵��������������������
	 */
	private synchronized void connect(InputStream is, OutputStream os)
		throws IOException
	{

		output = new OutputStreamHandler(osi);
		output.setOutputStream(os);
		output.start();

		input = new InputStreamHandler(isi);
		input.setInputStream(is);
		input.start();

		//TODO ���뿪ʼ�İ�	

		SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		//	dateString="0913185441";

		CmppConnectBuilder ccb = new CmppConnectBuilder();

		ccb.setSourceAddr(sourceAddr);
		ccb.setSpPassword(password);

		ccb.setTimestamp(dateString);

		send(ccb.builder());

	}

	/**
	 * ���õ�ǰ������״̬ΪSTATE_CONNECTING��
	 */

	private void setConnecting()
	{
		sendPacketList.clear();
		if (status == ConnectionEvent.STATE_DISCONNECTED)
		{

			setConnectionState(
				ConnectionEvent.STATE_CONNECTING,
				ConnectionEvent.REASON_CLIENT_INITIATED);
			idBuilder.resetSequenceId();
		}
	}

	/**
	 * ����socket.
	 * @param s <code>Socket</code> socket
	 */
	private synchronized void setSocket(Socket s)
	{
		socket = s;
	}

	/**
	 * �Ͽ��ڷ����������ӣ�������ֽ�Ͽ�״̬�������ж����Ӱ���
	 */
	public synchronized void disconnect()
	{
		if (status.getValue() == ConnectionEvent.EState.CONNECTED)
		{
			CmppTerminateBuilder ctb = new CmppTerminateBuilder();
			//ct.setSequenceId(idBuilder.getSequenceId());
			//			ctb.CreateNextSequenceId();
			send(ctb.builder());
		} else
		{
			disconnected(ConnectionEvent.REASON_CLIENT_INITIATED);
		}
	}

	/**
	 * �Ͽ��ڷ����������ӣ����öϿ�״̬��
	 * 
	 * @param reason <code>ConnectionEvent.EReason</code> �Ͽ���ԭ��
	 */
	private synchronized void disconnected(ConnectionEvent.EReason reason)
	{
		if (mTimer != null)
			mTimer.interrupt();
		mTimer = null;
		sendPacketList.clear();

		if (socket != null)
		{
			try
			{
				if (input != null)
					input.shutdown();
				if (output != null)
					output.shutdown();
				socket.close();
				try
				{
					input.join();
				} catch (InterruptedException e)
				{
				}
			} catch (IOException e)
			{
				/* do nothing */
			}
			socket = null;
		}

		input = null;
		output = null;

		setConnectionState(ConnectionEvent.STATE_DISCONNECTED, reason);
	}

	/**
	 * <code>addConnectionListener</code> Ϊ����״̬�ı�ļ������б�����һ��
	 * <code>ConnectionListener</code> ��
	 *
	 * @param l  <code>ConnectionListener</code>����ͬ��ConnectionListener�����������Ρ�
	 */
	public final synchronized void addConnectionListener(ConnectionListener l)
	{
		if (!connectionListeners.contains(l))
			connectionListeners.addElement(l);
	}

	/**
	 * <code>delConnectionListener</code>����״̬�ı�ļ������б�ɾ��һ��
	 * <code>ConnectionListener</code> �� 
	 *
	 * @param l  <code>ConnectionListener</code> ֵ
	 */
	public final synchronized void delConnectionListener(ConnectionListener l)
	{
		if (packetListeners.contains(l))
			connectionListeners.removeElement(l);
	}

	/**
	 * <code>addPacketListener</code> Ϊ���Ӱ��������б�����һ��
	 * <code>PacketListener</code>�����ڼ����Ѿ����ͻ����ܵ��İ� ��
	 *
	 * @param l  <code>PacketListener</code> ֵ
	 */
	public final synchronized void addPacketListener(PacketListener l)
	{
		if (!packetListeners.contains(l))
			packetListeners.addElement(l);

	}

	/**
	 * <code>addPacketListener</code> Ϊ���Ӱ��������б�����һ��
	 * <code>PacketListener</code>��
	 * 
	 * @param l a <code>PacketListener</code> ֵ
	 */
	public final synchronized void delPacketListener(PacketListener l)
	{
		if (packetListeners.contains(l))
			packetListeners.removeElement(l);
	}

	/**
	 * <code>send</code> ���з��Ͱ���Ҫ���õķ�����
	 * <code>PacketListener</code>���������ݰ��Ƿ��ͳɹ���
	 * @param packet  <code>Packet</code> ���͵��������İ���
	 */
	public void send(Packet packet)
	{
		if (!((CmppPacket) packet).isResponse())
		{
			sendPacketList.add(packet);

		}
		_log.log(
			LogCommon.DEBUG_LEVEL,
			"sendPacketList=" + ConnectionBean.this.sendPacketList.size());

		/**TODO �ı� */
		//	if (status.getValue() == status.CONNECTED)
		//			return;
		if (output != null)
			output.send(packet);
	}

	/**
	 * <code>received</code> Ϊ���յ�������֪ͨ�ķ���. �����������ֻ��<code>InputStreamHandler</code>���ã���Ҫ�������ĵط�ʹ�����������
	 *
	 * @param packet a <code>Packet</code> ���հ�.
	 */
	private void received(Packet packet)
	{

		fireReceivedPacket(packet);
	}

	/**
	 * <code>received</code> Ϊ���Ͱ�����֪ͨ�ķ���. �����������ֻ��<code>OutputStreamHandler</code>���ã���Ҫ�������ĵط�ʹ�����������
	 *
	 * @param packet a <code>Packet</code> ���Ͱ�. 
	 *
	 */
	private void sent(Packet packet)
	{
		fireSendPacket(packet);
	}

	/**
	 * <code>sendFailed</code> ���ͷ���ʧ�ܵ�֪ͨ�� �����������ֻ��<code>OutputStreamHandler</code>���ã���Ҫ�������ĵط�ʹ�����������
	 *
	 * @param packet a <code>Packet</code> ���Ͱ�. 
	 */
	private void sendFailed(Packet packet)
	{
		fireUnsendPacket(packet);
	}

	/**
	 * ���ͽ��յ����ݰ����¼������������<code>InputStreamHandler</code>���á�
	 *
	 * @param <code>Packet</code> ���͵����м����İ���
	 */
	protected final void fireReceivedPacket(Packet packet)
	{
		try
		{
			// ����һ���㲥�¼���
			PacketEvent pe = new PacketEvent(this, packet);

			Vector broadcast = (Vector) packetListeners.clone();

			// �㲥�����еļ�����
			for (Enumeration e = broadcast.elements(); e.hasMoreElements();)
			{
				try
				{
					((PacketListener) e.nextElement()).receivedPacket(pe);
				} catch (Throwable e1)
				{
				}
			}
		} catch (Throwable e)
		{
		}
	}

	/**
	 * �㲥�Ѿ��������ݰ����¼������������<code>OutputStreamHandler</code>���á�
	 *
	 * @param <code>Packet</code> ���͵����м����İ���
	 */
	protected final void fireSendPacket(Packet packet)
	{
		try
		{
			// ����һ���㲥�¼���
			PacketEvent pe = new PacketEvent(this, packet);

			Vector broadcast = (Vector) packetListeners.clone();

			// �㲥�����еļ�����
			for (Enumeration e = broadcast.elements(); e.hasMoreElements();)
			{
				try
				{
					((PacketListener) e.nextElement()).sentPacket(pe);
				} catch (Throwable e1)
				{
				}
			}
		} catch (Throwable e)
		{
		}
	}

	/**
	 * �㲥�Ѿ��������ݰ�ʧ�ܵ��¼������������<code>OutputStreamHandler</code>���á�
	 *
	 * @param <code>Packet</code> ���͵����м����İ���
	 */
	protected final void fireUnsendPacket(Packet packet)
	{
		try
		{
			// ����һ���㲥�¼���
			PacketEvent pe = new PacketEvent(this, packet);

			Vector broadcast = (Vector) packetListeners.clone();

			// �㲥�����еļ�����
			for (Enumeration e = broadcast.elements(); e.hasMoreElements();)
			{
				try
				{
					((PacketListener) e.nextElement()).sendFailed(pe);
				} catch (Throwable e1)
				{
				}
			}
		} catch (Throwable e)
		{
		}
	}

	/**
	 * ��ӦOutputStreamHandler����.
	 */
	public void onOutputDeath(Exception e)
	{
		disconnected(ConnectionEvent.REASON_IO_ERROR);
		// throw new RuntimeException("Death of output thread: " + e.toString());
	}

	/**
	 *��ӦInputStreamHandler����.
	 */
	public void onInputDeath(Exception e)
	{
		disconnected(ConnectionEvent.REASON_IO_ERROR);
		// throw new RuntimeException("Death of input thread: " + e.toString());
	}

	/**
	 * ���ص�ǰ��ǰ����״̬��
	 */
	public final ConnectionEvent.EState getConnectionState()
	{
		return status;
	}

	/**
	 *��������״̬���㲥״̬�ı���¼���
	 *
	 * @param state �µ�״̬��
	 * @param reason �ı�״̬��ԭ��
	 *
	 * @see ConnectionEvent.EState
	 */
	protected final void setConnectionState(
		ConnectionEvent.EState state,
		ConnectionEvent.EReason reason)
	{
		ConnectionEvent ce =
			new ConnectionEvent(this, state, getConnectionState(), reason);
		this.status = state;

		Vector broadcast = (Vector) connectionListeners.clone();
		Enumeration e = broadcast.elements();

		//�㲥�¼�
		while (e.hasMoreElements())
		{
			try
			{
				((ConnectionListener) e.nextElement()).connectionChanged(ce);
			} catch (Throwable e1)
			{
			}
		}
	}

	/**
	 * ��Ӧ��ʱ��
	 *
	 */
	private void onTimer()
	{
		
		
		_log.log(LogCommon.DEBUG_LEVEL ,"onTimer!!!");
		mTimer.setEnableTimer(false);

		for (Iterator iter = sendPacketList.values().iterator();
			iter.hasNext();
			)
		{
			try
			{
				SendPacketState sp = (SendPacketState) iter.next();

				if (sp.isOverTime())
				{
					sp.addSendNum();
					_log.fine("overTime packet");
					if (sp.getSendNum() > RESENDCOUNT)
					{

						sendFailed(sp.getSendPacket());
					} else
					{
						//	output.send(sp.getPacket());
						sp.addSendNum();
					}

				}

			} catch (Throwable e1)
			{
			}
		}
		mTimer.setEnableTimer(true);
	}

	/**
	 * ��Ӧ������·
	 *
	 */
	private void onActiveConnect()
	{
		_log.fine("onActiveConnect!!!");
		mTimer.setEnableActiveTimer(false);

		CmppActiveTestBuilder catb = new CmppActiveTestBuilder();

		send(catb.builder());
		mTimer.setEnableActiveTimer(true);
	}

	/**
	 * �õ���ǰû�н��յ��ظ����ķ��Ͱ���������
	 * @return ���Ͱ�������
	 */
	public int getSendPackedNumber()
	{
		return sendPacketList.size();
	}

	static {
		_log = Logger.getLogger("com.ll.smsbeans.ConnectBean");

	}

}
