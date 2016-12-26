package com.ll.smsbeans;

/**
 * Represents a singleton.
 */
public class IdBuilder
{

	/**
	* ���������к�
	*/
	static private int sequenceId;

	/**
	 * ������Ϣ�������к�
	*/
	private long messsageId;

	/**
	* Holds singleton instance
	*/
	private static IdBuilder instance;

	/**
	* prevents instantiation
	*/
	private IdBuilder()
	{
		// prevent creation
	}

	/**
	* Returns the singleton instance.
	 @return	the singleton instance
	*/
	static public IdBuilder getInstance()
	{
		if (instance == null)
		{
			instance = new IdBuilder();
		}
		return instance;
	}

	synchronized public int getSequenceId()
	{

		return sequenceId > 99999 ? 0 : sequenceId++;
	}

	synchronized public long getMessageId()
	{

		return messsageId > 999999999 ? 0 : messsageId++;
	}

	synchronized public void resetSequenceId()
	{
		sequenceId = 0;

	}

	synchronized public void resetMessageId()
	{
		messsageId = 0;

	}

}