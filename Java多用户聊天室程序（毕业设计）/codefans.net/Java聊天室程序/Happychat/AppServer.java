import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.util.*;
/**
 * <p>Title: HappyChat����ϵͳ����������</p>
 * <p>Description: ���������</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * �������أ�http://www.codefans.net
 * <p>Filename: AppServer.java</p>
 * @author ��־��
 * @version 1.0
 */

//��װ��¼��Ϣ
class Customer implements Serializable
{
	String custName;
	String custPassword;
}

//��װע����Ϣ
class Register_Customer extends Object implements java.io.Serializable
{
     String custName;
     String custPassword;
     String age;
     String sex;
     String email;
}

//���ڷ�������������û�����Ϣ  
class Message implements Serializable
{
  	Vector userOnLine;
  	Vector chat;
}
//������Ϣ���л�
class Chat implements Serializable
{
	String  chatUser;
	String  chatMessage;
	String  chatToUser;
	boolean whisper;
}  
//�˳���Ϣ���л�
class Exit1 implements Serializable
{
    String exitname;	
}


//////////*����������*//////////
public class AppServer extends Thread
{
	ServerSocket serverSocket;
	ServerFrame sFrame;
	static Vector u=new Vector(1,1);
	static Vector v=new Vector(1,1);
	public AppServer()
	{
	 	sFrame=new ServerFrame();
	 	try
	 	{
			serverSocket = new ServerSocket(1001);
			//��ȡ����������������IP��ַ
			InetAddress address = InetAddress.getLocalHost();      
   			sFrame.txtServerName.setText(address.getHostName());
   			sFrame.txtIP.setText(address.getHostAddress());
   			sFrame.txtPort.setText("1001");
		}
		catch(IOException e)
		{
			fail(e,"������������");
		}
		sFrame.txtStatus.setText("������...");
		this.start();    //�����߳�
	}
	
	
	public static void fail(Exception e,String str)
	{
		System.out.println(str+" ��"+e);
	}
	
	
	//////////*�����ͻ�������*//////////
	public void run()
	{
		try
		{
			while(true)
			{
				//���������ܿͻ�������
				Socket client = serverSocket.accept();
			    Connection con = new Connection(client,u,v);   //֧�ֶ��߳�
			}
		}
		catch(IOException e)
		{
			fail(e,"���ܼ�����");
		}
    }
    
    
    //////////*����������*//////////
    public static void main(String args[])
    {
    	new AppServer();
    }
}


//////////*�����߳�*//////////
class Connection extends Thread
{
	protected Socket netClient;
	
	Vector userOnline;
	Vector userChat;
	
	protected ObjectInputStream fromClient;  //�ӿͻ���������
	protected PrintStream toClient; //�����ͻ���
	static Vector  vList = new Vector();
	
	Object obj;
	
	public Connection(Socket client,Vector u,Vector c)
	{
		netClient = client;
		userOnline=u;
		userChat=c;
		
		try
		{
			//����˫��ͨ��
			                                   //�����ͻ�����
			fromClient = new ObjectInputStream(netClient.getInputStream());
						
			                                   //������д���ͻ�
			toClient = new PrintStream(netClient.getOutputStream());
		}
		catch(IOException e)
		{
			try
			{
				netClient.close();
			}
			catch(IOException e1)
			{
				System.out.println("���ܽ�����"+e1);
				return;
			}			
		}
		this.start();
	}
	
