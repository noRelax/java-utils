/*
 * �������� 2004-9-11
 *
 * 
 */
package com.ll.smsbeans.cmpp3;

import java.io.IOException;



/**
 * @author Administrator
 *
 * 
 */
public class CmppCancelResp extends CmppPacket
{
	
	public static int CANCEL_SUCCESSED=0;
	public static int CANCEL_FAILED=1;
	
	private int SuccessId;

	public CmppCancelResp(CmppCancelRespBuilder builder)
	{
		CommandId = CmppPacketCommon.ID_CMPP_CANCEL_RESP;
		
		SequenceId =builder.getSequenceId();
		
		SuccessId=builder.getSuccessId();
	}

	public byte[] getContent() throws IOException
	{
		this.writeD(CommandId);
		this.writeD(SequenceId);
		
		this.writeD(SuccessId);
		return getBytes();
	}

	
	/* ���� Javadoc��
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		// TODO �Զ����ɷ������
		
		return this.getClass().getName() + ": SequenceId=" + SequenceId + "\n  SuccessId=" +SuccessId ;
	}

	/**
	 * @return
	 */
	public int getSuccessId()
	{
		return SuccessId;
	}

	
}
