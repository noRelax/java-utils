package com.itstar.info.bean;

public class SeversBean {

	private int Sid;
	private String Sname;
	private String Saddress;
	private String Semail;
	private String Sphone;
	private String Sperson;
	private String SpostCode;
	private String Sbank;
	private String Snumber;
	private String Sflag;
	public int getSid() {
		return Sid;
	}
	public void setSid(int sid) {
		Sid = sid;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSaddress() {
		return Saddress;
	}
	public void setSaddress(String saddress) {
		Saddress = saddress;
	}
	public String getSemail() {
		return Semail;
	}
	public void setSemail(String semail) {
		Semail = semail;
	}
	public String getSphone() {
		return Sphone;
	}
	public void setSphone(String sphone) {
		Sphone = sphone;
	}
	public String getSperson() {
		return Sperson;
	}
	public void setSperson(String sperson) {
		Sperson = sperson;
	}
	public String getSpostCode() {
		return SpostCode;
	}
	public void setSpostCode(String spostCode) {
		SpostCode = spostCode;
	}
	public String getSbank() {
		return Sbank;
	}
	public void setSbank(String sbank) {
		Sbank = sbank;
	}
	public String getSnumber() {
		return Snumber;
	}
	public void setSnumber(String snumber) {
		Snumber = snumber;
	}
	public String getSflag() {
		return Sflag;
	}
	public void setSflag(String sflag) {
		Sflag = sflag;
	}
	public String toString(){
		return "name"+Sname+"address"+Saddress;
	}
	
	
}
