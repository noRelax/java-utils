package com.itstar.system.bean;

public class UserBean {
	private int uid;
	private String username;
	private String upassword;
	private String ustate;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUstate() {
		return ustate;
	}
	public void setUstate(String ustate) {
		this.ustate = ustate;
	}

	public String toString(){
		return "ÇëÖØĞ´user_beanµÄtoString";
	}
	public UserBean(){}
	public UserBean(String username,String upassword){
		this.setUsername(username);
		this.setUpassword(upassword);
	}
	
}
