package com.ll.smsbeans;

/**
 * <code>PacketListenerRegistrar</code> ���ڶ���ע��PacketListener�Ľӿڡ�
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 */


public interface PacketListenerRegistrar
{
	/**
	 * <code>addPacketListener</code> ����ע��һ����Ӧ���¼��� <code>
	 * PacketListener</code> ��
	 *
	 * @param l <code>PacketListener</code> ��Ҫע���PacketListener��
	 */
	void addPacketListener(PacketListener l);

	/**
	 * <code>delPacketListener</code> ȡ��ע��һ����Ӧ���¼��� <code>
	 * PacketListener</code> ��
	 *
	 * @param l <code>PacketListener</code> ��Ҫȡ��ע���PacketListener��
	 */
	void delPacketListener(PacketListener l);
}
