/*
 * �������� 2004-9-12
 *
 * 
 */
package com.ll.smsbeans.cmpp3;

import com.ll.smsbeans.Packet;

/**
 * @author Administrator
 *
 * 
 */
public class CmppConnectHander extends CmppHander
{

	/* ���� Javadoc��
	 * @see com.ll.smsbeans.cmpp3.cmpp3Hander#packetBuiler(byte[])
	 */
	public Packet packetBuiler(byte[] packbytes) throws Exception
	{
		
		CmppConnectBuilder ccb =new CmppConnectBuilder();
		
		
		ccb.setSequenceId(Bytes4ToInt(packbytes,4));
		
		
		//TODO ���������������ɴ���
		
		return ccb.builder();
	}

}
