/*
 * �������� 2004-9-10
 *
 * 
 */
package com.ll.smsbeans.test;

import java.net.InetAddress;

import com.ll.smsbeans.ConnectionAdapter;
import com.ll.smsbeans.ConnectionBean;
import com.ll.smsbeans.SyncMessageBean;
import com.ll.smsbeans.cmpp3.CmppCancelBuilder;
import com.ll.smsbeans.cmpp3.CmppQuery;
import com.ll.smsbeans.cmpp3.CmppQueryBuilder;
import com.ll.smsbeans.cmpp3.CmppQueryResp;
import com.ll.smsbeans.cmpp3.CmppSubmitBuilder;

/**
 * @author Administrator
 *
 * 
 */
public class ConnectBeanTest
{

	public static void main(String[] args)
	{
		InetAddress addr;
		ConnectionBean cb;
		cb = new ConnectionBean("901234","1234");
		cb.addConnectionListener(new ConnectionAdapter());

		try
		{
			cb.connect(addr = InetAddress.getByName("localhost"));
		} catch (java.net.UnknownHostException e)
		{ //from InetAddress.getByName()
			java.lang.System.err.println("DNS error finding your server:");
			java.lang.System.err.println(e.toString());

			//TODO
			return;

		} catch (java.io.IOException e)
		{ //from connect
			java.lang.System.err.println(
				"IO error while attempting to connect to server:");
			java.lang.System.err.println(e.toString());

			//TODO 
			return;

		}
		try
		{

			Thread.sleep(1000L);
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		
		CmppSubmitBuilder cm=new CmppSubmitBuilder();
		SyncMessageBean mb=new SyncMessageBean(cb);
		cm.setMsgId(0x7fffffff);
		cm.setPkTotal(1);
		cm.setPkNumber(1);
		cm.setRegisterDelivery(0 );
		cm.setMsgLevel(1);
		//cm.setServiceId("01850");
		cm.setFeeUserType(0);
		//cm.setFeeTermId("");

		cm.setFeeTermType(0 );
		cm.setTpPid(0 );
		cm.setTpUdhi(0);
		cm.setMsgFmt(15);
		cm.setMsgSrc("901234");
		cm.setFeeType("01");
		cm.setFeeCode("000000");
		cm.setAtTime("0000");
		cm.setValidTime("55555");
		cm.setSrcTermId("901234");
		cm.setDstTermType(0);
		cm.addDstTermId ("13803882229" );
		cm.addDstTermId("13803884444" );
		
		cm.addDstTermId("13803881234" ); 
		
		cm.setLinkId("");	
		
		mb.sendSubmit(cm,"����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!" );
		
		cm.setMsgId(0x1);
		mb.sendSubmit(cm,"����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!" );
		
		
		cm.setMsgId(0x2);
				mb.sendSubmit(cm,"����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ�������ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!" );
		
		cm.setMsgId(0x3);
				mb.sendSubmit(cm,"����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaa����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!����ǲ���11111111111111111,2222222222222222222222aaaaaaaaaaa!!!!!" );
		
		CmppQueryBuilder cqb=new CmppQueryBuilder();
		cqb.setQueryCode("test" );
		cqb.setQueryType(CmppQuery.QUERY_TYPE_TOTAL);
		cqb.setReserve("");
		cqb.setTime("testTime");
		
		CmppQueryResp cqr;
		cqr=mb.sendQuery(cqb );
		
		
		System.out.println("CmppQueryResp=");
		System.out.println(cqr);
		
		
		CmppCancelBuilder ccb =new CmppCancelBuilder();
		ccb.setMsgId(0x0102030405060708L);
		 
		System.err.println("mb.sendCancel( ccb) =" +  mb.sendCancel( ccb));
		//cb.send( cm); 
		
		try
		{

			Thread.sleep(100000L);
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		cb.disconnect();
	}
}
