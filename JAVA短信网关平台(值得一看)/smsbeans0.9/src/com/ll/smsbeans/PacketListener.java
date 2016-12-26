
package com.ll.smsbeans;

/**
 * <code>PacketListener</code> �ͻ�����Ӧ���¼��Ľӿڡ�
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 *
 */
public interface PacketListener
{
    /**
     * ���а������ʱ����õķ����� 
     *
     * @param pe PacketEvent �ոյ���İ��¼���
     */
    void receivedPacket(PacketEvent pe);

    /**
     *���ͻ��˷��Ͱ��ɹ�����õķ�����
     *
     * @param pe PacketEvent ���ͳɹ�����¼���
     */
    void sentPacket(PacketEvent pe);

    /**
     * ����û�з��ͳɹ���ʱ����� (���磺������ӶϿ���ʱ�򣬰��ڷ��Ͷ����У����߷��͵�ʱ��Ͽ�����)��
     *
     * @param pe PacketEvent ����ʧ�ܵ��¼���
     */
    void sendFailed(PacketEvent pe);
}
