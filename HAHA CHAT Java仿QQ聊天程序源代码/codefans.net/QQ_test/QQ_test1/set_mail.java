package QQ_test1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	/*
	 * JFrame��ģ�黯����
	 * 
	 * */
public class set_mail extends JDialog implements ActionListener
{
	JLabel top_label;
	JTextField mail_txt;
	RecButton send_button;
	String num="",pwd="";
	public set_mail(String n,String p)
	{
		num=n;
		pwd=p;
		
		setTitle("HAHA CHAT ��������");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,200);
		setLocationRelativeTo(null);            //���н�������ʾ����Ļ����
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		
		top_label=new JLabel("<html>����д��ȷ�����䣬��ȷ������İ�ȫ�ԣ�ϵͳ�Ὣ����������������뷢�͵�����������</html>");	//�����ǩ���ݳ���һ�У����Զ�����
		top_label.setForeground(Color.red);		//����������ɫ
		top_label.setFont(new Font("����",Font.BOLD,16));
		
		mail_txt=new JTextField();
		mail_txt.setFont(new Font("����",Font.BOLD,16));	//��������������ԣ��������ƣ��������ͣ������С
		
		send_button=new RecButton("ȷ��");
		send_button.setForeground(Color.green);				//������ɫ
		send_button.setFont(new Font("����",Font.BOLD,20));		//��������
		send_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		//���������״
		send_button.addActionListener(this);
		
		top_label.setBounds(new Rectangle(20,20,260,80));	
		mail_txt.setBounds(new Rectangle(20, 100,260,22));
		send_button.setBounds(new Rectangle(100,130,80,30));
		
		c.add(top_label);c.add(send_button);c.add(mail_txt);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("ȷ��"))
		{
			if(mail_txt.getText().indexOf("@")!=-1)
			{
				send_mail send=new send_mail(num,pwd,mail_txt.getText());
				JOptionPane.showMessageDialog(null,"ϵͳ�Ѿ������ʼ����뼰ʱ����","��ʾ",1);
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"�����ʽ����ȷ","����",0);
			}
		}
	}	
	public static void main(String args[])
	{
		set_mail m=new set_mail("152","sdfasdf");
		m.setVisible(true);
	}
}

