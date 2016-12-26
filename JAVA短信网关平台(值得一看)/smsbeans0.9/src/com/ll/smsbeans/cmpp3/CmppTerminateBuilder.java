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
public class CmppTerminateBuilder extends CmppPacketBuilder
{
	
	
	
	
	public CmppTerminateBuilder()
	{
		reset();
	}
	
	/* ���� Javadoc��
	 * @see com.ll.smsbeans.PacketBuilder#builder()
	 */
	public Packet builder()
	{	
		
		return new CmppTerminate(this);
	}
	
	

	/* ���� Javadoc��
	 * @see com.ll.smsbeans.PacketBuilder#reset()
	 */
	public void reset()
	{
		SequenceId=0; 		
	}

}
