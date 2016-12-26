import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.util.*;
/**
 * <p>Title: HappyChat聊天系统服务器程序</p>
 * <p>Description: 聊天服务器</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * 友情下载：http://www.codefans.net
 * <p>Filename: AppServer.java</p>
 * @author 刘志成
 * @version 1.0
 */

//封装登录信息
class Customer implements Serializable
{
	String custName;
	String custPassword;
}

//封装注册信息
class Register_Customer extends Object implements java.io.Serializable
{
     String custName;
     String custPassword;
     String age;
     String sex;
     String email;
}

//用于发送聊天和在线用户的信息  
class Message implements Serializable
{
  	Vector userOnLine;
  	Vector chat;
}
//聊天信息序列化
class Chat implements Serializable
{
	String  chatUser;
	String  chatMessage;
	String  chatToUser;
	boolean whisper;
}  
//退出信息序列化
class Exit1 implements Serializable
{
    String exitname;	
}


//////////*创建服务器*//////////
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
			//获取服务器的主机名和IP地址
			InetAddress address = InetAddress.getLocalHost();      
   			sFrame.txtServerName.setText(address.getHostName());
   			sFrame.txtIP.setText(address.getHostAddress());
   			sFrame.txtPort.setText("1001");
		}
		catch(IOException e)
		{
			fail(e,"不能启动服务！");
		}
		sFrame.txtStatus.setText("已启动...");
		this.start();    //启动线程
	}
	
	
	public static void fail(Exception e,String str)
	{
		System.out.println(str+" 。"+e);
	}
	
	
	//////////*监听客户的请求*//////////
	public void run()
	{
		try
		{
			while(true)
			{
				//监听并接受客户的请求
				Socket client = serverSocket.accept();
			    Connection con = new Connection(client,u,v);   //支持多线程
			}
		}
		catch(IOException e)
		{
			fail(e,"不能监听！");
		}
    }
    
    
    //////////*启动服务器*//////////
    public static void main(String args[])
    {
    	new AppServer();
    }
}


//////////*处理线程*//////////
class Connection extends Thread
{
	protected Socket netClient;
	
	Vector userOnline;
	Vector userChat;
	
	protected ObjectInputStream fromClient;  //从客户到服务器
	protected PrintStream toClient; //传导客户端
	static Vector  vList = new Vector();
	
	Object obj;
	
	public Connection(Socket client,Vector u,Vector c)
	{
		netClient = client;
		userOnline=u;
		userChat=c;
		
		try
		{
			//发生双向通信
			                                   //检索客户输入
			fromClient = new ObjectInputStream(netClient.getInputStream());
						
			                                   //服务器写到客户
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
				System.out.println("不能建立流"+e1);
				return;
			}			
		}
		this.start();
	}
	
	public void run()
	{
	 try
	{//obj是Object类的对象
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
			System.out.println("读对象发生错误！"+e1);
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
	
	/**********登录处理**********/
	public void serverLogin()
	{
	    
	    try
	    {
	    Customer clientMessage2 = (Customer)obj;
	    			    	
	            //读文件
            FileInputStream file3 =new FileInputStream("user.txt");
		    ObjectInputStream objInput1 = new ObjectInputStream(file3);
		    vList=(Vector)objInput1.readObject(); 
			    	
		    int find=0;  //查找判断标志
		    System.out.println(find);
		    for(int i=0;i<vList.size();i++)
		    {     
		       Register_Customer reg=(Register_Customer)vList.elementAt(i);
		          
		       if ( reg.custName.equals(clientMessage2.custName) )
		       {
		           find=1; 
		           if( !reg.custPassword.equals(clientMessage2.custPassword) )
		           {
		      	        toClient.println("密码不正确");
		      	        break;
		           }
		           else
		           {
		      	        //判断是否已经登录
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
		      	            userOnline.addElement(clientMessage2.custName);  //将该用户名何在
		      	            toClient.println("登录成功");
		      	            Date t=new Date();
		      	            System.out.println("用户"+clientMessage2.custName+"登录成功，"+
		      	                               "登录时间:"+t.toLocaleString()+"\n");
		       	            break;
		       	        }
		       	        else
		       	        {
		       	            toClient.println("该用户已登录");
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
		  	    toClient.println("没有这个用户，请先注册");
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
	
	/**********注册处理**********/
     public void serverRegiste()
    {
     try
     {
       	int flag=0;  //是否重名判断标志
		Register_Customer clientMessage =(Register_Customer)obj;
       	File fList=new File("user.txt");
      	if(fList.length()!= 0)//判断是否是第一个注册用户
      	{
        	ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(fList));
			vList=(Vector)objInput.readObject(); 
			//判断是否有重名
			for(int i=0;i<vList.size();i++)
			{
	 			Register_Customer reg=(Register_Customer)vList.elementAt(i);
     			if(reg.custName.equals(clientMessage.custName))
         		{
          			toClient.println("注册名重复,请另外选择");
           			flag=1;
             		break;
          		}
			}
       }
      if (flag==0)
      {
	//添加新注册用户
	vList.addElement(clientMessage);
	//将向量中的类写回文件
	FileOutputStream file = new FileOutputStream(fList);
	ObjectOutputStream objout = new ObjectOutputStream(file);
	objout.writeObject(vList);
	     
	//发送注册成功信息
		        toClient.println(clientMessage.custName+"注册成功");
		        Date t=new Date();
		        //append("用户"+clientMessage.custName+"注册成功!;注册时间:"+t.toLocaleString()+"\n");
		        System.out.println("用户"+clientMessage.custName+"注册成功, "+
		                           "注册时间:"+t.toLocaleString()+"\n");
				    
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
    
    /**********发送信息处理**********/
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
    
    /**********增加信息处理**********/
	public void serverChat()
  	{
  		//将接收到的对象值赋给聊天信息的序列化对象
  		Chat cObj = new Chat();
  		cObj = (Chat)obj;
  		
  		//将聊天信息的序列化对象填加到保存聊天信息的矢量中
  		userChat.addElement((Chat)cObj);
  		return;
  	}	   
  	
  	/**********用户退出处理**********/
  	public void serverExit()
  	{
  		Exit1  exit1=new Exit1();
  		exit1=(Exit1)obj;
  		  		
  		userOnline.removeElement(exit1.exitname);  //将该用户删除
  		Date t=new Date();
  		
  		System.out.println("用户"+exit1.exitname+"已经退出, "+
  		                   "退出时间:"+t.toLocaleString()+"\n");
  	}
}