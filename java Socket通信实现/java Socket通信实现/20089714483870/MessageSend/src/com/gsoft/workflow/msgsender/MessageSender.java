package com.gsoft.workflow.msgsender;

import java.io.*;
import java.net.*;

public class MessageSender {
	public static void main(String[] args) {
		
		String input="";
		String CmdString1="showqueue";  //�鿴��������
		String CmdString2="clearallqueue";  //��ն���
		String CmdString3="quit";  //�˳�
		String CmdString4="addmsg";  //����message��addmsg user,time,content,type
		String CmdString5="clearmsg";  //ɾ��ĳ����Ϣ��clearmsg index
		String CmdString6="help";  //�鿴����
		String CmdString7="showrun";  //�鿴�������
		String CmdString8="sendercontrol";  //���Ϳ��ƣ�sendercontrol pause ��ͣ��sendercontrol run ����
		
		
		try{
	
			MsgQueue aa=new MsgQueue();  //��Ϣ����
			
			SocketServer bb=new SocketServer(aa);  //��ʼ��socket������
		
			bb.start();  //����socket����
			
			STMsgSender stmsgsender=new STMsgSender(aa);
			
			stmsgsender.start();

			
			while(true)
			{
				//����̨���ܵ���
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
		System.out.println(">1.help           �鿴����");
		System.out.println(">2.showqueue      �鿴��������");
		System.out.println(">3.clearallqueue  ��ն���");
		System.out.println(">4.addmsg         ���message��usage:addmsg user,time,content,type");
		System.out.println(">5.clearmsg       ɾ��ĳ����Ϣ��usage:clearmsg index");
		System.out.println(">6.sendercontrol  ���Ϳ��ƣ�usage:sendercontrol pause ��ͣ��sendercontrol run ����");
		System.out.println(">7.showrun        �鿴�������");
		System.out.println(">8.quit           �˳�");
		
		
	}
}