
package com.ll.smsbeans;

import com.ll.smsbeans.cmpp3.CmppPacket;

/**
* 
*SendPacket��Ϊ�ڷ��͹����м�¼�����͵İ�����ʱ����ظ����͵Ĵ������࣬
*�����жϰ��Ƿ��ͳɹ���
*
*<p>
*
* @author  listlike <a href="mailto:listlike@hotmail.com">
*                      <i>&lt;listlike@hotmail.com&gt;</i></a>
*
 */
public class SendPacketState
{
	
	/**
	 * PState ��һ��ö���࣬Ϊ��ǰ���Ͱ���״̬��
	 *
	 * @author  listlike <a href="mailto:listlike@hotmail.com">
 	 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
	 */
	public static final class PState
	{
		/* PState ö��ֵ���� */
		public final static int NOT_SET = -1;
		public final static int SENDED = 0;
		public final static int RESPONSED = 1;
		public final static int FAILED = 2;

		/**  */
		private int value = NOT_SET;

		/**
		 * ���� <code>PState</code> ʵ��.
		 *
		 * @param newVal an <code>int</code> ֵ����ǰ������״̬��
		 * @exception IllegalArgumentException ״ֵ̬����ȷ
		 */
		protected PState(int newVal) throws IllegalArgumentException
		{
			if ((newVal < -1) || (newVal > 2))
				throw new IllegalArgumentException("Invalid Enumeration Value");
			value = newVal;
		}

		/**
		 * ��ǰֵ���ַ���ʾ.
		 *
		 * @return a <code>String</code> ö��ֵ������
		 */
		public String toString()
		{

			switch (value)
			{
				case NOT_SET :
					return "NOT_SET";
				case SENDED :
					return "SENDED";
				case RESPONSED :
					return "RESPONSED";
				case FAILED :
					return "FAILED";
				default :
					return "Internal Error";
			}
		}

		/**
		 * <code>getValue</code> �õ���ǰ��ֵ��
		 *
		 * @return  <code>int</code> ��ǰ��ֵ��
		 */
		public int getValue()
		{
			return value;
		}

	}

	public final static PState STATE_FAILED = new PState(PState.FAILED);
	public final static PState STATE_SENDED = new PState(PState.SENDED);
	public final static PState STATE_RESPONSED =
		new PState(PState.RESPONSED);
	public final static PState STATE_UNKNOWN = new PState(PState.NOT_SET);

	private PState state;

	/**
	 *  ���͵İ�
	 */
	private Packet sendPacket;

	/**
	 * �ظ���
	 */
	private Packet respPacket;

	/**
	 *  �ظ����ʹ���
	 */
	private int SendNum;
	
	/**
	 * ����ʱ��
	 */
	private long SendTime;

	/**
	 * ���캯��
	 * 
	 * @param packet ���͵����ݰ�
	 */
	public SendPacketState(Packet packet)
	{
		sendPacket = packet;
		this.SendNum = 0;
		this.SendTime = System.currentTimeMillis();
		state=new PState(PState.SENDED);
	}

	/**
	 * �����ظ����͵Ĵ���
	 *
	 */
	public void addSendNum()
	{
		SendNum++;
		SendTime = System.currentTimeMillis();
	}

	public boolean isOverTime()
	{
		return (System.currentTimeMillis() - SendTime)
			> (ConnectionBean.OVERTIME * 1000)
			? true
			: false;
	}

	/**
	 * 
	 * @return ��ǰ���͵Ĵ���
	 */
	public int getSendNum()
	{
		return SendNum;
	}

	/**
	 * @return ��ǰ�Ѿ����͵�ʱ��
	 */
	public long getSendTime()
	{
		return SendTime;
	}

	/**
	 * 
	 * @return �õ���ǰ���ķ������кţ������ж��Ƿ���ָ������
	 */
	public int getPacketSequenceId()
	{
		if (sendPacket instanceof CmppPacket)
		{
			CmppPacket cp = (CmppPacket) sendPacket;
			return cp.getSequenceId();
		} else
			return -1;
	}
	/**
	 * ���¹���equals�������������д���ǰ��
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if (obj instanceof SendPacketState)
		{
			return ((SendPacketState) obj).getPacketSequenceId()
				== getPacketSequenceId();

		} else
			return false;

	}

	/**
	 * @return ���Ͱ�
	 */
	public Packet getSendPacket()
	{
		return sendPacket;
	}

	public Packet getRespPacket()
	{
		return respPacket;
	}

	/**
	 * @param packet
	 */
	public void setRespPacket(Packet packet)
	{
		respPacket = packet;
	}

	/**
	 * @return
	 */
	public PState getState()
	{
		return state;
	}

	/**
	 * @param state
	 */
	public void setState(PState state)
	{
		this.state = state;
	}

}