package QQ_test1;
import java.awt.*; 
import java.awt.event.*; 
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.*;

public class register2  extends JFrame implements ActionListener,WindowListener{
	private JPanel basic_inf,network_inf,psw_def;
	private JTextField nickname_txt,age_txt;
	private JPasswordField password1,password2;
	private JLabel  basic_inf_label,nickname_label,sex_label,age_label;
	private JLabel psw_label,psw1_label,psw2_label;
	private JLabel network_label,province_label,region_label;
	private CheckboxGroup cg;
	private Checkbox r1,r2;
	private RecButton commit_button;
	private JComboBox province_option,region_option;
	private String sql; 
	private Socket socket;
	private PrintWriter out;
	private int port=2000;
	public static final int PORT2=8081;
	msg_sender send;
	public register2()
	{
		try{
		InetAddress addr = InetAddress.getByName(login.ip);
		socket=new Socket(addr,PORT2);
		out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
		}catch(Exception e){}
		
		setTitle("HAHA-CHAT注册窗口");
		setSize(220,390);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}
    	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	addWindowListener(this);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		basic_inf=new JPanel();
		network_inf=new JPanel();
		psw_def=new JPanel();
		
		basic_inf.setLayout(null);
		network_inf.setLayout(null);
		psw_def.setLayout(null);
				
		basic_inf.setBounds(new Rectangle(5, 5,210,110));
		psw_def.setBounds(new Rectangle(5,120,210, 90));
		network_inf.setBounds(new Rectangle(5, 220,210, 90));
		
		
//		第一部分：基本信息填写
		basic_inf_label=new JLabel("基本信息：");	
		basic_inf_label.setFont(new Font("宋体",Font.BOLD,14));
		nickname_label=new JLabel("昵称：");
		sex_label=new JLabel("性别：");
		age_label=new JLabel("年龄：");
		
		nickname_txt=new JTextField(10);
		age_txt=new JTextField(10);
		
		cg=new CheckboxGroup(); 
		r1=new Checkbox("男",cg,true);
		r2=new Checkbox("女",cg,false);
		
		basic_inf_label.setBounds(new Rectangle(5,5,290, 20));
		nickname_label.setBounds(new Rectangle(20,30,50, 20));
		nickname_txt.setBounds(new Rectangle(80,30,100, 20));
		age_label.setBounds(new Rectangle(20,55,50, 20));
		age_txt.setBounds(new Rectangle(80,55,100, 20));
		sex_label.setBounds(new Rectangle(20,80,50, 20));
		r1.setBounds(new Rectangle(80,80,50, 20));
		r2.setBounds(new Rectangle(140,80,50, 20));
		
		basic_inf.setBackground(Color.black);
		basic_inf_label.setForeground(Color.green);
		nickname_label.setForeground(Color.green);
		age_label.setForeground(Color.green);
		sex_label.setForeground(Color.green);
		r1.setForeground(Color.green);
		r2.setForeground(Color.green);
		
		basic_inf.add(basic_inf_label);
		basic_inf.add(nickname_label);
		basic_inf.add(nickname_txt);
		basic_inf.add(age_label);
		basic_inf.add(age_txt);
		basic_inf.add(sex_label);
		basic_inf.add(r1);
		basic_inf.add(r2);
		/////////////////////////////////////////////////////////第一部分：基本信息填写完毕
		
		//第二部分：密码设置
		
		psw_label=new JLabel("设置密码：");
		psw_label.setFont(new Font("宋体",Font.BOLD,14));
		psw1_label=new JLabel("密码：");
		psw2_label=new JLabel("确认密码：");
		
		password1=new JPasswordField(10);
		password2=new JPasswordField(10);
		password1.setEchoChar('*');
		password2.setEchoChar('*');
		
		psw_label.setBounds(new Rectangle(5,5,290, 20));
		psw1_label.setBounds(new Rectangle(20,30,50, 20));
		password1.setBounds(new Rectangle(80,30,100, 20));
		psw2_label.setBounds(new Rectangle(20,55,80, 20));
		password2.setBounds(new Rectangle(80,55,100, 20));
		
		psw_def.setBackground(Color.black);
		psw_label.setForeground(Color.green);
		psw1_label.setForeground(Color.green);
		psw2_label.setForeground(Color.green);
		
		psw_def.add(psw_label);
		psw_def.add(psw1_label);
		psw_def.add(password1);
		psw_def.add(psw2_label);
		psw_def.add(password2);
				
/////////////////////////////////////////////////////////第二部分：密码设置完毕
		
		//第三部分：所在网络
		network_label=new JLabel("所属网络：");
		network_label.setFont(new Font("宋体",Font.BOLD,14));
		province_label=new JLabel("省份：");
		region_label=new JLabel("地区：");
		
		province_option=new JComboBox();
		province_option.setMaximumRowCount(20);
		province_ini(province_option);
		
