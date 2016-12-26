package QQ_test1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

public class chat extends JFrame implements ActionListener,WindowListener{
	private int expression_window_x=1000,expression_window_y=1000;
	private Socket socket;
	private DatagramSocket ds;
	private BufferedReader in;
	private PrintWriter out;
	private int port=2000;
	public static final int PORT2=8081;
	private PrintWriter outputStream=null;
	private BufferedReader inputStream=null;
	private String temporaryContent;
	private JPanel text_panel1,text_panel2;
	private JLabel num1_label,nickname1_label,sex1_label,age1_label,province1_label,region1_label;//entry_year1_label;
	private JLabel num2_label,nickname2_label,sex2_label,age2_label,province2_label,region2_label;//entry_year2_label;
	private myTextPane area1;
	private myTextPane area2;
	private String content_of_area2="";
	private RecButton send_button,bold_button,italic_button,color_button,close_button,expression_button;
	private JComboBox font_option,size_option;
	private String from,to,sql,name_from,name_to,ziti="宋体",message,content="";
	private int size=16,style=Font.PLAIN,bold=0,italic=0;//style为字体的特征	
	private Color color=Color.black;
	private String font_name1="宋体",font_name2="宋体";
	private int font_size1=16,font_size2=16;
	private int color11=0,color12=0,color13=0,color21=0,color22=0,color23=0;
	private int font_style1=0,font_style2=0;
	private boolean font_changed=false;        //表示己方的字体属性是不是改变了
	private msg_sender send;
	private file f;
	String ip="";
public chat(String from,String to,String name_from,String name_to,String msg)
{
	System.out.println(to);
	this.from=from;
	this.to=to;
	this.name_from=name_from;
	this.name_to=name_to;
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  try
	{
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
	InetAddress ad=InetAddress.getLocalHost(); 
	ip=ad.getHostAddress().toString();//获得本机IP 
	send("ci"+from+name_from+"%"+to+name_to+"$"+ip+"&"+port);
  }catch(Exception e){e.printStackTrace();}
  
  f=new file();
	setTitle("haha聊天对话框---"+name_to+"chatting中");
	setSize(462,500);
	setBackground(Color.BLACK);
	addWindowListener(this);
	setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
	try
	{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	}catch(Exception e){e.printStackTrace();}
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Container c = getContentPane();
	c.setLayout(null);
	c.setBackground(Color.black);
	///////////////////////////////////area1===============
	text_panel1=new JPanel();
	text_panel1.setLayout(null);
	area1=new myTextPane();
	area1.setEditable(false);
	JScrollPane scrolledText1=new JScrollPane(area1);
	scrolledText1.setBounds(new Rectangle(3,3,293,263));
	text_panel1.add(scrolledText1);
//	================================================================
	
	//右面的图片(上)===================================================
	Icon image1=new ImageIcon("people\\man.gif"); 
	Icon image2=new ImageIcon("people\\woman.gif");
	JLabel pic1=new JLabel();
	pic1.setIcon(image1);
	pic1.setBounds(new Rectangle(310,-16,300,270));
	
	JLabel l1=new JLabel("对方形象：");
	l1.setBounds(new Rectangle(310,10,300,20));
	l1.setForeground(Color.red);
	l1.setFont(new Font("宋体",Font.BOLD,14));
	
	num1_label=new JLabel("haha号码：");
	nickname1_label=new JLabel("昵称：");
	sex1_label=new JLabel("性别：");
	age1_label=new JLabel("年龄：");
	province1_label=new JLabel("省份：");
	region1_label=new JLabel("地区：");
	
	num1_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	nickname1_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	sex1_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	age1_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	province1_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	region1_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	
	num1_label.setBounds(new Rectangle(320,25,300,20));
	nickname1_label.setBounds(new Rectangle(320,40,200,20));
	sex1_label.setBounds(new Rectangle(320,55,200,20));
	age1_label.setBounds(new Rectangle(320,70,200,20));
	province1_label.setBounds(new Rectangle(320,85,200,20));
	region1_label.setBounds(new Rectangle(320,100,200,20));
	
	
	//=============================================================
	
	
	//右面的图片(下)===================================================
	JLabel pic2=new JLabel();
	pic2.setIcon(image2);
	pic2.setBounds(new Rectangle(310,215,300,270));
	
	JLabel l2=new JLabel("自我形象：");
	l2.setBounds(new Rectangle(310,240,300,20));
	l2.setForeground(Color.red);
	l2.setFont(new Font("宋体",Font.BOLD,14));
	
	num2_label=new JLabel("haha号码：");
	nickname2_label=new JLabel("昵称：");
	sex2_label=new JLabel("性别：");
	age2_label=new JLabel("年龄：");
	province2_label=new JLabel("省份：");
	region2_label=new JLabel("地区：");
	
	num2_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	nickname2_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	sex2_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	age2_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	province2_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	region2_label.setFont(new Font("宋体",Font.HANGING_BASELINE,14));
	
	num2_label.setBounds(new Rectangle(320,255,300,20));
	nickname2_label.setBounds(new Rectangle(320,270,200,20));
	sex2_label.setBounds(new Rectangle(320,285,200,20));
	age2_label.setBounds(new Rectangle(320,300,200,20));
	province2_label.setBounds(new Rectangle(320,315,200,20));
	region2_label.setBounds(new Rectangle(320,330,200,20));
	//=============================================================
	
	////中间按钮===================================================
	font_option=new JComboBox();
	String[] fontNames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); 
	for(int i=0;i<fontNames.length;i++)
	{
		font_option.addItem(fontNames[i]);
	}
	font_option.addActionListener(new ActionListener(){
      	public void actionPerformed(ActionEvent evt){ 
      		font_name2=(String)font_option.getSelectedItem();
      		area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
      		font_changed=true;
         }
        });
	
	
	size_option=new JComboBox();
	size_option.addItem("12");size_option.addItem("14");
	size_option.addItem("16");size_option.addItem("18");
	size_option.addItem("20");size_option.addItem("22");
	size_option.addItem("26");size_option.addItem("30");
	size_option.addItem("34");size_option.addItem("38");
	size_option.addItem("40");size_option.addItem("50");
	
