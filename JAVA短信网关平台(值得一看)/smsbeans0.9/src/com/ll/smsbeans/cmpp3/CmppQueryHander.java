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
public class CmppQueryHander extends CmppHander
{

	/* ���� Javadoc��
	 * @see com.ll.smsbeans.cmpp3.cmpp3Hander#packetBuiler(byte[])
	 */
	public Packet packetBuiler(byte[] packbytes) throws Exception
	{
		
		CmppQueryBuilder cqb =new CmppQueryBuilder();
		
		
		cqb.setSequenceId(Bytes4ToInt(packbytes,4));
		
		
		//TODO ���������������ɴ���
		
		return cqb.builder();
	}

}
