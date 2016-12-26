package com.gsoft.workflow.msgsender;

public class STMsgSender extends Thread{

	MsgQueue msgque;
	int count=0;
	private String sDomain = "";
	private String sUserName = "";
	private String sPassword = "";
	
	public STMsgSender(MsgQueue msgque)
	{
		this.msgque=msgque;
	}
	
	public void run()
	{
		MsgPackage msgpackage;
		MsgSenderThread service = null;
		try{
			service= new MsgSenderThread();
			Configuration rc = new Configuration("st.property");
			
			sDomain = rc.getValue("domain");
			sDomain = (sDomain == null)?"":sDomain.trim();
			
			sUserName = rc.getValue("username");
			sUserName = (sUserName == null)?"":sUserName.trim();
			
			sPassword = rc.getValue("password");
			sPassword = (sPassword == null)?"":sPassword.trim();
			String a = "aaa";
			//service.start("foa3.zktx.com", "robot/zktx", "password");
			service.start(sDomain, sUserName, sPassword);
			Thread.sleep(10000);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//从队列取出message
		while(true)
		{
			
			if(!msgque.empty())
			{
				
				msgpackage=(MsgPackage)msgque.deq();
				//System.out.println("get message package:"+msgpackage.toString());
				try {
					service.setIm(null);
					service.callrevsolve(msgpackage.getSentTo(),msgpackage.getSendTime(), msgpackage.getMsgConetent(),msgpackage.MsgType());
					count=0;
					while(!service.isSent&&count<50)
					{
						Thread.sleep(500);
						count++;
					}
					System.out.println("sented");
				}					
				catch(Exception e)
				{
					e.printStackTrace();
					service.stop();
				}
				
			}else
			{
				//System.out.println("Queue Empty!");
			}
		}
	}
}
