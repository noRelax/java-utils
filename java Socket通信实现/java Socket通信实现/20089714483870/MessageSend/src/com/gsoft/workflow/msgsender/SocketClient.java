package com.gsoft.workflow.msgsender;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;

public class SocketClient {

	/**
	 * @param args
	 */
	public String hostIP = "192.168.0.200";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int numTasks = 10;
        
        SocketClient sc = new SocketClient();
		//ExecutorService exec = Executors.newCachedThreadPool();
		sc.hostIP= "192.168.0.119";
		sc.sent("admin","一条新的代办公文");
	}
	
	public void sent(String sendTo, String msg) {
		
		 Socket socket = null;
         int port = 8821;
         String currTime = null;
         //String str = "";
     	//用户信息与Token服务
         //UserInfo m_imUsers = new UserInfo();
         //Tokens m_tokens = new Tokens();
         
         //产生TokenId
         //m_imUsers.setToken(sendTo, null);
		 //m_tokens.generateToken(m_imUsers.getStUser(sendTo));
         //str = str + "<a href='http://st8.ibm.com/servlet/SSORedirect?LtpaToken="+m_imUsers.getToken(sendTo).getTokenString();
         
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
         currTime = formatter.format(new Date())+" "+formatter1.format(new Date());
         
        try {                    
            socket = new Socket(hostIP, port);
            //int taskID = 1000000;
            
            PrintStream socketOut = new PrintStream(socket.getOutputStream());
                                
            MsgBuilder msgb=new MsgBuilder(sendTo,currTime,msg,"信息");
            
            System.out.println(msgb.getMsgPackage());
            
            socketOut.println(msgb.getMsgPackage());
            
            socket.close();

        } catch (IOException e) {                    
            e.printStackTrace();
        }
    }


}
