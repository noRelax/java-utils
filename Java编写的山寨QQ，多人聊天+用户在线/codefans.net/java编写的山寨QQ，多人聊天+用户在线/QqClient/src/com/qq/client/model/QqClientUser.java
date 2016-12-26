package com.qq.client.model;
//Download by http://www.codefans.net
import com.qq.common.*;
public class QqClientUser {

	public boolean checkUser(User u)
	{
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
	
}
