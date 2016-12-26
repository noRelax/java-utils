package com.gsoft.workflow.msgsender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.*;


public class SocketServer extends Thread{

	private int port=8821;
    private ServerSocket serverSocket;
    private ExecutorService executorService;//线程池
    private final int POOL_SIZE=10;//单个CPU线程池大小
    private MsgQueue msgque;
    private User user;
    private String sUserName = "";
    private String sPassword = "";
    private String sDomain = "";
    
    public SocketServer(MsgQueue msgque) throws IOException{
    	
    	this.msgque=msgque;
        serverSocket=new ServerSocket(port);
        //Runtime的availableProcessor()方法返回当前系统的CPU数目.
        executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
        System.out.println("Listener is open! Current Thread Max is "+Runtime.getRuntime().availableProcessors()*POOL_SIZE);
        
    }
    public void run(){
        while(true){
            Socket socket=null;
            try {
                //接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
                socket=serverSocket.accept();
                executorService.execute(new SocketThread(socket,msgque));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
