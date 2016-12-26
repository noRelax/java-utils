package com.itstar.erp;

public class Customer {
	private int id;
	private String number=null;
	private String kallcall=null;
	private String address=null;
	private String phone=null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getKallcall() {
		return kallcall;
	}
	public void setKallcall(String kallcall) {
		this.kallcall = kallcall;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEamil() {
		return eamil;
	}
	public void setEamil(String eamil) {
		this.eamil = eamil;
	}
	private String eamil=null;

}
