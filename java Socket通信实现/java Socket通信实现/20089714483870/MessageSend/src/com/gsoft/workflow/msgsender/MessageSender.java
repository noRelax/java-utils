package com.gsoft.workflow.msgsender;

import java.io.*;
import java.net.*;

public class MessageSender {
	public static void main(String[] args) {
		
		String input="";
		String CmdString1="showqueue";  //查看队列内容
		String CmdString2="clearallqueue";  //清空队列
		String CmdString3="quit";  //退出
		String CmdString4="addmsg";  //增加message，addmsg user,time,content,type
		String CmdString5="clearmsg";  //删除某条消息，clearmsg index
		String CmdString6="help";  //查看帮助
		String CmdString7="showrun";  //查看运行情况
		String CmdString8="sendercontrol";  //发送控制，sendercontrol pause 暂停，sendercontrol run 启动
		
		
		try{
	
			MsgQueue aa=new MsgQueue();  //消息队列
			
			SocketServer bb=new SocketServer(aa);  //初始化socket服务类
		
			bb.start();  //启动socket服务
			
			STMsgSender stmsgsender=new STMsgSender(aa);
			
			stmsgsender.start();

			
			while(true)
			{
				//控制台功能调用
				try{ 
					BufferedReader bt= new BufferedReader(new InputStreamReader(System.in)); 
					input=bt.readLine();
					if(input.equals(CmdString1))
					{
						ListQueue(aa);
					}else if(input.equals(CmdString3))
					{
						System.exit(0);
					}else if(input.equals(CmdString6))
					{
						ShowHelp();
					}
				} 
				catch(IOException e)
				{
					System.out.println("err occ!");
				} 


			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("err occ!");
		}
	}
	
	public static void  ListQueue(MsgQueue msgque)
	{
		System.out.println(">Command showqueue executed.");
		System.out.println(">Queue Size is " + msgque.size());
		for(int i=0;i<msgque.size();i++)
			System.out.println(">"+"{"+i+"}"+((MsgPackage)msgque.elementAt(i)).toString());
	}
	
	public static void ShowHelp()
	{
		System.out.println(">Show Help.");
		System.out.println(">1.help           查看帮助");
		System.out.println(">2.showqueue      查看队列内容");
		System.out.println(">3.clearallqueue  清空队列");
		System.out.println(">4.addmsg         添加message，usage:addmsg user,time,content,type");
		System.out.println(">5.clearmsg       删除某条消息，usage:clearmsg index");
		System.out.println(">6.sendercontrol  发送控制，usage:sendercontrol pause 暂停，sendercontrol run 启动");
		System.out.println(">7.showrun        查看运行情况");
		System.out.println(">8.quit           退出");
		
		
	}
}