/*
 * �������� 2004-9-17
 *
 * 
 */
package com.ll.smsbeans.cmpp3;

import com.ll.smsbeans.Packet;

/**
*
* @author  listlike <a href="mailto:listlike@hotmail.com">
*                      <i>&lt;listlike@hotmail.com&gt;</i></a>
*
*/
public class CmppActiveTestRespBuilder extends CmppPacketBuilder
{

	private int Reserved;
	
	
	public CmppActiveTestRespBuilder()
	{
		reset();
	}
	/* ���� Javadoc��
	 * @see com.ll.smsbeans.PacketBuilder#builder()
	 */
	public Packet builder()
	{
		return new CmppActiveTestResp(this);
	}

	

	/**
	 * @return
	 */
	public int getReserved()
	{
		return Reserved;
	}

	/**
	 * @param i
	 */
	public void setReserved(int reserved)
	{
		Reserved = reserved;
	}

	/* ���� Javadoc��
	 * @see com.ll.smsbeans.PacketBuilder#reset()
	 */
	public void reset()
	{
		SequenceId=0; 
		Reserved=0;
	}

}
