package QQ_test1;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.*;
public class main extends JFrame implements ActionListener,MouseListener
{
	int n=0;
	private Socket socket;
	private DatagramSocket ds;
	private BufferedReader in;
	private PrintWriter out;
	private int port=2000;
	private String current_friend_info="";   		//当右键点击删除好友时有效，存放被删除好友的label
	public static final int PORT2=8081;
	private PrintWriter outputStream=null;
	private boolean have_msg=false;
	public static JLabel pic;
	public static String nick_name="";
	private String num,head_pic,to_id,to_name;
	private JLabel nickname_label,state_label,msg_label,game_label,send_file_label,add_group_label,music_label,chat_history_label;
	public static JTextField nickname_txt;
	private JPanel friend_panel;
	private RecButton help_button,search_button,haha_zone_button;
	private TrayIcon trayIcon = null; // 托盘图标
	private SystemTray tray = null; // 本操作系统托盘的实例
	private JComboBox state_option;
	private msg_sender send;
	private JPanel cardp;
	private CardLayout card;
	private RecButton group_button[];
	private int selected_button_index=0;
	private JPanel p[];
	private JList list[];
	private Vector inf[];
	private int button_index=0; 
	private Vector friend_vector,friend_group_vector;
	private int current_position_of_list=0,current_list_index;
	private JPopupMenu popupMenu,pop_button_menu; //弹出菜单
	private JMenuItem items[]; //菜单项
	private JMenu change_group_menu;
	private Icon image;
	private pop_inf pop;
	public main(String number)
	{
		num=number;
		setTitle("HAHA CHAT");
	    if (SystemTray.isSupported()) 
	    { // 如果操作系统支持托盘
			  tray();
	    }
	    addWindowListener(new WindowAdapter()
	    {
	    	public void windowClosing(WindowEvent e)
	    	{
	      		send("mo"+num+nick_name+"%"+num+nick_name+"$"+ds.getInetAddress()+"&"+port);
	      		String sql="update user1 set state ='离线' where id='"+num+"'";
	      		try{
	      			send=new msg_sender(0,sql);
	      		}catch(Exception e2){System.out.println("error");}
	      		System.exit(0);
	      	} 
	    	public void windowIconified(WindowEvent e) 
	    	{    
		         try {
		          tray.add(trayIcon); // 将托盘图标添加到系统的托盘实例中
		          setVisible(false); // 使窗口不可视
		         } catch (AWTException ex) {
		          ex.printStackTrace();
		         }
	        }   
	    });
		setSize(200,580);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.blue);
		
		popupMenu=new JPopupMenu(); //实例化弹出菜单
		items=new JMenuItem[4]; //初始化数组
	     
	    items[0]=new JMenuItem("发送消息"); //实例化菜单项
	    items[1]=new JMenuItem("查看资料"); //实例化菜单项
	    items[2]=new JMenuItem("修改备注"); //实例化菜单项
	    items[3]=new JMenuItem("删除好友"); //实例化菜单项
	    change_group_menu=new JMenu("移动好友"); //实例化菜单项
	    change_group_ini();
	    
	    popupMenu.add(items[0]); //增加菜单项到菜单上
	    popupMenu.add(items[1]); //增加菜单项到菜单上
	    popupMenu.add(change_group_menu);
	    popupMenu.add(items[2]); //增加菜单项到菜单上
	    popupMenu.add(items[3]); //增加菜单项到菜单上
	    