		region_option=new JComboBox();
		province_option.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent evt){ 
	      		region_option.removeAllItems();
	      		region_ini(region_option);
	         }
	        });
		
		region_ini(region_option);
		region_option.setMaximumRowCount(15);
		
		network_label.setBounds(new Rectangle(5,5,290, 20));
		province_label.setBounds(new Rectangle(20,30,50, 20));
		province_option.setBounds(new Rectangle(80,30,100, 20));
		region_label.setBounds(new Rectangle(20,55,50, 20));
		region_option.setBounds(new Rectangle(80,55,100, 20));
		
		network_inf.setBackground(Color.black);
		network_label.setForeground(Color.green);
		province_label.setForeground(Color.green);
		region_label.setForeground(Color.green);
		
		network_inf.add(network_label);
		network_inf.add(province_label);
		network_inf.add(province_option);
		network_inf.add(region_label);
		network_inf.add(region_option);
/////////////////////////////////////////////////////////第三部分：所在网路设置完毕
		
		////////第四部分：提交按钮
		commit_button=new RecButton("提交");
		commit_button.setForeground(Color.GREEN);
		commit_button.addActionListener(this);
		commit_button.setBounds(new Rectangle(75,310,70, 25));
		commit_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
/////////////////////////////////////////////////////////第四部分：提交按钮设置完毕		
		c.add(basic_inf);
		c.add(psw_def);
		c.add(network_inf);
		c.add(commit_button);
		

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("提交"))
		{
			String nickname,sex,psw1,psw2,province,region,entry_year;
			int age=0;
			nickname=nickname_txt.getText();
			age=Integer.parseInt(age_txt.getText());
			if(r1.getState()) sex="男";
			else sex="女";
			psw1=password1.getText();
			psw2=password2.getText();
			province=(String)province_option.getSelectedItem();
			region=(String)region_option.getSelectedItem();
		//----------------------------------------------------------------这里放出错检测
			
			
		//---------------------------------------------------------------------------------
			String num;
			do{
				num=get_random_num();
			}while(exist(num));
			sql="insert into user1(id,nickname,age,sex,province,region,pic,state,password) values ('"+num+"','"+nickname+"',"+age+"," +
					"'"+sex+"','"+province+"','"+region+"','1.jpg','离线','123')";
			//System.out.println(sql);
			try{
				send=new msg_sender(0,sql);
				sql="insert into user_inf(id,birthday,realname,birthmonth,birth_animal,constellation," +
						"personal_sign,personal_tells,email,address,cellphone,tel,l,blood_style,occupation) values " +
						"('"+num+"','-','-','-','-','-','HAHA-CHAT','HAHA-CHAT','-','-','-','-','仅好友可见','-','-')";
				send=new msg_sender(0,sql);
				boolean msg=send.updated();
				if(msg)
				{
					send("rgnothing");
					msg_window win1=new msg_window("恭喜您注册成功<br>您的HAHA账号为："+num);
					sql="insert into friend_group(id,group_name) values " +
					"('"+num+"','我的好友')";
					send=new msg_sender(0,sql);
					
					sql="insert into friend_group(id,group_name) values " +
					"('"+num+"','陌生人')";
					send=new msg_sender(0,sql);
					
					sql="insert into friend_group(id,group_name) values " +
					"('"+num+"','黑名单')";
					send=new msg_sender(0,sql);
					//dispose();
					win1.setVisible(true);
				}
				else JOptionPane.showMessageDialog(null, "注册失败，请检查注册信息");
				
			}catch(Exception e2){System.out.println("查询省内学校错误");}
		}
	}
	public boolean exist(String num)
	{
		boolean exist=false;
		sql="select id from user1 where id='"+num+"'";
		try{
			send=new msg_sender(1,sql);
			String str=send.r_msg();
			if(str.equals(""))
				exist=false;
			else
				exist=true;
		}catch(Exception e2){System.out.println("查询省内学校错误");}
		return exist;
	}
	public JComboBox province_ini(JComboBox j_box)
	{
		String sql="select distinct province from province_inf";
		try{
			send=new msg_sender(1,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
				j_box.addItem(str.nextToken());
			
		}catch(Exception e2){System.out.println("查询省份错误");}
		return j_box;
	}
	
	public JComboBox region_ini(JComboBox j_box)
	{
		String province=(String)province_option.getSelectedItem();
		String sql="select region from province_inf where province='"+province+"'";
		try{
			send=new msg_sender(1,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
				j_box.addItem(str.nextToken());
			
		}catch(Exception e2){System.out.println("查询省内地区错误");}
		return j_box;
	}
	public String get_random_num()
	{
		int a=0;
		do{
			a=(int)(Math.random()*10000);
		}while(a<1000);
		return String.valueOf(a);
	}
	public void windowOpened(WindowEvent e){}
	public void windowClosing(WindowEvent e){
		//String sql="los,"+username;
		try{
		//send=new msg_sender(0,sql);
		}catch(Exception ea){}
		dispose();
		login log=new login();
		log.setVisible(true);
	  } 
	public void windowClosed(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	private void send(String string)		//向服务器通知用户注册的消息
	{
		   try
		   {
			   out.println(string);
		   }catch(Exception ee){ee.printStackTrace();
		   }
	}
	public static void main(String args[])
	{
		register2 reg=new register2();
		reg.setVisible(true);
	}
}
