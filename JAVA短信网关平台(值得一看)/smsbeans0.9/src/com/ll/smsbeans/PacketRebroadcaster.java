package com.ll.smsbeans;

import java.util.Vector;
import java.util.Enumeration;
import java.io.Serializable;

/**
 * <code>PacketRebroadcaster</code>  ����ָ����beans����Ԥ�����˽ӿڵ��ࡣ 
 * ͨ��ʹ���������дPacketListener�ĳ�Ա�����Ը���������˰���
 * <p>
 * Ҫ��:
 * <ul><li>
 * ö��list(the Vector)�Ĺ����������ͼ�ı�list(the Vector)��ʱ���ʧ�ܡ�����Ԥ�⵽���ַ���ʹ�������
 * ��Ϊ�˱�֤��ȷ����ö��list��֮ǰ��clone���list���Һ�ϣ��֪������û�б�Ľ���취��
 *
 * </li><li>
 * �ڷ����¼���ʱ�������쳣����ʲôҲ������������ش��������п�����ɴ����жϡ�
 * </li></ul>
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 *
 */

public abstract class PacketRebroadcaster
	implements PacketListener, Serializable, PacketListenerRegistrar
{
	/** Vector holding people who are listening for packet events */
	private Vector listeners = new Vector();

	/**
	 * <code>registerListener</code> ��ConnectionBeanע�����, ���ڼ����¼���
	 *
	 * @param cb a <code>ConnectionBean</code> ֵ
	 */
	public void registerListener(ConnectionBean cb)
	{
		cb.addPacketListener(this);
	}

	/**
	 * <code>unregisterListener</code> ȡ��ע��������Ժ��ټ����¼���
	 *
	 * @param cb a <code>ConnectionBean</code> ֵ
	 */
	public void unregisterListener(ConnectionBean cb)
	{
		cb.delPacketListener(this);
	}

	/**
	 * PacketListener�ĳ�Ա
	 *
	 * @param pe  <code>PacketEvent</code> ֵ
	 */
	public abstract void receivedPacket(PacketEvent pe);

	/**
	 * PacketListener�ĳ�Ա
	 *
	 * @param pe  <code>PacketEvent</code> ֵ
	 */
	public abstract void sentPacket(PacketEvent pe);

	/**
	 * PacketListener�ĳ�Ա
	 *
	 * @param pe  <code>PacketEvent</code> ֵ
	 */
	public abstract void sendFailed(PacketEvent pe);

	/**
	 * <code>addPacketListener</code> ע��һ���������ǹ��˹��İ��㲥�ļ�������
	 *
	 * @param l  <code>PacketListener</code> ֵ
	 */
	public final void addPacketListener(PacketListener l)
	{
		listeners.addElement(l);
	}

	/**
	 * <code>delPacketListener</code>ȡ��ע��һ���������ǹ��˹��İ��㲥�ļ�������
	 *
	 * @param l  <code>PacketListener</code> ֵ
	 */
	public final void delPacketListener(PacketListener l)
	{
		if (listeners.contains(l))
			listeners.removeElement(l);
	}

	/**
	 * <code>fireReceived</code> ��PacketListener���յ����հ�����õķ����� 
	 * �������������ͬ�ı�������ע�ᵽ���������Ķ���
	 *
	 * @param p  <code>PacketEvent</code> ֵ
	 */
	protected final void fireReceived(PacketEvent p)
	{
		Vector broadcast = (Vector) listeners.clone();
		Enumeration e = broadcast.elements();
		while (e.hasMoreElements())
		{
			try
			{
				((PacketListener) e.nextElement()).receivedPacket(p);
			} catch (Throwable e1)
			{
			}
		}
	}

	/**
	 * <code>fireReceived</code> ��PacketListener���յ����Ͱ�����õķ����� 
	 * �������������ͬ�ı�������ע�ᵽ���������Ķ���
	 *
	 * @param p  <code>PacketEvent</code> ֵ
	 */
	protected final void fireSent(PacketEvent p)
	{
		Vector broadcast = (Vector) listeners.clone();
		Enumeration e = broadcast.elements();
		while (e.hasMoreElements())
		{
			try
			{
				((PacketListener) e.nextElement()).sentPacket(p);
			} catch (Throwable e1)
			{
			}
		}
	}

	/**
	 * <code>fireReceived</code> ��PacketListener���յ����Ͱ�����õķ����� 
	 * �������������ͬ�ı�������ע�ᵽ���������Ķ���
	 *
	 * @param p a <code>PacketEvent</code> ֵ
	 */
	protected final void fireSendFailed(PacketEvent p)
	{
		Vector broadcast = (Vector) listeners.clone();
		Enumeration e = broadcast.elements();
		while (e.hasMoreElements())
		{
			try
			{
				((PacketListener) e.nextElement()).sendFailed(p);
			} catch (Throwable e1)
			{
			}
		}
	}
	

}
