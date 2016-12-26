package com.ll.smsbeans;

import java.io.Serializable;

import com.ll.smsbeans.cmpp3.CmppDeliver;
import com.ll.smsbeans.cmpp3.CmppDeliverResp;
import com.ll.smsbeans.cmpp3.CmppDeliverRespBuilder;
import com.ll.smsbeans.cmpp3.CmppPacket;
import com.ll.smsbeans.cmpp3.CmppPacketCommon;

/**
 * 
 * <code>DeliverBean</code> ͨ��Connection����Delive�ͷ���DeliveResp���ݰ���
 * ��������Զ��ظ����յ���Deliver����Ҫʹ���߸��ݽ��յ�����Ϣ�����ظ���ֵ��
 *  ConnectionΪ�Ѿ����ӵ�ConnectBean,<code>DeliverBean</code>����ָ����
 * 
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 *
 */

public class DeliverBean implements Serializable, PacketListenerRegistrar
{

	/** ���������bean */
	private ConnectionBean connection = null;

	/** ���˷�����ת���� */
	private PacketRebroadcaster rebroadcaster = null;

	/**
	 * ͨ������࣬ʵ�ֶ��ƵĹ��ˣ����������ֻת��Deliver����
	 */
	class DeliverListener extends PacketRebroadcaster
	{
		/**
		 * <code>receivedPacket</code> ���˽��ձ�
		 *
		 * @param p a <code>PacketEvent</code> ֻ
		 */
		public void receivedPacket(PacketEvent p)
		{

			if ((p.getPacket() instanceof Packet)
				&& (p.getSource() == connection))
			{
				if (p.getPacket() instanceof CmppPacket)
				{
					CmppPacket cp = (CmppPacket) p.getPacket();
					if (cp.getCommandId() == CmppPacketCommon.ID_CMPP_DELIVER)
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

					if (cp.getCommandId()
						== CmppPacketCommon.ID_CMPP_DELIVER_RESP)
					{
						fireSent(p);
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
				if (p.getPacket() instanceof CmppDeliverResp)
					fireSendFailed(p);
			}
		}

	}

	/** ����һ���µ� <code>DeliverBean</code> ʵ���� */
	public DeliverBean()
	{
		rebroadcaster = new DeliverListener();
	}

	/**
	 * ����һ���µ� <code>DeliverBean</code> ʵ��, �������Ӷ���
	 *
	 * @param connection  <code>ConnectionBean</code> ��ǰ�����ʹ�õ�����
	 */
	public DeliverBean(ConnectionBean connection)
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
	 * <code>sendDeliverResp</code> ����DeliverResp��
	 * @param deliver <code>CmppDeliver</code>��Ҫ�ظ��İ���
	 * @param result ����ֵ��
	 */
	public void sendDeliverResp(CmppDeliver deliver,int result)
	{
		
		CmppDeliverRespBuilder cdrb=new CmppDeliverRespBuilder();
		
		cdrb.setSequenceId( deliver.getSequenceId());
		cdrb.setMsgId( deliver.getMsgId());
		cdrb.setResult(result); 
		connection.send(cdrb.builder());
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
