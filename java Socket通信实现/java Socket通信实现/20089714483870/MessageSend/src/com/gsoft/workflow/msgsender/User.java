package com.gsoft.workflow.msgsender;

import com.lotus.sametime.core.types.STUser;
import com.lotus.sametime.im.Im;
import com.lotus.sametime.token.Token;

public class User {
	private String m_name = "";
	private Im m_im = null;
	private STUser m_stuser = null;
	private Token m_token = null;
	
	public User (){
		
	}
	
	public String getName() {
		return m_name;
	}
	
	public void setName(String m_name) {
		this.m_name = m_name;
	}
	
	public Im getIm() {
		return m_im;
	}
	
	public void setIm(Im m_im) {
		this.m_im = m_im;
	}
	
	public STUser getSTUser() {
		return m_stuser;
	}
	
	public void setSTUser(STUser m_stuser) {
		this.m_stuser = m_stuser;
	}
	
	public Token getToken() {
		return m_token;
	}
	
	public void setToken(Token m_token) {
		this.m_token = m_token;
	}
}
