import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
/*<p>Title:HappyChat聊天系统聊天程序</p>
 *<p>Description:系统用户登录后进行聊天</p>
 *<p>Copyright:Copyright (C)2006</p>
 * 友情下载：http://www.codefans.net
 *<p>Filename:ChatRoom.java</p>
 *@author 刘志成
 *@version 1.0
 */
	
public class ChatRoom extends Thread implements ActionListener
{
	static JFrame frmChat;
	JPanel  pnlChat;
	JButton  btnCls,btnExit,btnSend,btnClear,btnSave,btnTimer,btnSendFile,btnBrowse;
	JLabel  lblUserList,lblUserMessage,lblSendMessage,lblChatUser;
	JLabel  lblUserTotal,lblCount,lblBack,lblFile;
	JTextField txtMessage,txtFile;
	java.awt.List  lstUserList;
	TextArea  taUserMessage;
	JComboBox  cmbUser;
	JCheckBox  chPrivateChat;
	String  strServerIp,strLoginName;
	Thread  thread;
	JMenuBar mbChat;
	JMenu mnuSystem,mnuHelp;
	JMenuItem mnuiCls,mnuiSave,mnuiClock,mnuiExit,mnuiContent,mnuiIndex,mnuiAbout;
	
    //用于将窗口用于定位
	Dimension scrnsize;
	Toolkit toolkit=Toolkit.getDefaultToolkit();
	//构造方法			
	public ChatRoom(String name,String ip)
	{
	    strServerIp=ip;
	    strLoginName=name;
  		frmChat=new JFrame("聊天室"+"[用户:"+name+"]");
	    pnlChat=new JPanel(); 
	    frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frmChat.getContentPane().add(pnlChat);

   	    Font fntDisp1=new Font("宋体",Font.PLAIN,12);
	    //Font fntDisp2=new Font("宋体",Font.PLAIN,11);
	    
	    mbChat=new JMenuBar();
	    mnuSystem=new JMenu("系统(S)");
	    mnuSystem.setMnemonic(KeyEvent.VK_S);
	    mnuSystem.setFont(fntDisp1);
	    mnuHelp=new JMenu("帮助(H)");
	    mnuHelp.setMnemonic(KeyEvent.VK_H);
	    mnuHelp.setFont(fntDisp1);
		mbChat.add(mnuSystem);
		mbChat.add(mnuHelp);
		mnuiCls=new JMenuItem("清除屏幕显示");
		mnuiCls.setFont(fntDisp1);
		mnuiSave=new JMenuItem("保存聊天记录");
		mnuiSave.setFont(fntDisp1);
		mnuiClock=new JMenuItem("查看时间");
		mnuiClock.setFont(fntDisp1);
		mnuiExit=new JMenuItem("退出系统");
		mnuiExit.setFont(fntDisp1);
		mnuSystem.add(mnuiCls);
		mnuSystem.add(mnuiSave);
		mnuSystem.add(mnuiClock);
		mnuSystem.add(mnuiExit);
		mnuiContent=new JMenuItem("目录");
		mnuiContent.setFont(fntDisp1);
		mnuiIndex=new JMenuItem("索引");
		mnuiIndex.setFont(fntDisp1);
		mnuiAbout=new JMenuItem("关于[HappyChat聊天系统]...");
		mnuiAbout.setFont(fntDisp1);
		mnuHelp.add(mnuiContent);
		mnuHelp.add(mnuiIndex);
		mnuHelp.add(mnuiAbout);
		
		frmChat.setJMenuBar(mbChat);
	    
	    String list[]={"所有人"};  
	    btnCls=new JButton("清屏(C)");
	    btnExit=new JButton("退出(X)");
	    btnSend=new JButton("发送(N)");
	    btnSave=new JButton("保存(S)");
	    btnTimer=new JButton("时钟(T)");
	    btnSendFile=new JButton("发送文件(F)");
	    btnBrowse=new JButton("浏览...");
	    lblUserList=new JLabel("【在线用户列表】");
	    lblUserMessage=new JLabel("【聊天信息】");
	    lblSendMessage=new JLabel("聊天内容:");
	    lblChatUser=new JLabel("你对:"); 
	    lblUserTotal=new JLabel("在线人数:");
	    lblCount=new JLabel("0");
	    lblFile=new JLabel("附件:");
	    txtFile=new JTextField(20);
	    lstUserList=new java.awt.List();
	    txtMessage=new JTextField(170);
	    cmbUser         =new JComboBox(list);
	    chPrivateChat =new JCheckBox("私聊");
	    taUserMessage=new TextArea("",300,200,TextArea.SCROLLBARS_VERTICAL_ONLY);//只能向下滚动
	    taUserMessage.setEditable(false);   //不可写入
	    
	    /*  该布局采用手动布局           *
		 *　setBounds设置组件位置        *
		 *  setFont设置字体、字型、字号  *
		 *　setForeground设置文字的颜色  *
		 *  setBackground设置背景色      *
		 *  setOpaque将背景设置为透明    */
	        
	    pnlChat.setLayout(null);
		pnlChat.setBackground(new Color(52,130,203));
		btnSendFile.setBounds(250,360,100,25);
		btnBrowse.setBounds(170,360,80,25);
		btnTimer.setBounds(400,360,80,25);
		btnSave.setBounds(500,330,80,25);
	    btnCls.setBounds(400,330,80,25);
	    btnExit.setBounds(500,360,80,25);
	    btnSend.setBounds(500,300,80,25);
	    
	    lblFile.setBounds(10,330,40,25);
	    lblUserList.setBounds(5,0,120,40);
   	    lblUserTotal.setBounds(130,0,60,40);
	    lblCount.setBounds(190,0,60,40);
	    lblUserMessage.setBounds(225,0,180,40);
	    lblChatUser.setBounds(10,290,40,40);
	    lblSendMessage.setBounds(210,290,60,40);

	    //lblUserTotal.setBounds(10,340,100,40);
	    //lblCount.setBounds(73,340,100,40);
	    txtFile.setBounds(50,330,300,25);
	    lstUserList.setBounds(5,40,210,255);
	    taUserMessage.setBounds(225,40,360,255);
	    txtMessage.setBounds(270,300,210,25);
	    cmbUser.setBounds(50,300,80,25);
	    chPrivateChat.setBounds(135,302,60,20);
	    
	    btnSendFile.setFont(fntDisp1);
	    btnBrowse.setFont(fntDisp1);
	    btnTimer.setFont(fntDisp1);
		btnCls.setFont(fntDisp1);
		btnExit.setFont(fntDisp1);
		btnSend.setFont(fntDisp1);
		btnSave.setFont(fntDisp1);
		lblFile.setFont(fntDisp1);
		lblUserList.setFont(fntDisp1);
		lblUserMessage.setFont(fntDisp1);
		lblChatUser.setFont(fntDisp1);
		lblSendMessage.setFont(fntDisp1);
		lblUserTotal.setFont(fntDisp1);
		lblCount.setFont(fntDisp1);
		txtFile.setFont(fntDisp1);
		cmbUser.setFont(fntDisp1);
		chPrivateChat.setFont(fntDisp1);
		taUserMessage.setFont(new Font("宋体",Font.PLAIN,12));
		
		lblUserList.setForeground(Color.YELLOW);
		lblUserMessage.setForeground(Color.YELLOW);
		lblSendMessage.setForeground(Color.black);
		lblChatUser.setForeground(Color.black);
		lblSendMessage.setForeground(Color.black);
		lblUserTotal.setForeground(Color.YELLOW);
		lblCount.setForeground(Color.YELLOW);
		cmbUser.setForeground(Color.black);
		chPrivateChat.setForeground(Color.black);
		lstUserList.setBackground(Color.white);
		taUserMessage.setBackground(Color.white);
		btnBrowse.setBackground(Color.PINK);
		btnSendFile.setBackground(Color.PINK);
		btnTimer.setBackground(Color.ORANGE);
		btnCls.setBackground(Color.ORANGE);
	    btnExit.setBackground(Color.ORANGE);
	    btnSend.setBackground(Color.PINK);
	    btnSave.setBackground(Color.ORANGE);
		
		pnlChat.add(lblFile);
		pnlChat.add(txtFile);
		pnlChat.add(btnSendFile);
		pnlChat.add(btnBrowse);
		pnlChat.add(btnTimer);
		pnlChat.add(btnCls);
	    pnlChat.add(btnExit);
	    pnlChat.add(btnSend);
	    pnlChat.add(btnSave);
	    pnlChat.add(lblUserList);
	    pnlChat.add(lblUserMessage);
	    pnlChat.add(lblSendMessage);
	    pnlChat.add(lblChatUser);
	    pnlChat.add(lblUserTotal);
	    pnlChat.add(lblCount);
	    pnlChat.add(lstUserList);
	    pnlChat.add(taUserMessage);
	    pnlChat.add(txtMessage);
	    pnlChat.add(cmbUser);
	    pnlChat.add(chPrivateChat);
	    
	    frmChat.addWindowListener(new Windowclose());
	    btnTimer.addActionListener(this);
	    btnCls.addActionListener(this);
	    btnExit.addActionListener(this);
	    btnSend.addActionListener(this);
	    btnSave.addActionListener(this);
	    lstUserList.addActionListener(this);
	    txtMessage.addActionListener(this);
	   
	    
	    //启动聊天页面信息刷新线程
		Thread thread = new Thread(this);
      	thread.start();
	    
//	    Icon log=new ImageIcon("images\\chat.jpg");
//	 	lblBack = new JLabel(log);
//		lblBack.setBounds(1, 1, 600,420);
//		pnlChat.add(lblBack);
		
	    frmChat.setSize(600,440);
	    frmChat.setVisible(true);
	    frmChat.setResizable(false);
	    
	    //将窗口定位在屏幕中央
    	scrnsize=toolkit.getScreenSize();
    	frmChat.setLocation(scrnsize.width/2-frmChat.getWidth()/2,
    	                 scrnsize.height/2-frmChat.getHeight()/2);
    	Image img=toolkit.getImage("images\\appico.jpg");
        frmChat.setIconImage(img);
	
	}  //构造方法结束
	
