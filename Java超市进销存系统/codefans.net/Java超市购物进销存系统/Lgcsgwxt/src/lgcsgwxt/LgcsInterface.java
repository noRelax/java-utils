package lgcsgwxt;

import java.util.Vector;

public interface LgcsInterface {
    public Vector select_merch_Name(String merch_Name);//����Ʒ���Ʋ�ѯ������һ����ά����
    public Vector select_merch_number(String merch_number);//����Ʒ��Ų�ѯ������һ����ά����
    public Vector select_merch_supply(String merch_supply);//����Ʒ����Ӧ�̲�ѯ������һ����ά����
    public Vector select_merch_producing(String merch_producing);//����Ʒ���ز�ѯ������һ����ά����
    public Vector select_sort_producing(String merch_sort);//����Ʒ����ѯ������һ����ά����
    //��¼����֤����,����һ��boolenֵ
    //��Ᵽ�浽����,����һ��intֵ
    //���Ᵽ�浽�����,����һ��intֵ
    //�˻����浽�˻���,����һ��intֵ
    //��ȡ��ǰʱ��ķ�������ʽ2007-09-01 08:05:01
    //��ѯ���л�Ա��Ϣ�ķ���,����һ����ά����Ľ����
    //�����û��� ��ѯ������Ա��Ϣ�ķ���,����һ����ά����Ľ����
    //�޸Ļ�Ա��Ϣ�ķ���,����һ��intֵ
    //ע���Ա�ķ���,,����һ��intֵ
    //����������㷽��������һ����ά����Ľ����������Ʒ���࣬�������۽��ܽ��
    //ÿ��������������۱�,�����¿���
}
