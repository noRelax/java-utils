package QQ_test1;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.StringTokenizer;

import javax.swing.*;

public class pop_inf extends JWindow
{
	private JLabel sex_label,name_label,personal_sign_label,haha_zone_label1,haha_zone_label2,
			province_label,region_label;
	String nick_name="",num="",personal_sign="",province="",region="",entry_year="",sex="";
	private Icon image;
	private String sql="";
	private msg_sender send;
	public pop_inf(String n,int x,int y)
	{
		num=n;
		setSize(270,240);
		setBackground(Color.BLACK);
		this.setLocation(x-272, y-242);            //此行将窗口显示在屏幕中心
		addWindowListener(new WindowAdapter()
	    {
			public void windowActivated(WindowEvent e){}
			public void windowDeactivated(WindowEvent e)		//当窗口不活跃的时候，自动关闭该窗口
			{
				dispose();
			}
	    });
		try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		
		sex_label=new JLabel();
		name_label=new JLabel();
		personal_sign_label=new JLabel();
		haha_zone_label1=new JLabel("QQ空间");
		haha_zone_label2=new JLabel("");
		province_label=new JLabel("");
		region_label=new JLabel("");
		
		personal_sign_label.setFont(new Font("宋体",Font.PLAIN,12));
		province_label.setFont(new Font("宋体",Font.BOLD,15));
		region_label.setFont(new Font("宋体",Font.BOLD,15));
		
		sex_label.setForeground(Color.green);
		name_label.setForeground(Color.green);
		personal_sign_label.setForeground(Color.green);
		haha_zone_label1.setForeground(Color.green);
		haha_zone_label2.setForeground(Color.green);
		personal_sign_label.setBorder(BorderFactory.createBevelBorder(0));
		province_label.setForeground(Color.green);
		region_label.setForeground(Color.green);
		
		image=new ImageIcon("pic\\haha_zone_pop_inf2.jpg");
		haha_zone_label1.setIcon(image);
		image=new ImageIcon("pic\\haha_zone.jpg");
		haha_zone_label2.setIcon(image);
		
		sex_label.setBounds(new Rectangle(5,5,140,226));
		name_label.setBounds(new Rectangle(150,10,70,20));
		personal_sign_label.setBounds(new Rectangle(150,40,115,60));
		province_label.setBounds(new Rectangle(150,100,50,30));
		region_label.setBounds(new Rectangle(150,130,115,30));
		haha_zone_label1.setBounds(new Rectangle(150,170,115,30));
		haha_zone_label2.setBounds(new Rectangle(150,195,50,50));
		ini();
		c.add(sex_label);
		c.add(name_label);
		c.add(personal_sign_label);
		c.add(haha_zone_label1);
		c.add(haha_zone_label2);
		c.add(province_label);
		c.add(region_label);
		//c.add(haha_zone_label);
	}
	public void ini()
	{
		try{
			sql="select nickname,sex,province,region from user1 where id='"+num+"'";
			send=new msg_sender(4,sql);
			String msg=send.r_msg();
			
			StringTokenizer str=new StringTokenizer(msg,"~");
			name_label.setText(str.nextToken()+"("+num+")");
			sex=str.nextToken();
			//personal_sign_label.setText("<html>"+str.nextToken()+"</html>");
			province_label.setText(str.nextToken());
			region_label.setText(str.nextToken());
			if(sex.equals("男"))
			{
				image=new ImageIcon("people\\man.jpg");
				sex_label.setIcon(image);
			}
			else
			{
				image=new ImageIcon("people\\woman.jpg");
				sex_label.setIcon(image);
			}
			
			sql="select personal_sign from user_inf where id='"+num+"'";
			send=new msg_sender(1,sql);
			msg=send.r_msg();
			str=new StringTokenizer(msg,"~");
			personal_sign_label.setText("<html>"+str.nextToken()+"</html>");
		}catch(Exception e2){System.out.println("消息写入数据库错误");}
	}
	public static void main(String args[])
	{
		pop_inf pop=new pop_inf("6263",202,200);
		pop.setVisible(true);
	}
}
