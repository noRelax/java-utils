

//���������

package classsource;

import java.awt.*;//����
import java.awt.event.*;//����
import javax.swing.*;//����

public class About extends JInternalFrame {


 		JLabel label = new JLabel("���л�����Windows");//������ǩ��ʵ����
		JLabel labe2 = new JLabel("�������ԣ�JAVA");//������ǩ��ʵ����
		JLabel labe3 = new JLabel("���ݿ����ͣ�SqlServer2000");//������ǩ��ʵ����
		JLabel labe4 = new JLabel("������Ա���������ϼ�������ȫ");//������ǩ��ʵ����
		public About(){//���췽��
	        setTitle("����");//���ñ���
	 	    Container con=getContentPane();
            con.setLayout(new GridLayout(4,1));//�������񲼾�
            con.add(label);//��ӱ�ǩ
            con.add(labe2);//��ӱ�ǩ
            con.add(labe3);//��ӱ�ǩ
            con.add(labe4);//��ӱ�ǩ
            con.setBackground(Color.green);//��ӱ�����ɫ


	 	    setResizable(false);//���ɸ��Ĵ�С
	 	    setSize(380,220);//���ô�С
	 	    setVisible(true);//�ɼ�
	 	    setClosable(true);//���ÿ��Թر�
			}

	}
