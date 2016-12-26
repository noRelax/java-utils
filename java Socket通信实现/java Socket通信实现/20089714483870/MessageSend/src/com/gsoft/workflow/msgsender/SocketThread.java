package com.gsoft.workflow.msgsender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.*;

public class SocketThread implements Runnable{
	
    private Socket socket;
    private MsgQueue msgque;
    
    public SocketThread(Socket socket,MsgQueue msgque){
        this.socket=socket;
        this.msgque=msgque;
    }
    private PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream socketOut=socket.getOutputStream();
        return new PrintWriter(socketOut,true);
    }
    private BufferedReader getReader(Socket socket) throws IOException{
        InputStream socketIn=socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }
    
    public void run(){
        try {
            System.out.println("New connection accepted-> "+socket.getInetAddress()+":"+socket.getPort());
            BufferedReader br=getReader(socket);
            PrintWriter pw=getWriter(socket);
            String msg=null;
            MsgPackage msgpackage;
            
            while(!socket.isClosed()&&(msg=br.readLine())!=null){
                System.out.println(msg);
                msgpackage=parsePackage(msg);
                if(msgpackage!=null)
                {
                	msgque.enq(msgpackage);
                	System.out.println(msgpackage.toString());
                }
                
            }
            System.out.println("SocketThread exit.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static MsgPackage parsePackage(String msgstring)
    {
    	String tmpArray[]={"","","","",""};
    	
    	if(msgstring!=null)
    	{
    		tmpArray=msgstring.split("\\|");
    	}
    	if(tmpArray.length<4)
    		return null;
    	
    	MsgPackage msgpackage=new MsgPackage(tmpArray[0],tmpArray[1],tmpArray[2],tmpArray[3]);
    	
    	return msgpackage;
    }

}