	public void run()
	{   
	    int intMessageCounter=0;
	    int intUserTotal=0;
	    boolean isFirstLogin=true; //判断是否刚登陆
	    boolean isFound;      //判断是否找到用户
	    Vector user_exit=new Vector();
	    try
	    {
			for(;;)
			{
	         	Socket toServer;
		 		toServer=new Socket(strServerIp,1001);
				//将信息发往服务器
	     		Message messobj=new Message();
	    		ObjectOutputStream streamtoserver=new ObjectOutputStream(toServer.getOutputStream());
			    streamtoserver.writeObject((Message)messobj);
			  	//收来自服务器的信息
	    		ObjectInputStream streamfromserver=new ObjectInputStream(toServer.getInputStream());
			    messobj=(Message)streamfromserver.readObject();
	 			////////刷新聊天信息列表//////////
	 			if (isFirstLogin)   //如果刚登陆
				{
				 	intMessageCounter=messobj.chat.size();   //屏蔽该用户登陆前的聊天内容
				 	isFirstLogin=false;
				}
				//  taUserMessage.setText("");
				for (int i=intMessageCounter;i<messobj.chat.size();i++)
				{
   					Chat temp=(Chat)messobj.chat.elementAt(i);
    				String temp_message;
   					if (temp.chatUser.equals(strLoginName))
   					{
		 	        	if(temp.chatToUser.equals(strLoginName))
			 	        {
			 	     	    temp_message="系统提示您：请不要自言自语！"+"\n";
			 	        }
			 	        else
			 	        {
			 	            if (!temp.whisper)   //不是悄悄话
			 	            {
			 	              	temp_message="【你】对【"+temp.chatToUser+"】说："+temp.chatMessage+"\n";	
			 	            }
			 	            else
			 	            {
			 	                temp_message="【你】悄悄对【"+temp.chatToUser+"】说："+temp.chatMessage+"\n";
			 	            }	
			 	        }	
			 	    }
			 	    else
			 	    {
			 	        if(temp.chatToUser.equals(strLoginName))
			 	        {
			 	            if (!temp.whisper)   //不是悄悄话
			 	            {
			 	                temp_message="【"+temp.chatUser+"】对【你】说："+temp.chatMessage+"\n";	
			 	            }
			 	            else
			 	            {
			 	              	temp_message="【"+temp.chatUser+"】悄悄对【你】说："+temp.chatMessage+"\n";
			 	            }
			 	        }
			 	        else
			 	        {
			 	            if (!temp.chatUser.equals(temp.chatToUser))  //对方没有自言自语
			 	            {
			 	          	    if (!temp.whisper)   //不是悄悄话
			 	          	    {
			 	          	        temp_message="【"+temp.chatUser+"】对【"+temp.chatToUser+"】说："+temp.chatMessage+"\n";
			 	           	    }
			 	           	    else
			 	           	    {
			 	           	    	temp_message="";
			 	           	    }
			 	            }
			 	            else
			 	            {
			 	          	    temp_message="";
			 	            }
			 	         }
			 	     }
			 	     taUserMessage.append(temp_message);
			 	     intMessageCounter++;
			    } 
			   			    
				////////刷新在线用户//////////	
				lstUserList.clear();
				for (int i=0;i<messobj.userOnLine.size();i++)
				{
	 				String User=(String)messobj.userOnLine.elementAt(i);
					lstUserList.addItem(User);
   				}
    			Integer a=new Integer(messobj.userOnLine.size());
				lblCount.setText(a.toString());
				//显示用户进入聊天室的信息
				if(messobj.userOnLine.size()>intUserTotal)
				{
	    			String tempstr=messobj.userOnLine.elementAt(messobj.userOnLine.size()-1).toString();
	    			if(!tempstr.equals(strLoginName))
					{
						taUserMessage.append("【"+tempstr+"】来了"+"\n");
					}
				}
   				//显示用户离开聊天室的信息
   				if(messobj.userOnLine.size()<intUserTotal)
   				{
   	    			for(int b=0;b<user_exit.size();b++)
   	    			{
   	        			isFound=false;
   	        			for(int c=0;c<messobj.userOnLine.size();c++)
   	        			{	
   	            			if(user_exit.elementAt(b).equals(messobj.userOnLine.elementAt(c)))
   	            			{
								isFound=true;
			   					break;
			   				}
   		    			} 
	    				if(!isFound)  //没有发现该用户
						{
		   					if(!user_exit.elementAt(b).equals(strLoginName))
		    				{
		        				taUserMessage.append("【"+user_exit.elementAt(b)+"】走了"+"\n");
		    				}
						}
					}	
				}
				user_exit=messobj.userOnLine;			   	
				intUserTotal=messobj.userOnLine.size();
    			streamtoserver.close();
    			streamfromserver.close();
    			toServer.close();
    			thread.sleep(1000);
			}
		    
		}
		catch (Exception e)
		{
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null,"不能连接服务器！");
			System.out.println(e);
		}
	
	}  //run()结束
	
	///////////监听按钮响应//////////////
    public void actionPerformed(ActionEvent ae)
    {
    	Object source=(Object)ae.getSource();
    	if (source.equals(btnTimer))
    	{
    		new Clock();
    	}
    	if(source.equals(btnCls))
    	{
    	    clearMessage();
    	}
    	if(source.equals(btnExit))
    	{
    	    exit();
    	}
    	if(source.equals(btnSend))
    	{
    		sendMessage();
    	}
    	if(source.equals(btnSave))
    	{
    	    saveMessage();	
    	}
    	if(source.equals(lstUserList))  //双击列表框
    	{
    		changeUser();
    	}
    } // actionPerformed()结束
    
    
    ///////////监听窗口关闭响应//////////////
    class Windowclose extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
        	exit();
        }
    }
    
    //"清屏"按钮
    public void  clearMessage()
    {
        taUserMessage.setText("");	
    }
    
    //"退出"按钮
    public void exit()
    {
        Exit exit=new Exit();
        exit.exitname=strLoginName;
        //发送退出信息
        try
        {
            Socket toServer=new Socket(strServerIp,1001);
            //向服务器发送信息
            ObjectOutputStream outObj=new ObjectOutputStream(toServer.getOutputStream());
            outObj.writeObject(exit);
            outObj.close();
    		toServer.close();
    		frmChat.dispose();
        }
        catch(Exception e)
        {
        }
    
    }  //exit()结束
    
    //"发送"按钮
    public void sendMessage()
    {
    	Chat chatobj=new Chat();
    	chatobj.chatUser=strLoginName;
    	chatobj.chatMessage=txtMessage.getText();
    	chatobj.chatToUser=String.valueOf(cmbUser.getSelectedItem());
    	chatobj.whisper=chPrivateChat.isSelected()?true:false;
       	//向服务器发送信息
    	try
    	{
    		Socket toServer=new Socket(strServerIp,1001);
    		ObjectOutputStream outObj=new ObjectOutputStream(toServer.getOutputStream());
    		outObj.writeObject(chatobj);
    	    txtMessage.setText("");   //清空文本框
    	    outObj.close();
    		toServer.close();
    	}
    	catch(Exception e)
    	{
    	}
    } //sendMessage()结束
    
    //"保存"按钮
    public void saveMessage()
    {
    	try
    	{
    		FileOutputStream  fileoutput=new FileOutputStream("message.txt",true);
    	    String temp=taUserMessage.getText();
    	    System.out.println(temp);
    	    fileoutput.write(temp.getBytes());
    	    fileoutput.close();
        }
        catch(Exception e)
        {
        	System.out.println(e);
        }
        
    }
    
    //将所选用户添加到cmbUser中
  	public void changeUser()
	{
		boolean key = true;
		String selected = lstUserList.getSelectedItem();
		
		for(int i = 0; i < cmbUser.getItemCount(); i++)
		{
		   if(selected.equals(cmbUser.getItemAt(i)))
	       {
			   key = false;
		       break;
		   }
		}
		if(key == true)
		{
		   cmbUser.insertItemAt(selected,0);
		}
		cmbUser.setSelectedItem(selected);
    
    } //changeUser()结束
    
    public static void main(String args[])
    {
    	new ChatRoom("测试用户","127.0.0.1");
    }
    	
}