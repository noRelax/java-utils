
package com.ll.smsbeans;


import java.util.EventObject;

/**
 *  <code>PacketEvent</code> ��connection�������ݰ����ͻ����ʱ�򷢳����¼��� 
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 *
 */

public class PacketEvent extends EventObject
{
	/** ���¼��йصİ� */
	private Packet packet = null;

	/** 
	 * ����һ��û����ذ��İ��¼���
	 * @param source �㲥�¼��Ķ���
	 */
	public PacketEvent(Object source)
	{
		super(source);
	}

	/** 
	 * ����һ������ذ��İ��¼���
	 * @param source �㲥�¼��Ķ���
	 * @param packet ���¼��йصİ���
	 */
	public PacketEvent(Object source, Packet packet)
	{
		this(source);
		this.packet = packet;
	}

	/**
	 * <code>getPacket</code> ���غ��¼���صİ���
	 *
	 * @return a <code>Packet</code>  ���¼���صİ������û�з���null��
	 */
	public Packet getPacket()
	{
		return packet;
	}
}
