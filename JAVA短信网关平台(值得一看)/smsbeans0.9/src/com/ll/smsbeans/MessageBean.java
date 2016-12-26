package com.ll.smsbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import com.ll.smsbeans.cmpp3.CmppPacket;
import com.ll.smsbeans.cmpp3.CmppPacketCommon;

/**
 * 
 * <code>MessageBean</code> ͨ��Connection����ͨ��Connection����submit,query,cancel���ݰ����������ǵĻظ�����
 * 
 *  ConnectionΪ�Ѿ����ӵ�ConnectBean,<code>DeliverBean</code>����ָ����
 * 
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 *
 */

public class MessageBean implements Serializable, PacketListenerRegistrar
{

	/** ���������bean */
	private ConnectionBean connection = null;

	/** ���˷�����ת���� */
	private PacketRebroadcaster rebroadcaster = null;

	/**
	 * ͨ������࣬ʵ�ֶ��ƵĹ��ˣ����������ֻת��Deliver����
	 */
	class MessageListener extends PacketRebroadcaster
	{
		/**
		 * <code>receivedPacket</code> ���˽��ձ�
		 *
		 * @param p a <code>PacketEvent</code> ֵ
		 */
		public void receivedPacket(PacketEvent p)
		{

			if ((p.getPacket() instanceof Packet)
				&& (p.getSource() == connection))
			{
				if (p.getPacket() instanceof CmppPacket)
				{
					CmppPacket cp = (CmppPacket) p.getPacket();

					if (cp.getCommandId()
						== CmppPacketCommon.ID_CMPP_SUBMIT_RESP
						|| cp.getCommandId()
							== CmppPacketCommon.ID_CMPP_QUERY_RESP
						|| cp.getCommandId()
							== CmppPacketCommon.ID_CMPP_CANCEL_RESP)
					{
						fireReceived(p);
					}

				}

			}

		}

		/**
		 * <code>sentPacket</code> ���˷��Ͱ�
		 *
		 * @param p  <code>PacketEvent</code> ֵ
		 */
		public void sentPacket(PacketEvent p)
		{
			if ((p.getPacket() instanceof Packet)
				&& (p.getSource() == connection))
			{
				if (p.getPacket() instanceof CmppPacket)
				{
					CmppPacket cp = (CmppPacket) p.getPacket();

					if (cp.getCommandId() == CmppPacketCommon.ID_CMPP_SUBMIT
						|| cp.getCommandId() == CmppPacketCommon.ID_CMPP_QUERY
						|| cp.getCommandId() == CmppPacketCommon.ID_CMPP_CANCEL)
					{
						fireReceived(p);
					}

				}

			}
		}

		/**
		 *
		 * <code>sendFailed</code> ���˷���ʧ���¼���
		 *
		 * @param p  <code>PacketEvent</code> ֵ
		 */
		public void sendFailed(PacketEvent p)
		{
			if ((p.getPacket() instanceof Packet)
				&& (p.getSource() == connection))
			{
				if (p.getPacket() instanceof CmppPacket)
				{
					CmppPacket cp = (CmppPacket) p.getPacket();

					if (cp.getCommandId() == CmppPacketCommon.ID_CMPP_SUBMIT
						|| cp.getCommandId() == CmppPacketCommon.ID_CMPP_QUERY
						|| cp.getCommandId() == CmppPacketCommon.ID_CMPP_CANCEL)
						fireReceived(p);
				}
			}
		}

	}

	/** ����һ���µ� <code>MessageBean</code> ʵ���� */
	public MessageBean()
	{
		rebroadcaster = new MessageListener();
	}

	/**
	 * ����һ���µ� <code>MessageBean</code> ʵ��, �������Ӷ���
	 *
	 * @param connection  <code>ConnectionBean</code> ��ǰ�����ʹ�õ�����
	 */
	public MessageBean(ConnectionBean connection)
	{
		this();
		setConnection(connection);
	}

	/**
	 * <code>setConnection</code> �ڶ��������������Ӷ���
	 *
	 * @param connection  <code>ConnectionBean</code> ��ǰ�����ʹ�õ�����
	 */
	public void setConnection(ConnectionBean connection)
	{
		this.connection = connection;
		rebroadcaster.registerListener(connection);
	}

	/**
	 * <code>getConnection</code> ���ص�ǰ����ʹ�õ����ӡ�
	 *
	 * @return <code>ConnectionBean</code> ���ص�ǰ����ʹ�õ�����
	 */
	public ConnectionBean getConnection()
	{
		return connection;
	}

	/**
	 * <code>sendsendMessage</code> ֻ����submit,query,cancel���ݰ���
	 *
	 * @param message a <code>Message</code> ֵ
	 */
	public void sendMessage(Packet message)
	{

		if (message instanceof CmppPacket)
		{
			CmppPacket cp = (CmppPacket) message;

			if (cp.getCommandId() == CmppPacketCommon.ID_CMPP_SUBMIT
				|| cp.getCommandId() == CmppPacketCommon.ID_CMPP_QUERY
				|| cp.getCommandId() == CmppPacketCommon.ID_CMPP_CANCEL)
				send(message);
		}

	}

	/**
		 * <code>send</code> ����һ����Ϣ��,�����Ƿ��з��ʹ��ڣ������Ƿ���ʱ��
		 *
		 * @param message  <code>Message</code> ��Ϣ��
		 */
	private void send(Packet message)
	{
		if (connection == null)
			throw new RuntimeException("MessengerBean not connected to a ConnectionBean object");

		Random rand = new Random(new Date().getTime());

		int times = 0;
		while (connection.getSendPackedNumber() > 13)
		{
			try
			{
				Thread.currentThread().sleep(500 + rand.nextInt(500));
			} catch (Exception e)
			{
				// TODO: handle exception
			}
			if (times++ > 100)
				break;
		}

		connection.send(message);
	}

	/**
	 * <code>addPacketListener</code> ����һ���µ�PacketListener��
	 *
	 * @param l  <code>PacketListener</code> ���ӵ�PacketListener��
	 */
	public synchronized void addPacketListener(PacketListener l)
	{
		rebroadcaster.addPacketListener(l);
	}

	/**
	 * <code>delPacketListener</code> ɾ��һ��PacketListener��
	 *
	 * @param l  <code>PacketListener</code> ��ɾ����PacketListener 
	 */
	public synchronized void delPacketListener(PacketListener l)
	{
		rebroadcaster.delPacketListener(l);
	}
}
