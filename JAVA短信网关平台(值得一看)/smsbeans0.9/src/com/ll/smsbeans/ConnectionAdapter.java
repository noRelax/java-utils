package com.ll.smsbeans;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * �������ConnectionListener�ӿڵ�һ����ʵ�֡�
 * �����������¼������˼򵥵Ĵ���
 * ����ͨ���̳������ķ������׵�ʵ�ֶ������¼��Ĵ��������ù�ע��̨�������
 *
 *<p>
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 *
 */

public class ConnectionAdapter implements ConnectionListener, Serializable
{	
	
	static Logger _log;
	/**
	 * <code>connectionChanged</code> ��������״̬�ĸı��¼������¼���ʱת������Ӧ�ķ���
	 *
	 * @param evt a <code>ConnectionEvent</code> �����¼�
	 */
	public void connectionChanged(ConnectionEvent evt)
	{
		switch (evt.getState().getValue())
		{
			case ConnectionEvent.EState.DISCONNECTED :
				if (evt.getOldState() == ConnectionEvent.STATE_CONNECTING)
					connectFailed(evt);
				else
					disconnected(evt);
				break;
			case ConnectionEvent.EState.CONNECTING :
				connecting(evt);
				break;
			case ConnectionEvent.EState.CONNECTED :
				connected(evt);
				break;
			default :
				throw new RuntimeException("Illegal Connection state imput to ConnectionAdapter");
		}
	}

	/** @deprecated  ���� {@link #connectionChanged(ConnectionEvent)},
	  */
	public void connected(ConnectionEvent evt)
	{

		connected();
		;
	}

	/** @deprecated  ���� {@link #connectionChanged(ConnectionEvent)},
	 */
	public void disconnected(ConnectionEvent evt)
	{
		disconnected();
	}
	/** @deprecated  ���� {@link #connectionChanged(ConnectionEvent)},
	  */
	public void connecting(ConnectionEvent evt)
	{
		connecting();
	}
	/** @deprecated  ����  {@link #connectionChanged(ConnectionEvent)},
	  */
	public void connectFailed(ConnectionEvent evt)
	{
		connectFailed();
	};

	/** @deprecated  ���� {@link #connectionChanged(ConnectionEvent)},
	 */
	public void connected()
	{
		_log.info("��������ɹ����ӣ�����");
	}

	/** @deprecated  ���� {@link #connectionChanged(ConnectionEvent)},
	  */
	public void disconnected()
	{
		_log.info("������������ѶϿ�������");
	}

	/** @deprecated  ���� {@link #connectionChanged(ConnectionEvent)},
	  */
	public void connecting()
	{
		_log.info("�������ӷ�����������");
	}

	/** @deprecated  ���� {@link #connectionChanged(ConnectionEvent)},
	  */
	public void connectFailed()
	{
		_log.info("�����������ʧ�ܣ�����");
	}
	
	static {
		_log = Logger.getLogger("com.ll.smsbeans.ConnectionAdapter");
		
	}
}
