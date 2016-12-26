

package com.ll.smsbeans;

/**
 *  <code>ConnectionListener</code>Ϊһ�������ӿ�,�����ͨ��ʵ�ֽӿں�ע�������״̬�ı��¼��Ĵ��� ��
 * <p>
 * 
 * 
 * <code>ConnectionAdapter</code> Ϊ���ӿ�ʵ�ֵ��࣬�������г�һЩ�����κ�����ķ�����ʹ�����ͨ���̳������ķ�ʽ��������״̬�ĸı䡣
 * <p>
 * ��Щ���е��¼�������ConnectionBean��������ģ���������ʵ���˶��¼���������ע�ᡣ
 * <p>
 * @see ConnectionAdapter
 * @see ConnectionBean
 *
 * @author  listlike <a href="mailto:listlike@hotmail.com">
 *                      <i>&lt;listlike@hotmail.com&gt;</i></a>
 *
 */
public interface ConnectionListener
{
    /**
     * <code>connectionChanged</code> ���յ�״̬�ı���¼���
     */
    void connectionChanged(ConnectionEvent ce);
}