	size_option.addActionListener(new ActionListener(){
      	public void actionPerformed(ActionEvent evt){ 
      		font_size2=Integer.parseInt((String)size_option.getSelectedItem());
      		area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
      		font_changed=true;
         }
        });
	
	Icon bold_image=new ImageIcon("pic\\bold.jpg"); 
	bold_button=new RecButton("",bold_image);
	bold_button.setActionCommand("粗体");
	bold_button.addActionListener(this);
	
	Icon italic_image=new ImageIcon("pic\\italic.jpg"); 
	italic_button=new RecButton("",italic_image);
	italic_button.setActionCommand("斜体");
	italic_button.addActionListener(this);
	
	Icon color_image=new ImageIcon("pic\\color.jpg"); 
	color_button=new RecButton("",color_image);
	color_button.setActionCommand("颜色");
	color_button.addActionListener(new ColorListener());
	
	Icon expression_image=new ImageIcon("pic\\expression.jpg"); 
	expression_button=new RecButton("",expression_image);
	expression_button.setActionCommand("表情");
	expression_button.addActionListener(this);
	
	font_option.setBounds(new Rectangle(10,277,110,20));
	size_option.setBounds(new Rectangle(125,277,50,20));
	bold_button.setBounds(new Rectangle(180,277,25,20));
	italic_button.setBounds(new Rectangle(210,277,25,20));
	color_button.setBounds(new Rectangle(240,277,25,20));
	expression_button.setBounds(new Rectangle(270,277,25,20));
	//===========================================================
	
	///////////////////////////////////area2=================
	text_panel2=new JPanel();
	text_panel2.setLayout(null);
	area2=new myTextPane();
	area2.addKeyListener(new KeyAdapter()
	{
	    public void keyTyped(KeyEvent e){
	        char c=e.getKeyChar();/*注意getKeyChar()的用法*/
	        if(((int)c)!=10)
	        	content_of_area2=content_of_area2+c;
	        else
	        {
	        	send_commond();
	        }
	     }
	});
	JScrollPane scrolledText2=new JScrollPane(area2);
	scrolledText2.setBounds(new Rectangle(4, 4,292,113));
	text_panel2.add(scrolledText2);
//	================================================================
	//==最后发送按钮==================================================
	send_button=new RecButton("发送");
	send_button.addActionListener(this);
	close_button=new RecButton("关闭");
	close_button.addActionListener(this);
	send_button.setForeground(Color.green);
	close_button.setForeground(Color.green);
	
