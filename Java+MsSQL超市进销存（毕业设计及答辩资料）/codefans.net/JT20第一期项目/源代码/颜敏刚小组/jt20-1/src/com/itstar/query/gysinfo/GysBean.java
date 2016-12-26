package com.itstar.query.gysinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GysBean {
	private int sid;
	private String sname;
	private String saddress;
	private String semail;
	private String sphone;
	private String sperson;
	private String spostCode;
	private String sbank;
	private String snumber;
	private String sflag;
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public String getSbank() {
		return sbank;
	}
	public void setSbank(String sbank) {
		this.sbank = sbank;
	}
	public String getSemail() {
		return semail;
	}
	public void setSemail(String semail) {
		this.semail = semail;
	}
	public String getSflag() {
		return sflag;
	}
	public void setSflag(String sflag) {
		this.sflag = sflag;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSnumber() {
		return snumber;
	}
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}
	public String getSperson() {
		return sperson;
	}
	public void setSperson(String sperson) {
		this.sperson = sperson;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public String getSpostCode() {
		return spostCode;
	}
	public void setSpostCode(String spostCode) {
		this.spostCode = spostCode;
	}
	
	public String toString(){
		return this.getSid()+this.getSname()+this.getSaddress();
	}
}
