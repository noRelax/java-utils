import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
/*<p>Title:HappyChat����ϵͳ�������</p>
 *<p>Description:ϵͳ�û���¼���������</p>
 *<p>Copyright:Copyright (C)2006</p>
 * �������أ�http://www.codefans.net
 *<p>Filename:ChatRoom.java</p>
 *@author ��־��
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
	
    //���ڽ��������ڶ�λ
	Dimension scrnsize;
	Toolkit toolkit=Toolkit.getDefaultToolkit();
	//���췽��			
	public ChatRoom(String name,String ip)
	{
	    strServerIp=ip;
	    strLoginName=name;
  		frmChat=new JFrame("������"+"[�û�:"+name+"]");
	    pnlChat=new JPanel(); 
	    frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frmChat.getContentPane().add(pnlChat);

   	    Font fntDisp1=new Font("����",Font.PLAIN,12);
	    //Font fntDisp2=new Font("����",Font.PLAIN,11);
	    
	    mbChat=new JMenuBar();
	    mnuSystem=new JMenu("ϵͳ(S)");
	    mnuSystem.setMnemonic(KeyEvent.VK_S);
	    mnuSystem.setFont(fntDisp1);
	    mnuHelp=new JMenu("����(H)");
	    mnuHelp.setMnemonic(KeyEvent.VK_H);
	    mnuHelp.setFont(fntDisp1);
		mbChat.add(mnuSystem);
		mbChat.add(mnuHelp);
		mnuiCls=new JMenuItem("�����Ļ��ʾ");
		mnuiCls.setFont(fntDisp1);
		mnuiSave=new JMenuItem("���������¼");
		mnuiSave.setFont(fntDisp1);
		mnuiClock=new JMenuItem("�鿴ʱ��");
		mnuiClock.setFont(fntDisp1);
		mnuiExit=new JMenuItem("�˳�ϵͳ");
		mnuiExit.setFont(fntDisp1);
		mnuSystem.add(mnuiCls);
		mnuSystem.add(mnuiSave);
		mnuSystem.add(mnuiClock);
		mnuSystem.add(mnuiExit);
		mnuiContent=new JMenuItem("Ŀ¼");
		mnuiContent.setFont(fntDisp1);
		mnuiIndex=new JMenuItem("����");
		mnuiIndex.setFont(fntDisp1);
		mnuiAbout=new JMenuItem("����[HappyChat����ϵͳ]...");
		mnuiAbout.setFont(fntDisp1);
		mnuHelp.add(mnuiContent);
		mnuHelp.add(mnuiIndex);
		mnuHelp.add(mnuiAbout);
		
		frmChat.setJMenuBar(mbChat);
	    
	    String list[]={"������"};  
	    btnCls=new JButton("����(C)");
	    btnExit=new JButton("�˳�(X)");
	    btnSend=new JButton("����(N)");
	    btnSave=new JButton("����(S)");
	    btnTimer=new JButton("ʱ��(T)");
	    btnSendFile=new JButton("�����ļ�(F)");
	    btnBrowse=new JButton("���...");
	    lblUserList=new JLabel("�������û��б�");
	    lblUserMessage=new JLabel("��������Ϣ��");
	    lblSendMessage=new JLabel("��������:");
	    lblChatUser=new JLabel("���:"); 
	    lblUserTotal=new JLabel("��������:");
	    lblCount=new JLabel("0");
	    lblFile=new JLabel("����:");
	    txtFile=new JTextField(20);
	    lstUserList=new java.awt.List();
	    txtMessage=new JTextField(170);
	    cmbUser         =new JComboBox(list);
	    chPrivateChat =new JCheckBox("˽��");
	    taUserMessage=new TextArea("",300,200,TextArea.SCROLLBARS_VERTICAL_ONLY);//ֻ�����¹���
	    taUserMessage.setEditable(false);   //����д��
	    
	    /*  �ò��ֲ����ֶ�����           *
		 *��setBounds�������λ��        *
		 *  setFont�������塢���͡��ֺ�  *
		 *��setForeground�������ֵ���ɫ  *
		 *  setBackground���ñ���ɫ      *
		 *  setOpaque����������Ϊ͸��    */
	        
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
		taUserMessage.setFont(new Font("����",Font.PLAIN,12));
		
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
	   
	    
	    //��������ҳ����Ϣˢ���߳�
		Thread thread = new Thread(this);
      	thread.start();
	    