	area1.setFont(font_name1, font_size1, new Color(color11,color12,color13),null, font_style1);		//设置对方默认字体属性
	area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);		//设置对方默认字体属性
	
	send_button.setBounds(new Rectangle(235,430,60,30));
	close_button.setBounds(new Rectangle(160,430,60,30));
//	================================================================
	text_panel1.setBounds(new Rectangle(5, 5,300,270));
	text_panel2.setBounds(new Rectangle(5, 300,300,120));
	
	ini_inf();
	
	c.add(text_panel1);
	c.add(text_panel2);
	c.add(l1);
	c.add(l2);
	c.add(num1_label);	c.add(nickname1_label);	c.add(sex1_label);
	c.add(age1_label);	c.add(province1_label);	c.add(region1_label);
	c.add(num2_label);	c.add(nickname2_label);	c.add(sex2_label);	
	c.add(age2_label);	c.add(province2_label);	c.add(region2_label);
	c.add(font_option);
	c.add(size_option);
	c.add(bold_button);
	c.add(italic_button);
	c.add(color_button);
	c.add(expression_button);
	c.add(close_button);
	c.add(send_button);
	c.add(pic1);
	c.add(pic2);
	new ReceiveInformation();
	new ReceiveMessage();
	if(!msg.equals(""))
	{
		try{
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
			{
				  area1.setFont("宋体", 15, new Color(0,128,64),null,0);
				  String content=str.nextToken();
				  area1.insert(content);
				  
				  f.enter=false;
				  f.write_into_file(to,content);
				  
				  area1.setFont(font_name2, font_size2,new Color(color21,color22,color23), null, font_style2);
				  content=str.nextToken();
				  
				  f.write_into_file(to,content);
				  area1.insert(content);
				  f.enter=true;
			}
		}catch(Exception e){System.out.println("消息添加错误");}
	}
}
public void send_commond()
{
	if(!content_of_area2.equals(""))
	{
		temporaryContent=content_of_area2;
		area2.setText("");
		content_of_area2="";
		send("to"+from+to);
	}
}
public void actionPerformed(ActionEvent e)
{
	if(e.getActionCommand().equals("粗体"))
	{
		if(font_style2==0)
		{
			font_style2=1;
			area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
      		font_changed=true;
		}
		else if(font_style2==1)
		{
			font_style2=0;
			area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
      		font_changed=true;
		}
		else if(font_style2==2)
		{
			font_style2=3;
			area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
      		font_changed=true;
		}
		else if(font_style2==3)
		{
			font_style2=2;
			area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
      		font_changed=true;
		}
	}
	else if(e.getActionCommand().equals("斜体"))
	{
		if(font_style2==0)
		{
			font_style2=2;
			area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
      		font_changed=true;
		}
		else if(font_style2==1)
		{
			font_style2=3;
			area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
      		font_changed=true;
		}
		else if(font_style2==2)
		{
			font_style2=0;
			area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
      		font_changed=true;
		}
		else if(font_style2==3)
		{
			font_style2=1;
			area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
      		font_changed=true;
		}
	}
	else if(e.getActionCommand().equals("表情"))
	{
		Point p = MouseInfo.getPointerInfo().getLocation(); 
		final expression e2=new expression(p.x,p.y);
		e2.addWindowListener(new WindowAdapter()
	    {
			public void windowActivated(WindowEvent e){}
			public void windowDeactivated(WindowEvent e)		//当窗口不活跃的时候，自动关闭该窗口
			{
				e2.dispose();
			}
	    });
		e2.setVisible(true);
		
		/*for(int i=0;i<100;i++)
		   area1.insertIcon(new ImageIcon("expression//"+i+".gif"));**/
	}
	else if(e.getActionCommand().equals("发送"))
	{
		if(!content_of_area2.equals(""))
		{
			temporaryContent=content_of_area2;
			area2.setText("");
			content_of_area2="";
			send("to"+from+to);
		}
		else
		{
			JOptionPane.showMessageDialog(null,"发送内容不能为空","警告",0);
		}
	}
	else if(e.getActionCommand().equals("关闭"))
	{
		send("co"+from+name_from+"%"+to+name_to+"$"+ip+"&"+port);
		dispose();
	}
}
//接受命令信息，接收方ip及端口号
private class ReceiveInformation extends Thread
{
	   public ReceiveInformation()
	   {
		   start();
	   }
	   public void run()
	   {
		   while(true)
		   {
			   try
			   {
				   String message=in.readLine();
				   String command=message.substring(0, 2),information=message.substring(2);
				   if(command.equals("to"))
				   {
					   if(information.equals("notfound"))			//对方不在线，或者对方没有打开聊天对话框
					   { 
						   //将消息写入数据库，并在己方显示窗口中添加该消息
							try{
								send=new msg_sender(0,sql);
								sql="insert into message(from_id,from_name,to_id,to_name,msg) values "+
										"('"+from+"','"+name_from+"','"+to+"','"+name_to+"','"+temporaryContent+"')";
								send=new msg_sender(0,sql);		
							}catch(Exception e2){System.out.println("消息写入数据库错误");}
							area1.setFont("宋体", 15, new Color(0,128,64),null,0);
							String t=time.gettime();
							content=name_from+"  "+t+" >>";
							f.write_into_file(to,content);
							area1.insertln(name_from+"  "+t+" >>");
							area1.setFont(font_name2, font_size2,new Color(color21,color22,color23),null, font_style2);
							area1.insertln(temporaryContent);
							f.write_into_file(to,temporaryContent);
						System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					   }
					   else		//对方在线
					   {
						   System.out.println(information);
						   talkTo(temporaryContent,information.substring(0, 4),information.substring(4, 
								   information.indexOf("$")),information.substring(information.indexOf("$")+1));
					   }
				   }
			   }catch(Exception e){e.getMessage();}
		   }
	   }
 }
