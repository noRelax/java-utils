package com.ll.smsbeans;

import java.io.IOException;
import java.io.Serializable;

public interface Packet extends Serializable
{
	/**
	 * 对当前的数据包打包成字节数组，以便于发送。
	 * @return 返回字节数组
	 * @throws IOException
	 */
	public abstract byte[] getContent() throws IOException;

}