	public void run()
	{
	 try
	{//obj��Object��Ķ���
	obj = (Object)fromClient.readObject();
			if(obj.getClass().getName().equals("Customer"))
			{
			    serverLogin();	
			}
			if(obj.getClass().getName().equals("Register_Customer"))
			{
			    serverRegiste();	
			}
		    if(obj.getClass().getName().equals("Message"))
		    {
		        serverMessage();
		    }
		    if(obj.getClass().getName().equals("Chat"))
		    {
		        serverChat();
		    }
		    if(obj.getClass().getName().equals("Exit1"))
		    {
		        serverExit();	
		    }	
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(ClassNotFoundException e1)
		{
			System.out.println("������������"+e1);
		}
		finally
		{
			try
			{
				netClient.close();
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
	}
	
	/**********��¼����**********/
	public void serverLogin()
	{
	    
	    try
	    {
	    Customer clientMessage2 = (Customer)obj;
	    			    	
	            //���ļ�
            FileInputStream file3 =new FileInputStream("user.txt");
		    ObjectInputStream objInput1 = new ObjectInputStream(file3);
		    vList=(Vector)objInput1.readObject(); 
			    	
		    int find=0;  //�����жϱ�־
		    System.out.println(find);
		    for(int i=0;i<vList.size();i++)
		    {     
		       Register_Customer reg=(Register_Customer)vList.elementAt(i);
		          
		       if ( reg.custName.equals(clientMessage2.custName) )
		       {
		           find=1; 
		           if( !reg.custPassword.equals(clientMessage2.custPassword) )
		           {
		      	        toClient.println("���벻��ȷ");
		      	        break;
		           }
		           else
		           {
		      	        //�ж��Ƿ��Ѿ���¼
		      	        int login_flag=0;
		      	        for(int a=0;a<userOnline.size();a++)
		      	        {
		      	            if(	clientMessage2.custName.equals(userOnline.elementAt(a)))
		      	            {
		      	            	login_flag=1;
		      	            	break;
		      	            }
		      	        }
		      	        
		      	        if (login_flag==0)
		      	        {
		      	            userOnline.addElement(clientMessage2.custName);  //�����û�������
		      	            toClient.println("��¼�ɹ�");
		      	            Date t=new Date();
		      	            System.out.println("�û�"+clientMessage2.custName+"��¼�ɹ���"+
		      	                               "��¼ʱ��:"+t.toLocaleString()+"\n");
		       	            break;
		       	        }
		       	        else
		       	        {
		       	            toClient.println("���û��ѵ�¼");
		       	        }
		           } 
		       }
		       else
		       {
		           continue;	
		       }    
		    }
		    if (find == 0)
		    {
		  	    toClient.println("û������û�������ע��");
	        }
	        
	        file3.close();
		    objInput1.close();
		    fromClient.close();	
	    }
	    catch(ClassNotFoundException e)
  		{
  			System.out.println(e);
  		}
  		catch(IOException e)
  		{
  			System.out.println(e);
  		}
	}
	
	/**********ע�ᴦ��**********/
     public void serverRegiste()
    {
     try
     {
       	int flag=0;  //�Ƿ������жϱ�־
		Register_Customer clientMessage =(Register_Customer)obj;
       	File fList=new File("user.txt");
      	if(fList.length()!= 0)//�ж��Ƿ��ǵ�һ��ע���û�
      	{
        	ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(fList));
			vList=(Vector)objInput.readObject(); 
			//�ж��Ƿ�������
			for(int i=0;i<vList.size();i++)
			{
	 			Register_Customer reg=(Register_Customer)vList.elementAt(i);
     			if(reg.custName.equals(clientMessage.custName))
         		{
          			toClient.println("ע�����ظ�,������ѡ��");
           			flag=1;
             		break;
          		}
			}
       }
      if (flag==0)
      {
	//�����ע���û�
	vList.addElement(clientMessage);
	//�������е���д���ļ�
	FileOutputStream file = new FileOutputStream(fList);
	ObjectOutputStream objout = new ObjectOutputStream(file);
	objout.writeObject(vList);
	     
	//����ע��ɹ���Ϣ
		        toClient.println(clientMessage.custName+"ע��ɹ�");
		        Date t=new Date();
		        //append("�û�"+clientMessage.custName+"ע��ɹ�!;ע��ʱ��:"+t.toLocaleString()+"\n");
		        System.out.println("�û�"+clientMessage.custName+"ע��ɹ�, "+
		                           "ע��ʱ��:"+t.toLocaleString()+"\n");
				    
		        file.close();
		        objout.close();
		        fromClient.close();
		    }
	    }
	    catch(ClassNotFoundException e)
  		{
  			System.out.println(e);
  		}
  		catch(IOException e)
  		{
  			System.out.println(e);
  		}
    }
    
    /**********������Ϣ����**********/
    public void serverMessage()
    {
        try
        {
        	Message mess=new Message();
            mess.userOnLine=userOnline;
            mess.chat=userChat;
        
            ObjectOutputStream outputstream=new ObjectOutputStream(netClient.getOutputStream());
            outputstream.writeObject((Message)mess);
            
            netClient.close();
            outputstream.close();
        }    
        catch(IOException e)
  	  	{
  	  	}
        
    }
    
    /**********������Ϣ����**********/
	public void serverChat()
  	{
  		//�����յ��Ķ���ֵ����������Ϣ�����л�����
  		Chat cObj = new Chat();
  		cObj = (Chat)obj;
  		
  		//��������Ϣ�����л�������ӵ�����������Ϣ��ʸ����
  		userChat.addElement((Chat)cObj);
  		return;
  	}	   
  	
  	/**********�û��˳�����**********/
  	public void serverExit()
  	{
  		Exit1  exit1=new Exit1();
  		exit1=(Exit1)obj;
  		  		
  		userOnline.removeElement(exit1.exitname);  //�����û�ɾ��
  		Date t=new Date();
  		
  		System.out.println("�û�"+exit1.exitname+"�Ѿ��˳�, "+
  		                   "�˳�ʱ��:"+t.toLocaleString()+"\n");
  	}
}