//	    Icon log=new ImageIcon("images\\chat.jpg");
//	 	lblBack = new JLabel(log);
//		lblBack.setBounds(1, 1, 600,420);
//		pnlChat.add(lblBack);
		
	    frmChat.setSize(600,440);
	    frmChat.setVisible(true);
	    frmChat.setResizable(false);
	    
	    //�����ڶ�λ����Ļ����
    	scrnsize=toolkit.getScreenSize();
    	frmChat.setLocation(scrnsize.width/2-frmChat.getWidth()/2,
    	                 scrnsize.height/2-frmChat.getHeight()/2);
    	Image img=toolkit.getImage("images\\appico.jpg");
        frmChat.setIconImage(img);
	
	}  //���췽������
	
	public void run()
	{   
	    int intMessageCounter=0;
	    int intUserTotal=0;
	    boolean isFirstLogin=true; //�ж��Ƿ�յ�½
	    boolean isFound;      //�ж��Ƿ��ҵ��û�
	    Vector user_exit=new Vector();
	    try
	    {
			for(;;)
			{
	         	Socket toServer;
		 		toServer=new Socket(strServerIp,1001);
				//����Ϣ����������
	     		Message messobj=new Message();
	    		ObjectOutputStream streamtoserver=new ObjectOutputStream(toServer.getOutputStream());
			    streamtoserver.writeObject((Message)messobj);
			  	//�����Է���������Ϣ
	    		ObjectInputStream streamfromserver=new ObjectInputStream(toServer.getInputStream());
			    messobj=(Message)streamfromserver.readObject();
	 			////////ˢ��������Ϣ�б�//////////
	 			if (isFirstLogin)   //����յ�½
				{
				 	intMessageCounter=messobj.chat.size();   //���θ��û���½ǰ����������
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
			 	     	    temp_message="ϵͳ��ʾ�����벻Ҫ�������"+"\n";
			 	        }
			 	        else
			 	        {
			 	            if (!temp.whisper)   //�������Ļ�
			 	            {
			 	              	temp_message="���㡿�ԡ�"+temp.chatToUser+"��˵��"+temp.chatMessage+"\n";	
			 	            }
			 	            else
			 	            {
			 	                temp_message="���㡿���Ķԡ�"+temp.chatToUser+"��˵��"+temp.chatMessage+"\n";
			 	            }	
			 	        }	
			 	    }
			 	    else
			 	    {
			 	        if(temp.chatToUser.equals(strLoginName))
			 	        {
			 	            if (!temp.whisper)   //�������Ļ�
			 	            {
			 	                temp_message="��"+temp.chatUser+"���ԡ��㡿˵��"+temp.chatMessage+"\n";	
			 	            }
			 	            else
			 	            {
			 	              	temp_message="��"+temp.chatUser+"�����Ķԡ��㡿˵��"+temp.chatMessage+"\n";
			 	            }
			 	        }
			 	        else
			 	        {
			 	            if (!temp.chatUser.equals(temp.chatToUser))  //�Է�û����������
			 	            {
			 	          	    if (!temp.whisper)   //�������Ļ�
			 	          	    {
			 	          	        temp_message="��"+temp.chatUser+"���ԡ�"+temp.chatToUser+"��˵��"+temp.chatMessage+"\n";
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
			   			    
				////////ˢ�������û�//////////	
				lstUserList.clear();
				for (int i=0;i<messobj.userOnLine.size();i++)
				{
	 				String User=(String)messobj.userOnLine.elementAt(i);
					lstUserList.addItem(User);
   				}
    			Integer a=new Integer(messobj.userOnLine.size());
				lblCount.setText(a.toString());
				//��ʾ�û����������ҵ���Ϣ
				if(messobj.userOnLine.size()>intUserTotal)
				{
	    			String tempstr=messobj.userOnLine.elementAt(messobj.userOnLine.size()-1).toString();
	    			if(!tempstr.equals(strLoginName))
					{
						taUserMessage.append("��"+tempstr+"������"+"\n");
					}
				}
   				//��ʾ�û��뿪�����ҵ���Ϣ
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
	    				if(!isFound)  //û�з��ָ��û�
						{
		   					if(!user_exit.elementAt(b).equals(strLoginName))
		    				{
		        				taUserMessage.append("��"+user_exit.elementAt(b)+"������"+"\n");
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
			jop.showMessageDialog(null,"�������ӷ�������");
			System.out.println(e);
		}
	
	}  //run()����
	
	///////////������ť��Ӧ//////////////
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
    	if(source.equals(lstUserList))  //˫���б��
    	{
    		changeUser();
    	}
    } // actionPerformed()����
    
    
    ///////////�������ڹر���Ӧ//////////////
    class Windowclose extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
        	exit();
        }
    }
    
    //"����"��ť
    public void  clearMessage()
    {
        taUserMessage.setText("");	
    }
    
    //"�˳�"��ť
    public void exit()
    {
        Exit exit=new Exit();
        exit.exitname=strLoginName;
        //�����˳���Ϣ
        try
        {
            Socket toServer=new Socket(strServerIp,1001);
            //�������������Ϣ
            ObjectOutputStream outObj=new ObjectOutputStream(toServer.getOutputStream());
            outObj.writeObject(exit);
            outObj.close();
    		toServer.close();
    		frmChat.dispose();
        }
        catch(Exception e)
        {
        }
    
    }  //exit()����
    
    //"����"��ť
    public void sendMessage()
    {
    	Chat chatobj=new Chat();
    	chatobj.chatUser=strLoginName;
    	chatobj.chatMessage=txtMessage.getText();
    	chatobj.chatToUser=String.valueOf(cmbUser.getSelectedItem());
    	chatobj.whisper=chPrivateChat.isSelected()?true:false;
       	//�������������Ϣ
    	try
    	{
    		Socket toServer=new Socket(strServerIp,1001);
    		ObjectOutputStream outObj=new ObjectOutputStream(toServer.getOutputStream());
    		outObj.writeObject(chatobj);
    	    txtMessage.setText("");   //����ı���
    	    outObj.close();
    		toServer.close();
    	}
    	catch(Exception e)
    	{
    	}
    } //sendMessage()����
    
    //"����"��ť
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
    
    //����ѡ�û���ӵ�cmbUser��
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
    
    } //changeUser()����
    
    public static void main(String args[])
    {
    	new ChatRoom("�����û�","127.0.0.1");
    }
    	
}