	    change_group_menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    items[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    items[1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    items[2].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    items[3].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    
	    
	    items[0].addActionListener(this); //菜单项事件处理
	    items[1].addActionListener(this); //菜单项事件处理
	    items[2].addActionListener(this); //菜单项事件处理
	    items[3].addActionListener(this); //菜单项事件处理
	     
	    pop_button_menu=new JPopupMenu(); //实例化弹出菜单
	    items[0]=new JMenuItem("更改组名"); //实例化菜单项
	    items[1]=new JMenuItem("删除该组"); //实例化菜单项
	    items[2]=new JMenuItem("添加用户"); //实例化菜单项
	    
	    pop_button_menu.add(items[0]); //增加菜单项到菜单上
	    pop_button_menu.add(items[1]); //增加菜单项到菜单上
	    pop_button_menu.add(items[2]); //增加菜单项到菜单上
	    
	    items[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    items[1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    items[2].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    items[0].addActionListener(this); //菜单项事件处理
	    items[1].addActionListener(this); //菜单项事件处理
	    items[2].addActionListener(this); //菜单项事件处理
		//第一部分：个人信息界面==================================================
		
		MouseListener mouseListener = new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		         if (e.getClickCount() == 1) 
		         {
		             set_personal_inf set=new set_personal_inf(num);
		             set.setVisible(true);
		         }
		     }
		 };
	    ini_personal_info();
		pic = new JLabel(); 
		image=new ImageIcon("face\\"+head_pic); 
		pic.setIcon(image);
		pic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pic.setToolTipText("<html>"+nick_name+"("+num+")<br><a href=\"http://www.sina.com\"> http://www.sina.com </a>"+
				"<br><img src=face\\2.JPG align=center>");
		pic.addMouseListener(mouseListener);
		
		nickname_label=new JLabel("昵称: ");
		state_label=new JLabel("状态: ");
		
		nickname_label.setForeground(Color.red);
		state_label.setForeground(Color.red);
		
		nickname_txt=new JTextField(50);
		nickname_txt.setEnabled(false);
		nickname_txt.setText(nick_name);
		
		state_option=new JComboBox();
		state_option.addItem("在线");
		state_option.addItem("隐身");
		state_option.addItem("忙碌");
		state_option.addItem("离线");
		state_option.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent evt){
	      		String sql="update user1 set state ='"+(String)state_option.getSelectedItem()+"' where id='"+num+"'";
	      		try{
	      			send=new msg_sender(0,sql);
	      		}catch(Exception e){System.out.println("error");}
	         }
	        });
		
		pic.setBounds(new Rectangle(20, 10,50,50));
		nickname_label.setBounds(new Rectangle(70, 15,40,20));
		nickname_txt.setBounds(new Rectangle(115, 15,60,20));
		state_label.setBounds(new Rectangle(70, 35,40,20));
		state_option.setBounds(new Rectangle(115, 35,60,20));
		//第一部分：个人信息界面完成===============================================
		
		//第二部分：好友信息界面==================================================
		friend_panel=new JPanel();
		friend_panel.setLayout(null);
		friend_panel.setBackground(Color.cyan);
		friend_panel.setBounds(new Rectangle(25, 70,160,440));
		cardp=new JPanel();
		card=new CardLayout();
		cardp.setBackground(Color.cyan);
     	cardp.setLayout(card);
     	ini_group();
     	ini_friends();
     	cardp.setBounds(new Rectangle(0,20,160,440-button_index*20));
     	current_position_of_list=button_index*20;
     	current_list_index=0;
		//按钮部分
      	for(int i=0;i<n;i++)
      	{
          	Vector v=(Vector)friend_vector.elementAt(i);
    		cardp.add((String)v.elementAt(0),p[i]);
      	}
      	friend_panel.add(cardp);
		card.first(cardp);	
		try
		{
			Vector v=(Vector)friend_vector.elementAt(0);
			card.show(cardp,(String)v.elementAt(0));
	      	for(int i=0;i<n;i++)
	      	{
	          	list[i].addMouseListener(this);
	      	}
		}catch(Exception e){}
		
//		第二部分：好友信息界面结束==================================================
		
		//第三部分：按钮==========================================================

		Icon search_image=new ImageIcon("pic\\search.jpg"); 
		search_button=new RecButton("",search_image);
		search_button.setActionCommand("search");
		search_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		search_button.addActionListener(this);
		search_button.setToolTipText("SEARCH");
		
		help_button=new RecButton("");
		Icon help_image=new ImageIcon("pic\\help.jpg"); 
		help_button.setIcon(help_image);
		help_button.setActionCommand("help");
		help_button.setToolTipText("HELP");
		help_button.addActionListener(this);
		help_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		haha_zone_button=new RecButton("");
		image=new ImageIcon("pic\\haha_zone.jpg"); 
		haha_zone_button.setIcon(image);
		haha_zone_button.setActionCommand("haha_zone");
		haha_zone_button.setToolTipText("HAHA ZONE");
		haha_zone_button.addActionListener(this);
		haha_zone_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		msg_label=new JLabel();
		image=new ImageIcon("pic\\msg1.jpg"); 
		msg_label.setIcon(image);
		msg_label.setToolTipText("MESSAGE");
		msg_label.addMouseListener(new MouseAdapter() 
		{
		     public void mouseClicked(MouseEvent e) 
		     {
		         if (e.getClickCount()==1) 
		         {
		        	 msg_deal();
		         }
		     }
		}
		 );
		
		msg_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		haha_zone_button.setBounds(new Rectangle(90, 515,20,20));
		msg_label.setBounds(new Rectangle(60, 515,20,20));
		search_button.setBounds(new Rectangle(120, 515,20,20));
		help_button.setBounds(new Rectangle(150, 515,20,20));
