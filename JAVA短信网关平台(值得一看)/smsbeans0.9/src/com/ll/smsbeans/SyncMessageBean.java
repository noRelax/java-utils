package com.ll.smsbeans;

import java.io.Serializable;
import java.util.logging.Logger;

import com.ll.smsbeans.cmpp3.CmppCancelBuilder;
import com.ll.smsbeans.cmpp3.CmppCancelResp;
import com.ll.smsbeans.cmpp3.CmppPacket;
import com.ll.smsbeans.cmpp3.CmppPacketCommon;
import com.ll.smsbeans.cmpp3.CmppQueryBuilder;
import com.ll.smsbeans.cmpp3.CmppQueryResp;
import com.ll.smsbeans.cmpp3.CmppSubmitBuilder;
import com.ll.smsbeans.cmpp3.CmppSubmitResp;

/**
 * 
 * <code>SyncMessengerBean</code> ͨ��MessengerBean����ͬ����ʽ����submit,query,cancel���ݰ�.
 *  ConnectionΪ�Ѿ����ӵ�ConnectBean,<code>SyncMessengerBean</code>����ָ��ConnectBean��
 * <p>
 * 
 * ԭ�ƻ�д�������Ϊ�������ǱȽϷ����ʹ�ã�����ʵ����ʹ�õ�ʱ��һ���ܷ��㡣
 * �ر�Ҫ��Ҫע����ǣ�����sendSubmit��˵����Ҫ��¼���ص�msgId���Ա�����cancel Submit,�͸���registerDelivery ��ѯ
 * ����Ϣ״̬���������������ʵ�֣�Ҫ����ʹ�õ�ʱ���޸�����࣬��������ʹ��һ��PacketListener��¼msgId��
 * �����ʵ�ֵļ���ª���ֲ�̫���ã�������Ū������ȫ��Ϊ����ʾһ�¹����������ʹ�÷�����
 * ��ʹ�õ�ʱ����Ҫ�����Ľ�������һ���߳������У���������¼�������
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 *
 */

public class SyncMessageBean implements Serializable, PacketListener
{

	static Logger _log;

	/** ���������bean */
	private ConnectionBean connection = null;

	private MessageBean messageBean = null;

	/**
	 * ����һ�� <code>MessengerBean</code> ʵ��,����ע��һ��ConnectionBean��
	 *
	 * @param connection  <code>ConnectionBean</code> 
	 */
	public SyncMessageBean(ConnectionBean connection)
	{
		setConnection(connection);
	}

	/**
	 * <code>setConnection</code> �ڽ������������һ��ConnectionBean
	 *
	 * @param connection a <code>ConnectionBean</code> 
	 */
	public void setConnection(ConnectionBean connection)
	{
		this.connection = connection;
		messageBean = new MessageBean(connection);
		messageBean.addPacketListener(this);
	}

	/**
	 * <code>getConnection</code>���ر��������ʹ�õ�<code>ConnectionBean</code>��
	 *
	 * @return <code>ConnectionBean</code> 
	 */
	public ConnectionBean getConnection()
	{
		return connection;
	}

	/**
	 * ����һ��Submit������ظ������غ󣬸��ݷ��ذ������Ƿ�ɹ������������
	 * 
	 * @param preMessage ��Ҫ���͵�submit����
	 * @param msgContent ��Ҫ���͵����ݡ�
	 * @return �Ƿ�ɹ���0 �ɹ�,-1 ����ʧ�ܣ�>1ͬsubmitResp�����塣
	 */
	public int sendSubmit(CmppSubmitBuilder preMessage, String msgContent)
	{

		//preMessage.setSequenceId(IdBuilder.getInstance().getSequenceId());

		byte[] sb = new byte[0];

		try
		{

			switch (preMessage.getMsgFmt())
			{
				case CmppPacketCommon.MSG_TYPE_ASCII :
					sb = msgContent.getBytes("ASCII");
					break;
				case CmppPacketCommon.MSG_TYPE_BINARY :
					sb = msgContent.getBytes();
					break;
				case CmppPacketCommon.MSG_TYPE_CHINESE :
					sb = msgContent.getBytes("GBK");
					break;
				case CmppPacketCommon.MSG_TYPE_UCS2 :
					sb = msgContent.getBytes("UnicodeBigUnmarked");
					break;
				case CmppPacketCommon.MSG_TYPE_WRITECARD :
					sb = msgContent.getBytes();
					break;
				default :
					sb = msgContent.getBytes("ASCII");
					break;

			}
		} catch (Exception e)
		{
			// TODO: handle exception
		}

		return sendSubmit(preMessage, sb);

	}

