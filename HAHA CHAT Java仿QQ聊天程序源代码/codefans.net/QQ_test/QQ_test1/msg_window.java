package QQ_test1;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.*;
public class msg_window extends JFrame implements ActionListener
{
	private String msg;
	private RecButton confirm_button;
	private JLabel msg_label;
	public msg_window(String m)
	{
		msg=m;
		setTitle("HAHA CHAT消息窗口");
		setSize(300,150);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		
		msg_label=new JLabel("<html> <body align=center>MSG:"+msg+"</body></html>",SwingConstants.CENTER);
		msg_label.setForeground(Color.green);
		msg_label.setFont(new Font("宋体",Font.HANGING_BASELINE,18));
		
		Icon confirm_image=new ImageIcon("pic\\confirm_window.jpg");
		JLabel confirm_pic=new JLabel();
		confirm_pic.setIcon(confirm_image);
		confirm_pic.setBounds(new Rectangle(0,0,300,200));
		msg_label.setBounds(new Rectangle(10,10,280,60));
		confirm_button=new RecButton("确定");
		confirm_button.setForeground(Color.green);
		confirm_button.addActionListener(this);
		confirm_button.setBounds(new Rectangle(110,80,70,30));
		
		
		c.add(msg_label);
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
		msg_window msg_win=new msg_window("hahatttttttttttt<br>tttttttttthha");
		msg_win.setVisible(true);
	}
}
