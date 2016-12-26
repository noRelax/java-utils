package com.gsoft.workflow.msgsender;

public class MsgBuilder {
	private String SendTo;
	private String SendTime;
	private String MsgContent;
	private String MsgType;
	
	public MsgBuilder(String sendto,String sendtime,String msgcontent,String msgtype)
	{
		this.SendTo=sendto;
		this.SendTime=sendtime;
		this.MsgContent=msgcontent;
		this.MsgType=msgtype;
		
	}
	
	public String getMsgPackage()
	{
		String msgpackage;
		msgpackage=SendTo+"|"+SendTime+"|"+MsgContent+"|"+MsgType+"|";
		
		return msgpackage;
		
	}

}
