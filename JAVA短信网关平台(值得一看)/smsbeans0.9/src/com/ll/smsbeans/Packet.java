package com.ll.smsbeans;

import java.io.IOException;
import java.io.Serializable;

public interface Packet extends Serializable
{
	/**
	 * �Ե�ǰ�����ݰ�������ֽ����飬�Ա��ڷ��͡�
	 * @return �����ֽ�����
	 * @throws IOException
	 */
	public abstract byte[] getContent() throws IOException;

}