//接收文字信息；
private class ReceiveMessage extends Thread
{
	byte[] buf;
	DatagramPacket dp;
	String str,str1,str2;
	public ReceiveMessage() 
	{
		start();
	}
	public void run()
	{
		try
		 {
		    buf=new byte[256];
			dp=new DatagramPacket(buf,buf.length);	
			while(true)
	   		{
				try
				{
			      ds.receive(dp);
			      str=new String(buf).trim();
				   System.out.println("chat("+from+")--ReceiveMessage::"+str);
			      insertWords(area1,str);
				}catch(Exception e){}
	   		}
		  }catch(Exception e){e.printStackTrace();}
	}
} 
public class expression extends JFrame implements MouseListener
{
	int window_x=0,window_y=0;			//保存窗口左上角的位置
	int n=135;
	int sqr_n=(int)(Math.sqrt(n))+1;
	JLabel expression_label[][];
	int i=0,j=0,x=0,y=0,setted_x=0,setted_y=0;
	boolean selected=false;
	Icon image;
	Container c=getContentPane();
	public expression(int wx,int wy)
	{
		window_x=wx-(2+25*(sqr_n));
		window_y=wy-(2+25*(sqr_n));
		setTitle("选择表情");
		setSize(2+25*(sqr_n),2+25*(sqr_n));
		this.setLocation(window_x,window_y);
		c.setLayout(null);
		c.setBackground(Color.black);
		expression_label=new JLabel[sqr_n][sqr_n];
		this.setUndecorated(true);
		expreesion_ini();
	}
	public void expreesion_ini()
	{
		for(i=0;i<sqr_n;i++)
		{
			for(j=0;j<sqr_n;j++)
			{
				int num=i*sqr_n+j;
				image=new ImageIcon("expression\\"+num+".gif");
				expression_label[i][j]=new JLabel();
				expression_label[i][j].setBounds(new Rectangle(1+j*25,1+i*25,24,24));
				expression_label[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				expression_label[i][j].addMouseListener(this);
				expression_label[i][j].setBorder(BorderFactory.createBevelBorder(0));
				if(image.getIconHeight()!=-1)
				{
					expression_label[i][j].setIcon(image);
					c.add(expression_label[i][j]);
				}
				else
				{
					image=new ImageIcon("expression\\-1.gif");
					expression_label[i][j].setIcon(image);
					c.add(expression_label[i][j]);
				}
			}
		}
	}
	public void mousePressed(MouseEvent e){	}
	public void mouseReleased(MouseEvent e)	{	}
	public void mouseEntered(MouseEvent e)
	{
		JLabel l=(JLabel)e.getSource();
		x=0;y=0;
		for(i=0;i<sqr_n;i++)
		{
			for(j=0;j<sqr_n;j++)
			{
				if(l==expression_label[i][j])
				{
					x=i;y=j;
					break;
				}
			}
		}
		expression_label[x][y].setBorder(BorderFactory.createBevelBorder(1));
	}
	public void mouseExited(MouseEvent e)
	{  
		JLabel l=(JLabel)e.getSource();
		x=0;y=0;
		for(i=0;i<sqr_n;i++)
		{
			for(j=0;j<sqr_n;j++)
			{
				if(l==expression_label[i][j])
				{
					x=i;y=j;
					break;
				}
			}
		}
		expression_label[x][y].setBorder(BorderFactory.createBevelBorder(0));
	}
	public void mouseClicked(MouseEvent e)
	{		
		JLabel l=(JLabel)e.getSource();
		x=0;y=0;
		for(i=0;i<sqr_n;i++)
		{
			for(j=0;j<sqr_n;j++)
			{
				if(l==expression_label[i][j])
				{
					setted_x=i;setted_y=j;
					break;
				}
			}
		}
		selected=true;
		int num=setted_x*sqr_n+setted_y;
		content_of_area2+="/"+num+"\\";
		area2.insert_icon("expression\\"+num+".gif");
		dispose();
	}
	public int get_expression()
	{
		if(selected)
		{
			return setted_x*sqr_n+setted_y;
		}
		else
		{
			return -1;
		}
	}
}
private void insertWords(myTextPane tp,String words)
{
	String myWords=words;
	System.out.println("insertWords------"+myWords);
	if(myWords.substring(0, 2).equals("To"))
	{
		try
		{
			String contents=myWords.substring(2,myWords.length());
			StringTokenizer str=new StringTokenizer(contents,"~");
			String name="",content="";
			if(str.hasMoreTokens())
				content=str.nextToken().trim();
			if(str.hasMoreTokens())
				name=str.nextToken().trim();
			tp.setFont("宋体", 15, new Color(0,128,64),null,0);
			String t=time.gettime();
			this.content=name+"  "+t+" >>";
			f.write_into_file(to,this.content);
			tp.insertln(name+"  "+t+" >>");
			tp.setFont(font_name1, font_size1, new Color(color11,color12,color13),null, font_style1);
			tp.insertln(content);
			f.write_into_file(to,content+"");
			
		}catch(Exception e){e.printStackTrace();}
	}
	else if(myWords.substring(0, 2).equals("Tc"))
	{
		try
		{
			/*System.out.println("To"+contents+"~"+name_from+"~"+font_name1+"~"+font_size1+"~"+color11+"~"+
			   color12+"~"+color13+"~"+font_style1);*/
			String contents=myWords.substring(2,myWords.length());
			StringTokenizer str=new StringTokenizer(contents,"~");
			String name="",content="";
			if(str.hasMoreTokens())
				content=str.nextToken().trim();
			if(str.hasMoreTokens())
				name=str.nextToken().trim();
			if(str.hasMoreTokens())
				font_name1=str.nextToken().trim();
			if(str.hasMoreTokens())
				font_size1=Integer.parseInt(str.nextToken().trim());
			if(str.hasMoreTokens())
				color11=Integer.parseInt(str.nextToken().trim());
			if(str.hasMoreTokens())
				color12=Integer.parseInt(str.nextToken().trim());
			if(str.hasMoreTokens())
				color13=Integer.parseInt(str.nextToken().trim());
			if(str.hasMoreTokens())
				font_style1=Integer.parseInt(str.nextToken().trim());
			tp.setFont("宋体", 15, new Color(0,128,64),null,0);
			tp.insertln(name+"  "+time.gettime()+" >>");
			System.out.println(font_name1+"  "+font_size1+"  "+color11+"  "+color12+"  "+color13+"  "+font_style1);
			tp.setFont(font_name1, font_size1, new Color(color11,color12,color13),null, font_style1);
			tp.insertln(content);
		}catch(Exception e){e.printStackTrace();}
	}
}
private void talkTo(String contents,String accont,String address,String port)
{
	   try
	   {
		  // System.out.println("chat("+from+")--talkTo::"+contents);
		   if(!font_changed)
		   {
			  byte[] buf=("To"+contents+"~"+name_from+"~").getBytes();
			  DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getByName(address),Integer.parseInt(port));
			  ds.send(dp);
		   }
		   else
		   {
			   byte[] buf=("Tc"+contents+"~"+name_from+"~"+font_name2+"~"+font_size2+"~"+color21+"~"+
					   color22+"~"+color23+"~"+font_style2).getBytes();
			   DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getByName(address),Integer.parseInt(port));
			   ds.send(dp);
			   font_changed=false;
		   }
		  area1.setFont("宋体", 15, new Color(0,128,64),null,0);
		  
