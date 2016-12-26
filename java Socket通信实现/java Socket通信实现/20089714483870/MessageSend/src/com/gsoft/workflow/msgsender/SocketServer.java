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
    private ExecutorService executorService;//�̳߳�
    private final int POOL_SIZE=10;//����CPU�̳߳ش�С
    private MsgQueue msgque;
    private User user;
    private String sUserName = "";
    private String sPassword = "";
    private String sDomain = "";
    
    public SocketServer(MsgQueue msgque) throws IOException{
    	
    	this.msgque=msgque;
        serverSocket=new ServerSocket(port);
        //Runtime��availableProcessor()�������ص�ǰϵͳ��CPU��Ŀ.
        executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
        System.out.println("Listener is open! Current Thread Max is "+Runtime.getRuntime().availableProcessors()*POOL_SIZE);
        
    }
    public void run(){
        while(true){
            Socket socket=null;
            try {
                //���տͻ�����,ֻҪ�ͻ�����������,�ͻᴥ��accept();�Ӷ���������
                socket=serverSocket.accept();
                executorService.execute(new SocketThread(socket,msgque));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