	/**
	* ����һ��Cancel������ظ������غ󣬸��ݷ��ذ������Ƿ�ɹ������������
	 * 
	 * @param CmppCancelBuilder ��Ҫ���͵�Cancel����
	 * @return �Ƿ�ɹ���0 �ɹ�,-1 ����ʧ�ܣ�>1ͬCancelResp�����塣
	 */
	public int sendCancel(CmppCancelBuilder ccb)
	{
		int ErrCode = 0;
		CmppCancelResp ccr = null;
		//		ccb.CreateNextSequenceId();
		Packet sendPacket = ccb.builder();
		messageBean.sendMessage(sendPacket);
		Packet rp = (CmppCancelResp) syncPacketResp(sendPacket);
		if (rp != null)
		{
			if (rp instanceof CmppCancelResp)
			{
				ccr = (CmppCancelResp) rp;
				ErrCode = ccr.getSuccessId();
			} else
				ErrCode = -1;

		} else
		{
			ErrCode = -1;
		}
		return ErrCode;
	}

	private Packet syncPacketResp(Packet sendPacket)
	{
		Packet respPack = null;

		int respPacketCommandId =
			0x80000000 | (0xff & ((CmppPacket) sendPacket).getCommandId());
		while (respPack == null)
		{

			try
			{
				wait(1000);
			} catch (Exception e)
			{
			}

			//�ж��Ƿ�ʱ������
			SendPacketState sps =
				SendPackStateList.getInstance().get(sendPacket);
			if (sps != null)
			{
				if (System.currentTimeMillis() - sps.getSendTime() > 120000)
				{

					break;
				}

			}

			//�ж��Ƿ��ܵ���ȷ�Ļظ���

			sps = SendPackStateList.getInstance().get(sendPacket);
			if (sps != null)
			{
				if (sps.getState().getValue()
					== SendPacketState.STATE_RESPONSED.getValue())
				{
					Packet rp = sps.getRespPacket();
					if (rp instanceof CmppPacket)
					{

						if (((CmppPacket) rp).getCommandId()
							== respPacketCommandId)
						{
							respPack = sps.getRespPacket();

							break;

						} else
							break;

					} else
						break;

				}

				if (sps.getState().getValue()
					== SendPacketState.STATE_FAILED.getValue())
					break;
			};
		}

		SendPackStateList.getInstance().remove(sendPacket);
		return respPack;
	}

	/**
	 * ����һ��Query������ظ������غ󣬸��ݷ��ذ������Ƿ�ɹ������������
	 * @param cqb ��ѯ��
	 * @return  ���ز�ѯ��������Ϊnullʧ�ܡ�
	 */

	public CmppQueryResp sendQuery(CmppQueryBuilder cqb)
	{
		int ErrCode = 0;
		CmppQueryResp cqr = null;

		Packet sendPacket = cqb.builder();
		messageBean.sendMessage(sendPacket);

		return (CmppQueryResp) syncPacketResp(sendPacket);
	}

	public int sendSubmit(CmppSubmitBuilder preMessage, byte[] messgae)
	{

		int ErrCode = 0;

		int msgMaxCount =
			preMessage.getMsgFmt() == CmppPacketCommon.MSG_TYPE_ASCII
				? 159
				: 140;

		int packetCount = messgae.length / msgMaxCount;

		packetCount =
			messgae.length % msgMaxCount == 0 ? packetCount : packetCount + 1;

		Packet[] packetList = new Packet[packetCount];

		int currPoint = 0;
		int packetIndex = 0;

		while (true)
		{

			int curLen =
				(currPoint + msgMaxCount) < messgae.length
					? msgMaxCount
					: messgae.length - currPoint;

			byte[] msgByte = new byte[curLen];

			for (int i = 0; i < curLen; i++)
			{
				msgByte[i] = messgae[currPoint + i];
			}

			//			preMessage.CreateNextSequenceId();

			preMessage.setMsgContent(msgByte);

			packetList[packetIndex] = preMessage.builder();

			messageBean.sendMessage(packetList[packetIndex]);
			packetIndex++;

			currPoint += msgMaxCount;
			if (currPoint >= messgae.length)
				break;

		}

		if (packetIndex != packetCount)
		{
			ErrCode = -1;
			return ErrCode;
		}

		Packet respPacket;
		for (int i = 0; i < packetList.length; i++)
		{
			respPacket = syncPacketResp(packetList[i]);
			if (respPacket == null)
			{
				ErrCode = -1;
				break;
			}
			ErrCode = ((CmppSubmitResp) respPacket).getResult();
			if (ErrCode != 0)
				break;
		}
		
		
		for (int i = 0; i < packetList.length; i++)
		{

			SendPackStateList.getInstance().remove(packetList[i]);

		}
		return ErrCode;
	}

	/* ���� Javadoc��
	 * @see com.ll.smsbeans.PacketListener#receivedPacket(com.ll.smsbeans.PacketEvent)
	 */
	public void receivedPacket(PacketEvent pe)
	{

		notify();
	}

	/* ���� Javadoc��
	 * @see com.ll.smsbeans.PacketListener#sendFailed(com.ll.smsbeans.PacketEvent)
	 */
	public void sendFailed(PacketEvent pe)
	{
		notify();
	}

	/* ���� Javadoc��
	 * @see com.ll.smsbeans.PacketListener#sentPacket(com.ll.smsbeans.PacketEvent)
	 */
	public void sentPacket(PacketEvent pe)
	{
		//notify();	
	}

	static {
		_log = Logger.getLogger("com.ll.smsbeans.SyncMessageBean");
	}

}