		  String t=time.gettime();
		  content=name_from+"  "+t+" >>";
		  f.write_into_file(to,content);
		  area1.insertln(name_from+"  "+t+" >>");
		  area1.setFont(font_name2, font_size2,new Color(color21,color22,color23), null, font_style2);
		  area1.insertln(contents);
		  f.write_into_file(to,contents);
	   }catch(Exception e){e.printStackTrace();}
}
private void send(String string)
{
	   try
	   {
		   System.out.println("chat向服务器发送消息，请求得到接收方ip及端口号::"+string);
		   out.println(string);
	   }catch(Exception ee){ee.printStackTrace();
	   }
}
public void ini_inf()
{
	sql="select id,nickname,sex,age,province,region from user1 where id='"+from+"'";
	//System.out.println(sql);
	try{
		send=new msg_sender(6,sql);
		String msg=send.r_msg();
		StringTokenizer str=new StringTokenizer(msg,"~");
		
		num2_label.setText("HAHA账号:"+str.nextToken());
		nickname2_label.setText("昵称:"+str.nextToken());
		sex2_label.setText("性别:"+str.nextToken());
		age2_label.setText("年龄:"+str.nextToken());
		province2_label.setText("省份:"+str.nextToken());
		region2_label.setText("地区:"+str.nextToken());
		
	}catch(Exception e2){System.out.println("错误");}
	
	sql="select id,nickname,sex,age,province,region from user1 where id='"+to+"'";
	//System.out.println(sql);
	try{
		send=new msg_sender(6,sql);
		String msg=send.r_msg();
		StringTokenizer str=new StringTokenizer(msg,"~");
		
		num1_label.setText("HAHA账号:"+str.nextToken());
		nickname1_label.setText("昵称:"+str.nextToken());
		sex1_label.setText("性别:"+str.nextToken());
		age1_label.setText("年龄:"+str.nextToken());
		province1_label.setText("省份:"+str.nextToken());
		region1_label.setText("地区:"+str.nextToken());
		
	}catch(Exception e2){System.out.println("错误");}
}

private class ColorListener implements ActionListener
{                            
   public void actionPerformed(ActionEvent e)
   {
       if(e.getActionCommand().equals("颜色"))
       {
    	   color=JColorChooser.showDialog(null, "选择颜色", Color.black);//显示调色板
    	   color21=color.getRed();
    	   color22=color.getGreen();
    	   color23=color.getBlue();
    	   area2.setFont(font_name2, font_size2, new Color(color21,color22,color23),null, font_style2);
			area2.setText("");
			area2.insert(content_of_area2);
    	   font_changed=true;
       }
   }
}
public void windowOpened(WindowEvent e){} 
public void windowClosing(WindowEvent e)
{
	send("co"+from+name_from+"%"+to+name_to+"$"+ip+"&"+port);
	dispose();
} 
public void windowClosed(WindowEvent e){}
public void windowIconified(WindowEvent e){}
public void windowDeiconified(WindowEvent e){}
public void windowActivated(WindowEvent e){}
public void windowDeactivated(WindowEvent e){}
public static void main(String args[])
{
	chat c=new chat("1111","6263","haha","hahahaha","");
	c.setVisible(true);
}
}
