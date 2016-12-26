package com.itstar.erp.dao;

public class Admin {
	private String userid;
	private String psd; 
	private int sex;
	private String AEmail;
	private String APhone;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getAEmail() {
		return AEmail;
	}
	public void setAEmail(String email) {
		AEmail = email;
	}
	public String getAPhone() {
		return APhone;
	}
	public void setAPhone(String phone) {
		APhone = phone;
	}

}
