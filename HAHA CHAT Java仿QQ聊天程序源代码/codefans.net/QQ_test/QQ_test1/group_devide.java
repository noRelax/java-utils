package QQ_test1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class group_devide extends JFrame implements ActionListener{
	private String group_name,num,friend_num;
	private RecButton confirm_button;
	private JLabel top_label,group_label;
	private JComboBox group_option;
	public group_devide(String name,String num1,String num2)
	{
		group_name=name;		//分组名称
		num=num1;				//自己号码
		friend_num=num2;		//好友号码
		setTitle("HAHA CHAT添加好友窗口");
		setSize(300,150);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}
    	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		
		group_label=new JLabel("添加好友：");
		group_label.setForeground(Color.green);
		group_label.setFont(new Font("宋体",Font.HANGING_BASELINE,18));
		
		group_label=new JLabel("请选择分组名称：");
		group_label.setForeground(Color.green);
		group_label.setFont(new Font("宋体",Font.HANGING_BASELINE,18));
		
		Icon confirm_image=new ImageIcon("pic\\confirm_window.jpg");
		JLabel confirm_pic=new JLabel();
		confirm_pic.setIcon(confirm_image);
		confirm_pic.setBounds(new Rectangle(0,0,300,200));
		group_label.setBounds(new Rectangle(10,10,280,60));
		confirm_button=new RecButton("确定");
		confirm_button.setForeground(Color.green);
		confirm_button.addActionListener(this);
		confirm_button.setBounds(new Rectangle(110,80,70,30));
		
		
		c.add(group_label);
		c.add(confirm_button);
		c.add(confirm_pic);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("确定"))
		{
			dispose();
		}
	}
	public static void main (String args[])
	{
		group_devide msg_win=new group_devide("","","");
		msg_win.setVisible(true);
	}
}