//		第三部分：按钮==========================================================
		game_label=new JLabel();
		add_group_label=new JLabel();
		music_label=new JLabel();
		chat_history_label=new JLabel();
		
		image=new ImageIcon("pic\\game.gif"); 
		game_label.setIcon(image);
		image=new ImageIcon("pic\\add_group.gif"); 
		add_group_label.setIcon(image);
		image=new ImageIcon("pic\\music.jpg"); 
		music_label.setIcon(image);
		image=new ImageIcon("pic\\chat_history.jpg"); 
		chat_history_label.setIcon(image);
		
		add_group_label.setBounds(new Rectangle(2,80,20,20));
		game_label.setBounds(new Rectangle(2,110,20,20));
		music_label.setBounds(new Rectangle(2,140,20,20));
		chat_history_label.setBounds(new Rectangle(2,170,20,20));
		
		add_group_label.setBorder(BorderFactory.createBevelBorder(1));
		game_label.setBorder(BorderFactory.createBevelBorder(1));
		music_label.setBorder(BorderFactory.createBevelBorder(1));
		chat_history_label.setBorder(BorderFactory.createBevelBorder(1));
		
		add_group_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		game_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		music_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chat_history_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		add_group_label.setToolTipText("添加分组");
		game_label.setToolTipText("GAME");
		music_label.setToolTipText("MUSIC");
		chat_history_label.setToolTipText("聊天记录");
		
		add_group_label.addMouseListener(new MouseAdapter() 
		{
		     public void mouseEntered(MouseEvent e) 
		     {
		    	 add_group_label.setBorder(BorderFactory.createRaisedBevelBorder());
		     }
		     public void mouseExited(MouseEvent e) 
		     {
		    	 add_group_label.setBorder(BorderFactory.createBevelBorder(1));
		     }
		     public void mouseClicked(MouseEvent e) 
		     {
		    	 String s=JOptionPane.showInputDialog(null,"分组名称","添加好友分组",1);
		    	 if(s==null||s.equals(""))
		    		 return;
		    	 else
		    	 {
		    		 Point p = getLocation(); 
		    		 String sql="insert into friend_group(id,group_name) values('"+num+"','"+s+"')";
		    	  		try{
		    	  			send=new msg_sender(0,sql);
		    	  		}catch(Exception e2){System.out.println("读取昵称时出错");}
		    	  	main m=new main(num);
		    		m.setLocation(p);
		    		m.setVisible(true);
		    	  	dispose();
		    	 }
		     }
		} );
		game_label.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				haha_game.game game=new haha_game.game();
				game.setVisible(true);
			}
		     public void mouseEntered(MouseEvent e) 
		     {
		    	 game_label.setBorder(BorderFactory.createRaisedBevelBorder());
		     }
		     public void mouseExited(MouseEvent e) 
		     {
		    	 game_label.setBorder(BorderFactory.createBevelBorder(1));
		     }
		});
		music_label.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				haha_player.s_player mp3=new haha_player.s_player();
				mp3.setVisible(true);
			}
		     public void mouseEntered(MouseEvent e) 
		     {
		    	 music_label.setBorder(BorderFactory.createRaisedBevelBorder());
		     }
		     public void mouseExited(MouseEvent e) 
		     {
		    	 music_label.setBorder(BorderFactory.createBevelBorder(1));
		     }
		} );
		chat_history_label.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				chat_history ch=new chat_history(num);
				ch.setVisible(true);
			}
		     public void mouseEntered(MouseEvent e) 
		     {
		    	 chat_history_label.setBorder(BorderFactory.createRaisedBevelBorder());
		     }
		     public void mouseExited(MouseEvent e) 
		     {
		    	 chat_history_label.setBorder(BorderFactory.createBevelBorder(1));
		     }
		} );
		c.add(pic);
		c.add(nickname_label);
		c.add(state_label);
		c.add(nickname_txt);
		c.add(state_option);
		c.add(add_group_label);
		c.add(game_label);
		c.add(music_label);
		c.add(chat_history_label);
		c.add(friend_panel);
		c.add(msg_label);
		c.add(haha_zone_button);
		c.add(search_button);
		c.add(help_button);
		//this.setVisible(true);
		ini_vectors();
		send_to_server();			//通知服务器本号码上线
		new msg_listener();
	}
	public void change_group_ini()
	{
		String sql="select distinct group_name from friend_group where id='"+num+"'";
  		try{
  			send=new msg_sender(1,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
			{
				JMenuItem m=new JMenuItem(str.nextToken().trim());
				m.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				m.addActionListener(new menu_listener());
				change_group_menu.add(m);
			}
  		}catch(Exception e){System.out.println("更新分组出错");}
	}
	private class menu_listener	implements ActionListener
	{

		public void actionPerformed(ActionEvent a) 
		{
			System.out.println(a.getActionCommand());
			String dst=a.getActionCommand();
			String temp_info=(String)list[current_list_index].getSelectedValue();
			if(temp_info==null)
			{
				JOptionPane.showMessageDialog(null,"您没有选中好友");
				return;
			}
	    	temp_info=temp_info.substring(6,temp_info.length());
			String temp_num;
			temp_num=temp_info.substring(temp_info.indexOf('>')+1,temp_info.length()-7);
			String sql="update friend set group_name='"+dst+"' where id='"+num+"' and friend_id='"+temp_num+"'";
			System.out.println(sql);
	  		try{
	  			send=new msg_sender(0,sql);
	  		}catch(Exception e){System.out.println("读取昵称时出错");}
	  		System.out.println(current_list_index+current_friend_info);
	  		inf[current_list_index].removeElement(current_friend_info);
	  		int index=0;
	  		for(index=0;index<n;index++)
	  		{
	  			if(group_button[index].getActionCommand().equals(dst))
	  				break;
	  		}
	  		System.out.println(index+current_friend_info);
	  		//inf[index].add(current_friend_info);

	  		//refresh();
		}
		
	}
	public void ini_personal_info()
	{
//		设置本人信息，昵称
		String sql="select nickname,pic from user1 where id='"+num+"'";
  		try{
  			send=new msg_sender(2,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			String nickname="";
			if(str.hasMoreTokens())
				nickname=str.nextToken().trim();
			if(str.hasMoreTokens())
			{
				head_pic=str.nextToken().trim();
			}
			nick_name=nickname;
  		}catch(Exception e){System.out.println("读取昵称时出错");}
  		
	}
	public void ini_group()
	{
		friend_group_vector=new Vector();		//存放所有分组名称
		String sql="select group_name from friend_group where id='"+num+"'";
  		try{
  			send=new msg_sender(1,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
			{
				friend_group_vector.add(str.nextToken().trim());
			}
			n=friend_group_vector.size();
  		}catch(Exception e){System.out.println("读取分组名称时出错");}
  		
	}
	public void ini_friends()
	{
		String sql;
  		//得到好友信息
  		friend_vector=new Vector();
		group_button=new RecButton[n];
		p=new JPanel[n];			//面板数
		list=new JList[n];			//列表数
		inf=new Vector[n];			//存放列表元素
		for(int i=0;i<n;i++)
		{
			sql="select remark,friend_id from friend,user1 where friend.id='"+num+"' and " +
				"friend.friend_id=user1.id and group_name='"+(String)friend_group_vector.elementAt(i)+"'";
			try{
				Vector v=new Vector();
				v.addElement((String)friend_group_vector.elementAt(i));
				send=new msg_sender(2,sql);
				String msg=send.r_msg();
				StringTokenizer str=new StringTokenizer(msg,"~");
				while(str.hasMoreTokens())
				{
					String temp_name=str.nextToken(),temp_id=str.nextToken();
					String s="<html>"+temp_name+"<br>"+temp_id+"</html>";
					v.addElement(s);
					//size[size_index]+=1;
				}
				friend_vector.addElement(v);
				}catch(Exception e2){e2.printStackTrace();}
				//size_index++;
		}

		for(int i=0;i<n;i++)
		{
			inf[i]=new Vector();
			for(int j=0;j<50;j++)
			{
				inf[i].add("猜猜");
			}
			list[i] = new JList(inf[i]);
			list[i].setBackground(Color.cyan);
			list[i].setCellRenderer(new CellRenderer());
		}
        
        for(int i=0;i<n;i++)
        {
        	Vector v=(Vector)friend_vector.elementAt(i);
        	group_button[button_index]=new RecButton((String)v.elementAt(0));
        	group_button[button_index].setForeground(Color.black);
        	if(i==0)
        		group_button[button_index].setBounds(new Rectangle(0,0,160,20));
        	else
        		group_button[button_index].setBounds(new Rectangle(0,440-(n-button_index)*20,160,20));
        	group_button[button_index].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	group_button[button_index].addActionListener(this);
        	group_button[button_index].addMouseListener(new MouseAdapter()
        	{
				public void mouseClicked(MouseEvent e)
				{
			    	if (!e.isMetaDown()&&e.getClickCount() == 2)
			    	{    		
			    		String temp_info=(String)list[current_list_index].getSelectedValue();
			    		send_msg(temp_info);
			    	}
			    	else if(e.isMetaDown())
			    	{
			    		RecButton r=((RecButton)(e.getSource()));
			    		for(int i=0;i<button_index;i++)
			    		{
			    			if(r.getText().equals(group_button[i].getText()))
			    			{
			    				if(r.getText().equals("黑名单"))
			    				{
			    					selected_button_index=-1;break;
			    				}
			    				else if(r.getText().equals("陌生人"))
			    				{
			    					selected_button_index=-2;break;
			    				}
			    				else
			    				{selected_button_index=i;break;}
			    			}
			    		}
			    		try{
			    		current_friend_info=(String)list[current_list_index].getSelectedValue();
			    		pop_button_menu.show(e.getComponent(),e.getX(),e.getY());
			    		}catch(Exception e2){}
			    	}
				}
		     });
        	friend_panel.add(group_button[button_index]);
        	button_index++;
        }
		for(int i=0;i<n;i++)
		{
			p[i]=new JPanel();
			p[i].setBackground(Color.DARK_GRAY);
			p[i].setLayout(null);
			JScrollPane scroll=new JScrollPane(list[i]);
		    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    scroll.setBounds(new Rectangle(0,0,160,440-button_index*20));
			p[i].add(scroll);
		}
	}
	public void ini_vectors()
	{
		for(int i=0;i<n;i++)
		{
			inf[i].removeAllElements();
			Vector v=(Vector)friend_vector.elementAt(i);
			for(int j=1;j<v.size();j++)
			{
				inf[i].addElement((String)v.elementAt(j));
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("help"))          //待完成，可以自己编写chm文档
		{
			System.out.println("asdfasdfa");
	  		inf[0].add("<html>butey<br>7025</html>");
		}
		else if(e.getActionCommand().equals("search"))
		{
			search sea=new search(num,nick_name);
			sea.setVisible(true);
		}
		else if(e.getActionCommand().equals("更改组名"))
		{
			if(selected_button_index==-2)				//陌生人
			{
				JOptionPane.showMessageDialog(null,"系统默认：“陌生人”组名不允许修改","警告对话框",1);
			}
			else if(selected_button_index==-1)			//黑名单
			{
				JOptionPane.showMessageDialog(null,"系统默认：“黑名单”组名不允许修改","警告对话框",1);
			}
			else
			{
				String group_name=JOptionPane.showInputDialog(null,"输入新组名","更改组名",1);
				if(group_name==null)
				{
					return;
				}
				else
				{
					if(group_name.equals(""))
					{return;}
					else
					{
						String old_name=group_button[selected_button_index].getText();
						group_button[selected_button_index].setText(group_name);
						String sql="update friend_group set group_name ='"+group_name+"' where id='"+num+"' and group_name='"+old_name+"'";
			      		System.out.println(sql);
			      		try{
			      			send=new msg_sender(0,sql);
			      			sql="update friend set group_name='"+group_name+"' where id='"+num+"' and group_name='"+old_name+"'";
			      			send=new msg_sender(0,sql);
			      		}catch(Exception e3){}
					}
				}
				change_group_menu.removeAll();
				change_group_ini();
			}
		}
		else if(e.getActionCommand().equals("删除该组"))
		{
			if(selected_button_index==-2)				//陌生人
			{
				JOptionPane.showMessageDialog(null,"系统默认：“陌生人”组名不允许删除","警告对话框",1);
			}
			else if(selected_button_index==-1)			//黑名单
			{
				JOptionPane.showMessageDialog(null,"系统默认：“黑名单”组名不允许删除","警告对话框",1);
			}
			else
			{
				int answer=JOptionPane.showConfirmDialog(null,"是否级联删除组内成员？","删除组",0);
				if(answer==0)		//点击是
				{
					String group_name=group_button[selected_button_index].getText();
					String sql="delete from friend where id='"+num+"' and group_name='"+group_name+"'";
		      		try{
		      			send=new msg_sender(0,sql);
		      		}catch(Exception e3){}
		      		sql="delete from friend_group where id='"+num+"' and group_name='"+group_name+"'";
		      		try{
		      			send=new msg_sender(0,sql);
		      		}catch(Exception e3){}
		      		Point p = getLocation(); 
		      		main m=new main(num);
		    		m.setLocation(p);
		    		m.setVisible(true);
		    	  	dispose();
				}
				else				//点击否,将好友移动到...
				{
					String group_name=group_button[selected_button_index].getText();
					String sql="update friend set group_name='陌生人' where id='"+num+"' and group_name='"+group_name+"'";
		      		try{
		      			send=new msg_sender(0,sql);
		      		}catch(Exception e3){}
		      		Point p = getLocation(); 
		      		main m=new main(num);
		    		m.setLocation(p);
		    		m.setVisible(true);
		    	  	dispose();
				}
				//0删除，1不删
			}
		}
		else if(e.getActionCommand().equals("添加用户"))
		{
			String group_name=group_button[selected_button_index].getText();
			search sea=new search(num,nick_name,group_name);
			sea.setVisible(true);
		}
		else if(e.getActionCommand().equals("haha_zone"))
		{
			System.out.println("haha_zone");
		}
		else if(e.getActionCommand().equals("发送消息"))
		{
			String temp_info=(String)list[current_list_index].getSelectedValue();
			//System.out.println(temp_info);
			if(temp_info==null)
			{
				JOptionPane.showMessageDialog(null,"您没有选中好友");
				return;
			}
    		send_msg(temp_info);
		}
		else if(e.getActionCommand().equals("查看资料"))
		{
			String temp_info=(String)list[current_list_index].getSelectedValue();
	    	temp_info=temp_info.substring(6,temp_info.length());
			String temp_name,temp_num;
			temp_name=temp_info.substring(0,temp_info.indexOf('<'));
			temp_num=temp_info.substring(temp_info.indexOf('>')+1,temp_info.length()-7);
			friend_inf f=new friend_inf(temp_num);
			f.setVisible(true);
		}
		else if(e.getActionCommand().equals("修改备注"))
		{
			String new_name=null;
			String temp_info=(String)list[current_list_index].getSelectedValue();
			if(temp_info==null)
			{
				JOptionPane.showMessageDialog(null,"您没有选中好友");
				return;
			}
	    	temp_info=temp_info.substring(6,temp_info.length());
			String temp_num;
			temp_num=temp_info.substring(temp_info.indexOf('>')+1,temp_info.length()-7);
			new_name=JOptionPane.showInputDialog("请输入新备注名称",null);
			if(new_name==null||new_name.equals("")) return;
      		String sql="update friend set remark ='"+new_name+"' where id='"+num+"' and friend_id='"+temp_num+"'";
      		System.out.println(sql);
      		try{
      			send=new msg_sender(0,sql);
        		String old_inf=(String)list[current_list_index].getSelectedValue();
        		String new_inf=old_inf.substring(0,6)+new_name+old_inf.substring(old_inf.length()-15,old_inf.length());
        		int i=list[current_list_index].getSelectedIndex();
        		set_vector_value(inf[current_list_index],i,new_inf);

      			JOptionPane.showMessageDialog(null,"服务器已接受您的请求");
      		}catch(Exception e2){System.out.println("error");}
		}
		else if(e.getActionCommand().equals("删除好友"))
		{
			if(!current_friend_info.equals(""))
			{
				String temp_info=(String)list[current_list_index].getSelectedValue();
				if(temp_info==null)
				{
					JOptionPane.showMessageDialog(null,"您没有选中好友");
					return;
				}
		    	temp_info=temp_info.substring(6,temp_info.length());
				String temp_num;
				temp_num=temp_info.substring(temp_info.indexOf('>')+1,temp_info.length()-7);
				int answer=JOptionPane.showConfirmDialog(null,"确认删除该好友？","确认对话框",2);
				//answer=0，确认，answer=2,取消
				//删除机制是将friend里面的一项去掉
				if(answer==0)
				{
					inf[current_list_index].removeElement(current_friend_info);
					String sql="delete from friend where id='"+num+"' and friend_id='"+temp_num+"'";
					try{
						send=new msg_sender(0,sql);
					}catch(Exception e2){}
					refresh();
					JOptionPane.showMessageDialog(null,"删除成功");
				}
			}
		}
		else
		{
			current_position_of_list=cardp.getLocation().y;
			int position_of_list=0,next_button_index=0,previous_button_index=0,list_height=440-button_index*20;
			int next_position;
			for(int i=0;i<n;i++)
			{
				if(e.getActionCommand().equals(group_button[i].getText()))
				{
					position_of_list=(i+1)*20;
					next_button_index=i+1;
					previous_button_index=i;
					current_list_index=i;
					break;
				}
			}
			if(position_of_list<current_position_of_list)
			{
				card.show(cardp,e.getActionCommand());
				cardp.setLocation(0, position_of_list);
				next_position=position_of_list+list_height;
				for(int i=next_button_index;i<n;i++)
				{
					group_button[i].setLocation(0, next_position);
					next_position+=20;
				}
			}
			else
			{
				card.show(cardp,e.getActionCommand());
				cardp.setLocation(0, position_of_list);
				next_position=0;
				for(int i=0;i<=previous_button_index;i++)
				{
					group_button[i].setLocation(0, next_position);
					next_position+=20;
				}
			}
		}
	}	
	public void refresh()
	{
		card.show(cardp,(String)(friend_group_vector.elementAt((current_position_of_list+1)%n)));
		card.show(cardp,(String)(friend_group_vector.elementAt((current_position_of_list)%n)));
	}
	public void dispose()
	{
		send("mo"+num+nick_name+"%"+num+nick_name+"$"+ds.getInetAddress()+"&"+port);
  		String sql="update user1 set state ='离线' where id='"+num+"'";
  		try{
  			send=new msg_sender(0,sql);
  		}catch(Exception e2){System.out.println("error");}
		super.dispose();
	}
	public void set_vector_value(Vector v,int index,String value)
	{
		int i=0;
		Vector temp1=new Vector(),temp=new Vector(),temp2=new Vector();
		int n=v.size();
		for(i=0;i<v.size();i++)
		{
			if(i<index)
				temp1.addElement((String)v.elementAt(i));
			else if(i==index)
				temp.addElement((String)v.elementAt(i));
			else
				temp2.addElement((String)v.elementAt(i));
		}
		v.removeAllElements();
		for(i=0;i<n;i++)
		{
			if(i<index)
				v.addElement((String)temp1.elementAt(i));
			else if(i==index)
				v.addElement(value);
			else
				v.addElement((String)temp2.elementAt(i-index-1));
		}
	}
	class CellRenderer extends JLabel implements ListCellRenderer
	{
		int row=0,col=0;
	    public CellRenderer()
	    {
	       setOpaque(true);
	      /* this.addMouseListener(new MouseAdapter()
	       {
	    	   
	       });*/
	    }
	       public String get_pic(String target)
	       {
	    	   try{
	    	   //System.out.println(target);
	    	   target=target.substring(target.lastIndexOf("r>")+2,target.length());
	    	   target=target.substring(0,4);
	    	   String sql="select pic,state from user1 where id='"+target+"'";
	     		try{
	     			send=new msg_sender(2,sql);
		   			String msg=send.r_msg();
		   			StringTokenizer str=new StringTokenizer(msg,"~");
		   			String pic="",state="";
		   			if(str.hasMoreTokens())
		   				pic=str.nextToken().trim();
		   			state=str.nextToken().trim();
		   			if(!state.equals("在线"))
		   				pic=pic.substring(0,pic.length()-4)+"_3.jpg";
		   			return pic;
		     		}catch(Exception e){System.out.println("读取昵称时出错");}
	    	   
	    	   }catch(Exception e){}
	    	   return "1.jpg";
	       }
	       public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus)
	       {
	           if (value != null)
	           {
	               setText(value.toString());
	               setIcon(new ImageIcon("Face\\"+get_pic((String)value)));
	           }
	           setBackground(list.getBackground());
	          if (isSelected&&cellHasFocus) {
	        	 //System.out.println((String)value);
	               setBackground(list.getSelectionBackground());
	               setForeground(list.getSelectionForeground());
	           }
	           else {
	               setBackground(list.getBackground());
	               setForeground(list.getForeground());
	           }
	           return this;
	       }
	}
	private void tray() {

		   tray = SystemTray.getSystemTray(); // 获得本操作系统托盘的实例
		   ImageIcon icon = new ImageIcon("pic//client.gif"); // 将要显示到托盘中的图标

		   PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单
		   MenuItem show = new MenuItem("打开程序(s)");
		   MenuItem exit = new MenuItem("退出程序(x)");
		   pop.add(show);
		   pop.add(exit);
		   trayIcon = new TrayIcon(icon.getImage(), "HAHA_CHAT:"+nick_name+"("+num+")", pop);

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
		    	  send("mo"+num+nick_name+"%"+num+nick_name+"$"+ds.getInetAddress()+"&"+port);
		      		String sql="update user1 set state ='离线' where id='"+num+"'";
		      		////System.out.println(sql);
		      		try{
		      			send=new msg_sender(0,sql);
		      		}catch(Exception e2){System.out.println("error");}
		      		System.exit(0);
		      }
		     });

		}
	public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e)
    { 
    	try{
	    	int index = list[current_list_index].locationToIndex(e.getPoint());
	    	list[current_list_index].setSelectedIndex(index);
	    	String temp_info=(String)list[current_list_index].getSelectedValue();
	    	temp_info=temp_info.substring(6,temp_info.length());
			String temp_name,temp_num;
			temp_name=temp_info.substring(0,temp_info.indexOf('<'));
			temp_num=temp_info.substring(temp_info.indexOf('>')+1,temp_info.length()-7);
	    	Point p = MouseInfo.getPointerInfo().getLocation(); 
	    	pop=new pop_inf(temp_num,p.x,p.y);
	    	pop.setVisible(true);
    	}
    	catch(Exception e2){}
    }
    public void mouseExited(MouseEvent e)
    {
    	if(pop!=null)
    		pop.dispose();
    }
    public void mouseClicked(MouseEvent e)
    {
    	if (!e.isMetaDown()&&e.getClickCount() == 2)
    	{
    		String temp_info=(String)list[current_list_index].getSelectedValue();
    		send_msg(temp_info);
    	}
    	else if(e.isMetaDown())
    	{
    		try{
    		current_friend_info=(String)list[current_list_index].getSelectedValue();
    		popupMenu.show(e.getComponent(),e.getX(),e.getY());
    		}catch(Exception e2){}
    	}
    }
    public void send_msg(String temp_info)			//发送消息
    {
    	temp_info=temp_info.substring(6,temp_info.length());
		String temp_name,temp_num;
		temp_name=temp_info.substring(0,temp_info.indexOf('<'));
		temp_num=temp_info.substring(temp_info.indexOf('>')+1,temp_info.length()-7);
  		chat cha=new chat(num,temp_num,nick_name,temp_name,"");
		cha.setVisible(true);
    }
    public void msg_deal()
    {
		if(have_msg)
		{
			String message="";//接收由发送方发送的消息
			String msg_temp="";
      		String sql="select from_id,from_name,msg from message where to_id='"+num+"' and not from_id='0000'";
      		System.out.println(sql);
      		try{
      			send=new msg_sender(3,sql);
      			String msg=send.r_msg();
    			StringTokenizer str=new StringTokenizer(msg,"~");
    			while(str.hasMoreTokens())
    			{
    				to_id=str.nextToken().trim();
    				to_name=str.nextToken().trim();
    				msg_temp=str.nextToken().trim();
        			sql="delete from message where to_id='"+num+"' and from_id='"+to_id+"'" +
        					" and msg='"+msg_temp+"' and not from_id='0000'";
          			send=new msg_sender(0,sql);
    				message+=to_name+"  "+time.gettime()+" >> \n~"+msg_temp+"\n~";
    			}
    			if(!message.equals(""))
    			{
	    			chat cha=new chat(num,to_id,nick_name,to_name,message);
	    			cha.setVisible(true);
    			}
      		}catch(Exception e2){System.out.println("error");}
      		
			message="";//接收由xitong方发送的消息
			msg_temp="";
      		sql="select from_id,from_name,msg from message where to_id='"+num+"' and from_id='0000'";
      		System.out.println(sql);
      		try{
      			send=new msg_sender(3,sql);
      			String msg=send.r_msg();
    			StringTokenizer str=new StringTokenizer(msg,"~");
    			while(str.hasMoreTokens())
    			{
    				to_id=str.nextToken().trim();
    				to_name=str.nextToken().trim();
    				msg_temp=str.nextToken().trim();
        			sql="delete from message where to_id='"+num+"' and from_id='"+to_id+"'" +
        					" and msg='"+msg_temp+"' and from_id='0000'";
          			send=new msg_sender(0,sql);
    				message+=msg_temp+"\n";
    			}
    			if(!message.equals(""))
    			{
	    			system_msg sm=new system_msg(message);
	    			sm.setVisible(true);
    			}
      		}catch(Exception e2){System.out.println("error");}
		}
    }
    private class msg_listener extends Thread		//监听接收信息
    {
    	public msg_listener() 
    	{
    		start();
    	}
    	public void run()
    	{
    		try
    		 {

				String sql="select to_id from message where to_id='"+num+"'";
    		    boolean last_msg_label=false;
    			while(true)
    	   		{
    				try
    				{
						send=new msg_sender(1,sql);
						String msg=send.r_msg();
						StringTokenizer str=new StringTokenizer(msg,"~");
						if(str.hasMoreTokens())
						{
							last_msg_label=have_msg;
							have_msg=true;
							//闪起来
							if(!last_msg_label)
							{
								image=new ImageIcon("pic\\msg2.gif"); 
								msg_label.setIcon(image);
							}
						}
						else
						{
							last_msg_label=have_msg;
							have_msg=false;
							//停止闪
							if(last_msg_label)
							{
								image=new ImageIcon("pic\\msg1.jpg"); 
								msg_label.setIcon(image);
							}
						}
    				}catch(Exception e){}
    	   		}
    		  }catch(Exception e){e.printStackTrace();}
    	}
    } 
	public void send_to_server()			//通知服务器端，本号码上线
	{
		String sql="update user1 set state='在线' where id='"+num+"'";
	  try
		{
		  send=new msg_sender(0,sql);
		InetAddress addr = InetAddress.getByName(login.ip);
		socket=new Socket(addr,PORT2);
		out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//创建	UDP；
		boolean istry = true;
		while (istry) 
		{
			try 
			{
				ds = new DatagramSocket(port);
				istry = false;
			} catch (Exception e) 
			{
				port++;
				istry = true;
			}
		}
		send("mi"+num+nick_name+"%"+num+nick_name+"$"+ds.getInetAddress()+"&"+port);
	  }catch(Exception e){e.printStackTrace();}
	}
	private void send(String string)
	{
		   try
		   {
			   out.println(string);
		   }catch(Exception ee){ee.printStackTrace();
		   }
	}
	public static void main(String args[])
	{
		main m=new main("2222");
		m.setVisible(true);
	}
}
