package com.ll.smsbeans;

import java.util.EventObject;

/**
 *  <code>ConnectionEvent</code> Ϊ������״̬��ConnectionBean�����ߡ����ߡ������С�����ʧ�ܣ����ı��ʱ��
 * ���������¼�������
 *<p>
 * <code>ConnectionEvent</code> ����Ҫ����Ϊsource����ǵ�ǰ���¼������ߣ���Ҫ���ڴ����ж�����ӵ�ʱ��
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 *
 */

public class ConnectionEvent extends EventObject
{
	public final static EState STATE_UNKNOWN = new EState(EState.NOT_SET);
	public final static EState STATE_DISCONNECTED =
		new EState(EState.DISCONNECTED);
	public final static EState STATE_CONNECTING = new EState(EState.CONNECTING);
	public final static EState STATE_CONNECTED = new EState(EState.CONNECTED);

	public final static EReason REASON_UNKNOWN = new EReason(EReason.NOT_SET);
	public final static EReason REASON_CLIENT_INITIATED =
		new EReason(EReason.CLIENT_INITIATED);
	public final static EReason REASON_SERVER_INITIATED =
		new EReason(EReason.SERVER_INITIATED);
	public final static EReason REASON_IO_ERROR = new EReason(EReason.IO_ERROR);

	/**
	 * EState ��һ��ö���࣬Ϊ��ǰ���ӵ���������״̬��
	 *
	* @author  listlike <a href="mailto:listlike@hotmail.com">
 	*                      <i>&lt;listlike@hotmail.com&gt;</i></a>
	*/
	
	public static final class EState
	{
		/* EState ö��ֵ���� */
		public final static int NOT_SET = -1;
		public final static int DISCONNECTED = 0;
		public final static int CONNECTING = 1;
		public final static int CONNECTED = 2;

		/** ���浱ǰֵ - Ĭ��ֵΪ 'not set' */
		private int value = NOT_SET;

		/**
		 * ���� <code>EState</code> ʵ��.
		 *
		 * @param newVal an <code>int</code> ֵ����ǰ������״̬��
		 * @exception IllegalArgumentException ״ֵ̬����ȷ
		 */
		protected EState(int newVal) throws IllegalArgumentException
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
				case DISCONNECTED :
					return "DISCONNECTED";
				case CONNECTING :
					return "CONNECTING";
				case CONNECTED :
					return "CONNECTED";
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
	
	/**
	* EReason ��һ��ö���࣬��ǰ����������״̬�ı��ԭ��
	*
	* @author  listlike <a href="mailto:listlike@hotmail.com">
	*                      <i>&lt;listlike@hotmail.com&gt;</i></a>
	*/
	
	public final static class EReason
	{
		/* EReason ö�ٵ�ֵ  */
		public static final int NOT_SET = -1;
		public static final int CLIENT_INITIATED = 0;
		public static final int SERVER_INITIATED = 1;
		public static final int IO_ERROR = 2;
		
		/** ���浱ǰֵ  */
		private int value;

		/**
		* ���� <code>EReason</code> ʵ��.
		*
		* @param reason an <code>int</code> ֵ���ı��ԭ��
	    * @exception IllegalArgumentException ״ֵ̬����ȷ
		*/
		protected EReason(int reason) throws IllegalArgumentException
		{
			if ((reason < -1) || (reason > 2))
				throw new IllegalArgumentException("Invalid Enumeration Value");
			value = reason;
		}
		/**
		 * <code>getValue</code> ���ص�ǰö��������Ӧ��ֵ.
		 *
		 * @return  <code>int</code> ��ǰö��������Ӧ��ֵ
		 */
		public int getValue()
		{
			return value;
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
				case CLIENT_INITIATED :
					return "CLIENT_INITIATED";
				case SERVER_INITIATED :
					return "SERVER_INITIATED";
				case IO_ERROR :
					return "IO_ERROR";
				default :
					return "Internal Error";
			}
		}
	}

	private EState state, oldState;
	private EReason reason;

	/**
	 * ����һ���µ�<code>ConnectionEvent</code> ʵ��.���������ý�����ConnectionBean����
	 *
	 * @param source an <code>Object</code> ��ǰ��ConnectionBean��
	 * @param state an <code>Estate</code> �µ�״̬
	 * @param oldState an <code>EState</code> �ϵ�״̬
	 * @param reason an <code>Estate</code> �ı�ԭ��
	 */
	public ConnectionEvent(
		Object source,
		EState state,
		EState oldState,
		EReason reason)
	{
		super(source);
		this.state = state;
		this.oldState = oldState;
		this.reason = reason;
	}

	/**
	 * <code>getState</code> ������¼�����������������״̬��
	 *
	 * @return an <code>EState</code> ��ǰ��״̬
	 */
	public EState getState()
	{
		return state;
	}

	/**
	 * <code>getOldState</code> ������¼�����ǰ������������״̬��
	 *
	 * @return an <code>EState</code> �ϵ�״̬
	 */
	public EState getOldState()
	{
		return oldState;
	}

	/**
	 * <code>getReason</code> ����״̬�ı��ԭ��, ������Щԭ������ʧ�� ���ͻ�������Ͽ�������������Ͽ���
	 *
	 * @return an <code>EReason</code> �ı�ԭ��
	 */
	public EReason getReason()
	{
		return reason;
	}
}
