package QQ_test1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	/*
	 * JFrame的模块化程序
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
		
		setTitle("HAHA CHAT 邮箱设置");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,200);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		
		top_label=new JLabel("<html>请填写正确的邮箱，并确保邮箱的安全性，系统会将您特殊号码的随机密码发送到您的邮箱中</html>");	//如果标签内容超过一行，会自动换行
		top_label.setForeground(Color.red);		//设置字体颜色
		top_label.setFont(new Font("宋体",Font.BOLD,16));
		
		mail_txt=new JTextField();
		mail_txt.setFont(new Font("宋体",Font.BOLD,16));	//所输入字体的属性，字体名称，字体类型，字体大小
		
		send_button=new RecButton("确认");
		send_button.setForeground(Color.green);				//字体颜色
		send_button.setFont(new Font("宋体",Font.BOLD,20));		//字体类型
		send_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		//设置鼠标形状
		send_button.addActionListener(this);
		
		top_label.setBounds(new Rectangle(20,20,260,80));	
		mail_txt.setBounds(new Rectangle(20, 100,260,22));
		send_button.setBounds(new Rectangle(100,130,80,30));
		
		c.add(top_label);c.add(send_button);c.add(mail_txt);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("确认"))
		{
			if(mail_txt.getText().indexOf("@")!=-1)
			{
				send_mail send=new send_mail(num,pwd,mail_txt.getText());
				JOptionPane.showMessageDialog(null,"系统已经发送邮件，请及时查收","提示",1);
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"邮箱格式不正确","警告",0);
			}
		}
	}	
	public static void main(String args[])
	{
		set_mail m=new set_mail("152","sdfasdf");
		m.setVisible(true);
	}
}

