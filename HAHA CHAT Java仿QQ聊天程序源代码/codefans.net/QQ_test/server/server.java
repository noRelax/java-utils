package server;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class server 
{
	public static final int PORT1 = 8080;
	public static final int PORT2 = 8081;
	public static int num_length=4;				//号码长度
	public static int num_size=0;				//号码发放数
	public static double num_rate=0;			//号码发放率
	
	public static int online_num=0;				//当前在线数
	public static int regist_num=0;				//当前注册数
	public static double online_rate=0;			//当前在线率

	private TrayIcon trayIcon = null; // 托盘图标
	private SystemTray tray = null; // 本操作系统托盘的实例
	private ArrayList<OneClient> clients=new ArrayList<OneClient>(); 
	private ArrayList<String> arrayLists=new ArrayList<String>();
//	private String path="C:\\Documents and Settings\\HP\\桌面\\Face\\";
	private database db; 
	private JTextField num_size_txt,num_rate_txt,online_num_txt,online_rate_txt,num_length_txt,special_num_txt;
	public static void main(String[] args)
	{
		new server();
	}
	public server()
	{
		new ServerListener();
		new ClientListener();
		new ServerWindow().setVisible(true);
	}
	private class ServerWindow extends JFrame implements ActionListener
	{
		//private String qq;
		private JLabel top_label,num_size_label,num_rate_label,online_num_label,online_rate_label
						,set_num_length_label,set_special_num_label,send_msg_label;
		private JTextArea msg_area;
		private RecButton num_size_button,set_special_num_button,send_msg_button,cancle_msg_button;
		private Icon image;
		private JComboBox num_length_option;
		private JLabel center1,center2,center3,center4,center5,center6,center7,center8,center9,center10,
						left1,left2,left3,left4,right1,right2,right3,right4,left,right;
		public ServerWindow()
		{
			setTitle("HAHA_CHAT服务器窗口");
		    if (SystemTray.isSupported()) 
		    { // 如果操作系统支持托盘
				  tray();
		    }
		    addWindowListener(new WindowAdapter()
		    {
		        public void windowClosing(WindowEvent e) {
		         System.exit(0);
		        }
		       public void windowIconified(WindowEvent e) {    
		         try {
		          tray.add(trayIcon); // 将托盘图标添加到系统的托盘实例中
		          setVisible(false); // 使窗口不可视
		         } catch (AWTException ex) {
		          ex.printStackTrace();
		         }
		        }   
		    });
			setSize(250,550);
			setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
			try
	    	{
	    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    	}catch(Exception e){e.printStackTrace();}
	    	
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c=getContentPane();
			c.setLayout(null);
			c.setBackground(Color.black);
			
			top_label=new JLabel();
			num_size_label=new JLabel("发放号码数：");
			num_rate_label=new JLabel("号码发放率：");
			online_num_label=new JLabel("当前在线数：");
			online_rate_label=new JLabel("当前在线率：");
			set_num_length_label=new JLabel("设定用户免费注册号码位数");
			set_special_num_label=new JLabel("添加特殊号码,该号码将被预留");
			send_msg_label=new JLabel("发送系统消息");
			
			num_size_label.setForeground(Color.green);
			num_rate_label.setForeground(Color.green);
			online_num_label.setForeground(Color.green);
			online_rate_label.setForeground(Color.green);
			set_num_length_label.setForeground(Color.red);
			set_special_num_label.setForeground(Color.red);
			send_msg_label.setForeground(Color.red);
			
			num_size_label.setFont(new Font("宋体",Font.PLAIN,14));
			num_size_label.setFont(new Font("宋体",Font.PLAIN,14));
			online_num_label.setFont(new Font("宋体",Font.PLAIN,14));
			num_rate_label.setFont(new Font("宋体",Font.PLAIN,14));
			online_rate_label.setFont(new Font("宋体",Font.PLAIN,14));
			set_num_length_label.setFont(new Font("宋体",Font.PLAIN,14));
			set_special_num_label.setFont(new Font("宋体",Font.PLAIN,14));
			send_msg_label.setFont(new Font("宋体",Font.PLAIN,14));
			
			image=new ImageIcon("server_pic\\server0.gif"); 
			top_label.setIcon(image);
			
			num_size_txt=new JTextField(50);
			num_rate_txt=new JTextField(50);
			online_num_txt=new JTextField(50);
			online_rate_txt=new JTextField(50);
			num_length_txt=new JTextField(50);
			special_num_txt=new JTextField(50);
			special_num_txt.addMouseListener(new MouseAdapter() {
					     public void mouseClicked(MouseEvent e) 
					     {
					         if (e.getClickCount() == 1) 
					         {
					        	 special_num_txt.setText("");
					         }
					     }
					     public void mouseExited(MouseEvent e) 
					     {
					    	 if(special_num_txt.getText().equals(""))
					    		 special_num_txt.setText("请输入特殊号码");
					     }
			} );
			msg_area=new JTextArea();
			msg_area.setLineWrap(true);
		    JScrollPane scroll=new JScrollPane(msg_area);
		    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
		    num_size_txt.setEnabled(false);
			num_rate_txt.setEnabled(false);
			online_num_txt.setEnabled(false);
			online_rate_txt.setEnabled(false);
			
			num_size_txt.setText(num_size+"");
			num_rate_txt.setText(num_rate+"%");
			online_num_txt.setText(online_num+"");
			online_rate_txt.setText(online_rate+"%");
			num_length_txt.setText(num_length+"");
			special_num_txt.setText("请输入特殊号码");
			
			num_size_button=new RecButton("设定");
			set_special_num_button=new RecButton("提交");
			send_msg_button=new RecButton("发送");
			cancle_msg_button=new RecButton("取消");
			
			num_size_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			set_special_num_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			send_msg_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cancle_msg_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			num_size_button.addActionListener(this);
			set_special_num_button.addActionListener(this);
			send_msg_button.addActionListener(this);
			cancle_msg_button.addActionListener(this);
			
			num_size_button.setForeground(Color.green);
			send_msg_button.setForeground(Color.green);
			set_special_num_button.setForeground(Color.green);
			cancle_msg_button.setForeground(Color.green);
			
			num_length_option=new JComboBox();
			num_length_option.addItem("4");
			num_length_option.addItem("5");
			num_length_option.addItem("6");
			num_length_option.addItem("7");
			
			center1=new JLabel();	image=new ImageIcon("server_pic\\server_top.jpg"); center1.setIcon(image);
			center2=new JLabel();	image=new ImageIcon("server_pic\\server_bottom.jpg"); center2.setIcon(image);
			center3=new JLabel();	image=new ImageIcon("server_pic\\server_top.jpg"); center3.setIcon(image);
			center4=new JLabel();	image=new ImageIcon("server_pic\\server_bottom.jpg"); center4.setIcon(image);
			center5=new JLabel();	image=new ImageIcon("server_pic\\server_top.jpg"); center5.setIcon(image);
			center6=new JLabel();	image=new ImageIcon("server_pic\\server_bottom.jpg"); center6.setIcon(image);
			center7=new JLabel();	image=new ImageIcon("server_pic\\server_top.jpg"); center7.setIcon(image);
			center8=new JLabel();	image=new ImageIcon("server_pic\\server_bottom.jpg"); center8.setIcon(image);
			center9=new JLabel();	image=new ImageIcon("server_pic\\server_top.jpg"); center9.setIcon(image);
			center10=new JLabel();	image=new ImageIcon("server_pic\\server_bottom.jpg"); center10.setIcon(image);
			left=new JLabel();		image=new ImageIcon("server_pic\\server_left.jpg"); left.setIcon(image);
			left1=new JLabel();		image=new ImageIcon("server_pic\\server_left.jpg"); left1.setIcon(image);
			left2=new JLabel();		image=new ImageIcon("server_pic\\server_left.jpg"); left2.setIcon(image);
			left3=new JLabel();		image=new ImageIcon("server_pic\\server_left.jpg"); left3.setIcon(image);
			left4=new JLabel();		image=new ImageIcon("server_pic\\server_left.jpg"); left4.setIcon(image);
			right=new JLabel();		image=new ImageIcon("server_pic\\server_right.jpg"); right.setIcon(image);
			right1=new JLabel();	image=new ImageIcon("server_pic\\server_right.jpg"); right1.setIcon(image);
			right2=new JLabel();	image=new ImageIcon("server_pic\\server_right.jpg"); right2.setIcon(image);
			right3=new JLabel();	image=new ImageIcon("server_pic\\server_right.jpg"); right3.setIcon(image);
			right4=new JLabel();	image=new ImageIcon("server_pic\\server_right.jpg"); right4.setIcon(image);
			
			Component strut01=Box.createVerticalStrut(5);
			strut01.setForeground(Color.red);
			strut01.setBackground(Color.red);
			top_label.setBounds(new Rectangle(20,20,200,80));
			center9.setBounds(new Rectangle(10,100,222,3));
			left.setBounds(new Rectangle(10,100,3,410));
			center1.setBounds(new Rectangle(15,105,210,3));
			left1.setBounds(new Rectangle(15,105,3,104));
			num_size_label.setBounds(new Rectangle(20,110,100,20));
			num_size_txt.setBounds(new Rectangle(120,110,100,20));
			num_rate_label.setBounds(new Rectangle(20,135,100,20));
			num_rate_txt.setBounds(new Rectangle(120,135,100,20));
			online_num_label.setBounds(new Rectangle(20,160,100,20));
			online_num_txt.setBounds(new Rectangle(120,160,100,20));
			online_rate_label.setBounds(new Rectangle(20,185,100,20));
			online_rate_txt.setBounds(new Rectangle(120,185,100,20));
			right1.setBounds(new Rectangle(222,105,3,104));
			center2.setBounds(new Rectangle(15,207,210,3));
			center3.setBounds(new Rectangle(15,218,210,3));
			left2.setBounds(new Rectangle(15,218,3,54));
			set_num_length_label.setBounds(new Rectangle(40,220,170,20));
			num_length_option.setBounds(new Rectangle(70,250,40,20));
			num_size_button.setBounds(new Rectangle(120,250,60,20));
			right2.setBounds(new Rectangle(222,220,3,54));
			center4.setBounds(new Rectangle(15,272,210,3));
			center5.setBounds(new Rectangle(15,283,210,3));
			left3.setBounds(new Rectangle(15,283,3,55));
			set_special_num_label.setBounds(new Rectangle(30,285,200,20));
			special_num_txt.setBounds(new Rectangle(50,315,90,20));
			set_special_num_button.setBounds(new Rectangle(150,315,60,20));
			right3.setBounds(new Rectangle(222,285,3,55));
			center6.setBounds(new Rectangle(15,337,210,3));
			center7.setBounds(new Rectangle(15,348,210,3));
			left4.setBounds(new Rectangle(15,348,3,154));
			send_msg_label.setBounds(new Rectangle(80,350,100,20));
			scroll.setBounds(new Rectangle(20,375,200,100));
			cancle_msg_button.setBounds(new Rectangle(60,480,60,20));
			send_msg_button.setBounds(new Rectangle(130,480,60,20));
			right4.setBounds(new Rectangle(222,348,3,154));
			center8.setBounds(new Rectangle(15,502,210,3));
			right.setBounds(new Rectangle(227,100,3,410));
			center10.setBounds(new Rectangle(10,507,220,3));
			
			c.add(top_label);
			c.add(center9);
			c.add(left);
			c.add(center1);
			c.add(left1);
			c.add(num_size_label);
			c.add(num_size_txt);			
			c.add(num_rate_label);
			c.add(num_rate_txt );
			c.add(online_num_label);
			c.add(online_num_txt );
			c.add(online_rate_label);
			c.add(online_rate_txt );
			c.add(right1);
			c.add(center2);
			c.add(center3);
			c.add(left2);
			c.add(set_num_length_label);
			c.add(num_length_option);
			c.add(num_size_button);
			c.add(right2);
			c.add(center4);
			c.add(center5);
			c.add(left3);
			c.add(set_special_num_label);
			c.add(special_num_txt);
			c.add(set_special_num_button);
			c.add(right3);
			c.add(center6);
			c.add(center7);
			c.add(left4);
			c.add(send_msg_label);	
			c.add(scroll);	
			c.add(cancle_msg_button);	
			c.add(send_msg_button);	
			c.add(right4);
			c.add(center8);
			c.add(right);
			c.add(center10);
			number_ini();
		}
		private void tray() {

			   tray = SystemTray.getSystemTray(); // 获得本操作系统托盘的实例
			   ImageIcon icon = new ImageIcon("pic//server.gif"); // 将要显示到托盘中的图标

			   PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单
			   MenuItem show = new MenuItem("打开程序(s)");
			   MenuItem exit = new MenuItem("退出程序(x)");
			   pop.add(show);
			   pop.add(exit);
			   trayIcon = new TrayIcon(icon.getImage(), "HAHA-CHAT 服务器", pop);

			   trayIcon.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
			     if (e.getClickCount() == 2) { // 鼠标双击
			      tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
			      setExtendedState(JFrame.NORMAL);
			      setVisible(true); // 显示窗口
			      toFront();     
			     }
			    }
			   });
			   show.addActionListener(new ActionListener() { // 点击“显示窗口”菜单后将窗口显示出来
			      public void actionPerformed(ActionEvent e) {
			       tray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
			       setExtendedState(JFrame.NORMAL);
			       setVisible(true); // 显示窗口
			       toFront();
			      }
			     });
			   exit.addActionListener(new ActionListener() { // 点击“退出演示”菜单后退出程序
			      public void actionPerformed(ActionEvent e) {
			       System.exit(0); // 退出程序
			      }
			     });

			}
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("设定")) 
			{
				System.out.println(num_length_option.getSelectedItem());
				String temp_str=(String)num_length_option.getSelectedItem();
				if(temp_str.equals("4"))
				{
					num_length=4;
					JOptionPane.showMessageDialog(null, "设定成功");
				}
				else if(temp_str.equals("5"))
				{
					num_length=5;
					JOptionPane.showMessageDialog(null, "设定成功");
				}
				else if(temp_str.equals("6"))
				{
					num_length=6;
					JOptionPane.showMessageDialog(null, "设定成功");
				}
				else if(temp_str.equals("7"))
				{
					num_length=7;
					JOptionPane.showMessageDialog(null, "设定成功");
				}
			}
			else if(e.getActionCommand().equals("提交"))   
			{
				String temp_str=special_num_txt.getText();
				try{Integer.parseInt(temp_str);}
				catch(Exception e2){JOptionPane.showMessageDialog(null, "号码必须为整数，请重新输入");return;}
				add_special_num(temp_str);
			}
			else if(e.getActionCommand().equals("发送"))  
			{
				String temp_str=msg_area.getText();
				if(!temp_str.equals(""))
					send_msg(temp_str);
				else
				{
					JOptionPane.showMessageDialog(null, "发送内容不能为空，请重新输入");return;
				}
				
			}
			else if(e.getActionCommand().equals("取消"))
			{
				msg_area.setText("");
			}
		}
		public void add_special_num(String n)				//添加特殊号码
		{
			db=new database();
			db.connect();
			String sql="insert into special_num(id,price) values('"+n+"','1000')";
			try{
				db.update(sql);
				db.close();
				JOptionPane.showMessageDialog(null,"添加特殊号码成功");
			}catch(Exception e){JOptionPane.showMessageDialog(null,"添加特殊号码失败");}
		}
		public void send_msg(String temp_str)				//发送系统消息
		{
			database db=new database();
			db.connect();
			String sql="select id,nickname from user1";
			try{
				db.select(sql);
				while(db.rs.next())
				{
					database db2=new database();
					db2.connect();
					String sql2="insert into message(from_id,to_id,from_name,to_name,msg) " +
							"values('0000','"+db.rs.getString("id").trim()+"','system','"+
							db.rs.getString("nickname").trim()+"','"+temp_str+"')";
					System.out.println(sql2);
					db2.update(sql2);
					db2.close();
				}
				db.close();
				JOptionPane.showMessageDialog(null,"发送系统消息成功");
			}catch(Exception e){e.printStackTrace();JOptionPane.showMessageDialog(null,"发送系统消息失败");}
		}
		public void get_regist_num()
		{
			database db=new database();
			db.connect();
			String sql="select distinct id from user1";
			db.select(sql);
			try{
				while(db.rs.next())
				{
					regist_num++;
				}
			}catch(Exception e){JOptionPane.showMessageDialog(null,"添加特殊号码失败");}
		}
		public void get_num_size()
		{
			num_size=(int) Math.pow(10,num_length)-1000;
			db=new database();
			db.connect();
			String sql="select distinct id from special_num";
			db.select(sql);
			try{
				while(db.rs.next())
				{
					num_size--;
				}
			}catch(Exception e){JOptionPane.showMessageDialog(null,"查找特殊号码失败");}
		}
		public void number_ini()			//设置所有静态初始数据
		{
			get_regist_num();
			get_num_size();
			num_rate=((regist_num*10000)/num_size)/100.0;
			num_size_txt.setText(num_size+"");
			num_rate_txt.setText(num_rate+"%");
		}
	}
	private class ServerListener extends Thread   //负责接收用户查询数据库的语句
	{
		ServerSocket s;
		public ServerListener()
		{
			try
			{
				s = new ServerSocket(PORT1);
				
				start();
			}catch (Exception e ){}
		}
		public void run()
		{
			try 
			{
				while(true) 
				{
					Socket socket = s.accept();
					try
					{
						BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
						String str="",return_msg="";
						int count=Integer.parseInt(in.readLine());
						str=in.readLine();
						System.out.println(str);
						if(str.charAt(0)=='s'||str.charAt(0)=='S')
						{
							db=new database();
							db.connect();
							try{
								db.select(str);
								while(db.rs.next())
								{
									for(int i=1;i<=count;i++)
									{
										String s=db.rs.getString(i).trim();
										if(s==null)
											return_msg+="null~";
										else
											return_msg+=s.trim()+"~";
									}
								}
								db.close();
							}catch(Exception e){System.out.println("server端数据库连接失败 ");}
							//System.out.println(return_msg);
							out.println(return_msg);
						}
						else if(str.charAt(0)=='u'||str.charAt(0)=='U'||str.charAt(0)=='i'||str.charAt(0)=='I'||
								str.charAt(0)=='d'||str.charAt(0)=='D')//update,insert,delete
						{
							db=new database();
							db.connect();
							try{
								db.update(str);
								db.close();
							}catch(Exception e){out.println("f");}
							out.println("s");
						}

					}catch(Exception e){e.getStackTrace();}
					socket.close();
				}
			}catch (Exception e){}
			finally 
			{
				try 
				{
					s.close();
				}catch (Exception e){}				
			}
		}
	}
	private class ClientListener extends Thread		//用于处理用户操作：上下线，用户聊天端口获取等
	{
		ServerSocket s;
		public ClientListener()
		{
			try
			{
				s = new ServerSocket(PORT2);
				start();
			}catch (Exception e ){}
		}
		public void run()
		{
			try 
			{
				while(true) 
				{
					Socket socket = s.accept();
					try { new OneClient(socket);

					} catch(IOException e) {
						socket.close();
					}
				}
			}catch (Exception e){}
			finally 
			{
				try 
				{
					s.close();
				}catch (Exception e){}				
			}
		}
	}
	public void processMessage(OneClient sender,String data)
	{
		/*
		 * data为用户发送的命令。前两个字符为命令字:rl----用户登录命令
		 * 例：rl5003haha%2502hh$null&2000
		 */
		String command=data.substring(0, 2);				//存放用户命令
		String message=data.substring(2);					//
		try
		{
        if(command.equals("mi"))							//主界面登录
		{
			login(sender,message);
			online_num+=1;
			online_num_txt.setText(online_num+"");
			online_rate=((online_num*10000)/regist_num)/100.0;
			online_rate_txt.setText(online_rate+"%");
			
        	System.out.println("main on-------------------------------------------------------------");
        	for(OneClient client : clients)
			{
        		System.out.println(client.account_from+"\t"+client.account_to+"\t"+client.name_from+"\t"+client.name_to);
			}
        	System.out.println("main on-------------------------------------------------------------");
			//System.out.println("main  in-------------------------------------------------------------");

		}
        else if(command.equals("ci"))							//聊天框登录
		{
			login(sender,message);
        	System.out.println("chat on-------------------------------------------------------------");
        	for(OneClient client : clients)
			{
        		System.out.println(client.account_from+"\t"+client.account_to+"\t"+client.name_from+"\t"+client.name_to);
			}
        	System.out.println("chat on-------------------------------------------------------------");
			//System.out.println("chat in-------------------------------------------------------------");
		}
        else if(command.equals("mo"))							//主界面下线
		{
        	for(OneClient client : clients)
			{
				if(client.account_from.equals(sender.account_from)&&
						client.account_to.equals(sender.account_to))
				{
					clients.remove(client);
					break;
				}
			}
			online_num-=1;
			online_num_txt.setText(online_num+"");
			online_rate=((online_num*10000)/regist_num)/100.0;
			online_rate_txt.setText(online_rate+"%");
			
        	System.out.println("main off-------------------------------------------------------------");
        	for(OneClient client : clients)
			{
        		System.out.println(client.account_from+"\t"+client.account_to+"\t"+client.name_from+"\t"+client.name_to);
			}
        	System.out.println("main off-------------------------------------------------------------");
		}
        else if(command.equals("co"))							//聊天框关闭
		{
        	for(OneClient client : clients)
			{
				if(client.account_from.equals(sender.account_from)&&
						client.account_to.equals(sender.account_to))
				{
					clients.remove(client);
					break;
				}
			}
        	
        	System.out.println("chat off-------------------------------------------------------------");
        	for(OneClient client : clients)
			{
        		System.out.println(client.account_from+"\t"+client.account_to+"\t"+client.name_from+"\t"+client.name_to);
			}
        	System.out.println("chat off-------------------------------------------------------------");
		}
        else if(command.equals("to"))						//客户端查询接收方是否在线
		{
			String a,b;
			a=message.substring(0,4);		//发送方号码
			b=message.substring(4,8);		//接收方号码
			//System.out.println("server--processMessage::  from:"+a+"to:"+b);
			boolean found=false;
			for(OneClient client : clients)
			{	
				//System.out.println("account_from"+client.account_from+"   account_to"+client.account_to);
				if(client.account_from.equals(b)&&client.account_to.equals(a))
				{
					System.out.println("server--找到接收方::to"+client.account_to+client.address+"$"+client.port);
					sender.sendData("to"+client.account_to+client.address+"$"+client.port);
					found=true;
					break;
				}
			}
			if(!found)
			{
				System.out.println("server--没有找到接收方");
				sender.sendData("to"+"notfound");
			}
		}
        else if(command.equals("rg"))         
        {
        	regist_num++;
        	num_rate=(regist_num*10000/num_size)/100.0;
        	num_rate_txt.setText(num_rate+"%");
        	online_rate=(online_num*10000/regist_num)/100.0;
        	online_rate_txt.setText(online_rate+"%");
        }
		}catch(Exception e){e.printStackTrace();}
	}
	private void login(OneClient sender,String message)
	{
		/*用户登录，message为信息部分,包含:发送方号码、昵称，接收方号码、昵称，ip,端口号，
		 * 
		 * */
		String[] str=new String[6];
		str[0]=message.substring(0,4);
		str[1]=message.substring(4, message.indexOf("%"));
		str[2]=message.substring(message.indexOf("%")+1,message.indexOf("%")+5);
		str[3]=message.substring(message.indexOf("%")+5,message.indexOf("$"));
		str[4]=message.substring(message.indexOf("$")+1,message.indexOf("&"));
		str[5]=message.substring(message.indexOf("&")+1);
			sender.name_from=str[1];
			sender.account_from=str[0];
			sender.name_to=str[3];
			sender.account_to=str[2];
			sender.address=str[4];
			sender.port=str[5];
			clients.add(sender);
	}
	private boolean whetherLogin(String account)
	{
		boolean b=false;
		for(OneClient client : clients)
			if(account.equals(client.account_from))
			{b=true;break;}
		return b;
	}
	private class OneClient extends Thread 
	{
		private Socket socket;
    	private BufferedReader in;
    	private PrintWriter out;
    	public String name_from;
    	public String account_from;
    	public String name_to;
    	public String account_to;
    	public String address;
    	public String port;
    	public OneClient(Socket s) throws IOException
    	{
    		socket = s;
    		in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		out =new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    		start();
    	}
    	public void sendData(String data)
    	{
    		System.out.println("Server::sendData---"+data);
    		out.println(data);
    	}
    	public void run() 
    	{
    		try
    		{
    			while(true)
    			{
    			String str = in.readLine();
    			System.out.println("server接收到客户端操作语句:"+str);
				processMessage(this, str);
    			}
    		}catch(Exception e){}
    	}
	}
}
