package QQ_test1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

import javax.swing.*;

public class login  extends JFrame implements ActionListener
{
	public static String ip="121.250.221.182";
	private JPanel username_panel,buttonpanel;
	private JTextField haha_num;
	private JTextField ip_set;
	private JPasswordField password;
	private JLabel username_label,password_label;
	//public static final int PORT1 = 8080;
	msg_sender send;
	public login()
	{
		setTitle("haha聊天登录窗口");
		setSize(300,190);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}*/
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		Icon login_image=new ImageIcon("pic\\login_face.jpg");
		JLabel login_pic=new JLabel();
		login_pic.setIcon(login_image);
		login_pic.setBounds(new Rectangle(0,-20,300,80));
		
		Icon login_image2=new ImageIcon("pic\\login_face2.jpg");
		JLabel login_pic2=new JLabel();
		login_pic2.setIcon(login_image2);
		login_pic2.setBounds(new Rectangle(0,109,300,80));
		
		username_panel=new JPanel();	
		username_panel.setBackground(Color.black);
		buttonpanel=new JPanel();		
		buttonpanel.setBackground(Color.black);
		
		username_panel.setLayout(null);
		
		haha_num=new JTextField(5);
		ip_set=new JTextField(5);
		password=new JPasswordField(5);
		password.setEchoChar('*');
		
		username_label=new JLabel("HAHA账号：");
		password_label=new JLabel("HAHA密码：");
		
		username_label.setForeground(Color.green);
		password_label.setForeground(Color.green);
		
		RecButton login_button=new RecButton("登录");
		this.getRootPane().setDefaultButton(login_button); // 默认回车按钮 
		login_button.setForeground(Color.green);
		login_button.addActionListener(this);
		login_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		login_button.addKeyListener(new KeyAdapter(){ 
			public void keyPressed(KeyEvent ke){ 
			if(ke.getKeyChar() == ke.VK_ENTER){ 
				user_login(); 
			} } });
		
		RecButton exit_button=new RecButton("退出");
		exit_button.setForeground(Color.green);
		exit_button.addActionListener(this);
		exit_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit_button.addKeyListener(new KeyAdapter(){ 
			public void keyPressed(KeyEvent ke){ 
			if(ke.getKeyChar() == ke.VK_ENTER){ 
				dispose(); 
			} } });
		RecButton ip_set_button=new RecButton("设置ip");
		ip_set_button.setForeground(Color.green);
		ip_set_button.addActionListener(this);
		ip_set_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		RecButton register_button=new RecButton("注册");
		register_button.setForeground(Color.green);
		register_button.addActionListener(this);
		register_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		register_button.addKeyListener(new KeyAdapter(){ 
			public void keyPressed(KeyEvent ke){ 
			if(ke.getKeyChar() == ke.VK_ENTER){ 
				register2 reg=new register2();
				dispose();
				reg.setVisible(true); 
			} } });
		
	    username_label.setBounds(new Rectangle(5, 5,70, 20));
	    password_label.setBounds(new Rectangle(5, 35,70, 20));
	    haha_num.setBounds(new Rectangle(75, 5,150, 20));
	    password.setBounds(new Rectangle(75, 35,150, 20));
	    
	    login_button.setBounds(new Rectangle(45, 130,70, 20));
	    exit_button.setBounds(new Rectangle(120, 130,70, 20));
	    register_button.setBounds(new Rectangle(195, 130,70, 20));
	    
		buttonpanel.add(login_button);
		buttonpanel.add(exit_button);
		buttonpanel.add(register_button);
		username_panel.add(username_label);
		username_panel.add(haha_num);
		username_panel.add(password_label);
		username_panel.add(password);
		username_panel.setBounds(new Rectangle(25,35,280, 70));
		buttonpanel.setBounds(new Rectangle(5, 100,280, 40));		
		ip_set.setBounds(new Rectangle(5, 170,170, 20));
		ip_set_button.setBounds(new Rectangle(190, 170,70, 20));
		
		c.add(username_panel);
		c.add(buttonpanel);
		c.add(login_pic);
		c.add(login_pic2);
		c.add(ip_set);
		c.add(ip_set_button);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("登录"))
		{
			user_login();			
		}
		else if(e.getActionCommand().equals("退出"))
		{
			System.exit(0);
		}
		else if(e.getActionCommand().equals("注册"))
		{
			zhuce reg=new zhuce();
			dispose();
			reg.setVisible(true);
		}
		else if(e.getActionCommand().equals("设置ip"))
		{
			login.ip=ip_set.getText();
		}
		
	}
public void user_login()
{
	String num=haha_num.getText();
	String sql="select password from user1 where id='"+num+"'";
	try{
		send=new msg_sender(1,sql);
		String msg=send.r_msg();
		StringTokenizer str=new StringTokenizer(msg,"~");
		String psw;
		if(str.hasMoreTokens())
			psw=str.nextToken().trim();
		else
			{JOptionPane.showMessageDialog(null, "登录失败，用户名不存在");return;}
		
		String psw2=password.getText();
		if(psw.equals(psw2))//===========================================成功进入主界面
		{
			dispose();
			main m=new main(num);
			m.setVisible(true);
		}//===============================================================成功进入主界面
		else
			JOptionPane.showMessageDialog(null, "登录失败，用户名或密码错误");
		
	}catch(Exception e2){System.out.println(e2.getStackTrace());}
}
	public static void main(String args[])
	{
		login log=new login();
		log.setVisible(true);
	}
}
