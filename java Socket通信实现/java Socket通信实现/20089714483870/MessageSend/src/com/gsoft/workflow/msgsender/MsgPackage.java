package com.gsoft.workflow.msgsender;

public class MsgPackage {
	private String SendTo;
	private String SendTime;
	private String MsgContent;
	private String MsgType;
	
	public MsgPackage()
	{
		SendTo="";
		SendTime="";
		MsgContent="";
		MsgType="";
		
	}
	
	public MsgPackage(String sendto,String sendtime,String msgcontent,String msgtype)
	{
		this.SendTo=sendto;
		this.SendTime=sendtime;
		this.MsgContent=msgcontent;
		this.MsgType=msgtype;
		
	}
	
	public String getSentTo()
	{
		return SendTo;
	}
	
	public String getSendTime()
	{
		return SendTime;
	}
	
	public String getMsgConetent()
	{
		return MsgContent;
	}
	
	public String MsgType()
	{
		return MsgType;
	}
	
	public void setSendTo(String sendto)
	{
		this.SendTo=sendto;
	}
	
	public void setSendTime(String sendtime)
	{
		this.SendTime=sendtime;
	}
	
	public void setMsgContent(String msgcontent)
	{
		this.MsgContent=msgcontent;
	}
	
	public void setMsgType(String msgtype)
	{
		this.MsgType=msgtype;
	}
	
	public String toString()
	{
		return SendTo+"["+SendTime+"]:"+MsgContent+"("+MsgType+")";
	}
	
	
}
