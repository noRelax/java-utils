package com.itstar.erp.dao;

public class SaleRecord {
	int  OId;     //���۵���
	int  OGId;   //��Ʒ���
	String goodName;// ��Ʒ����
	int OGNumber;  //��Ʒ����
	String OUserName; //����Ա
	String OTime;  //����ʱ��
	   
   public int getOId() {
		return OId;
	}
	public void setOId(int id) {
		OId = id;
	}
	public int getOGId() {
		return OGId;
	}
	public void setOGId(int id) {
		OGId = id;
	}
	
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	
	public int getOGNumber() {
		return OGNumber;
	}
	public void setOGNumber(int number) {
		OGNumber = number;
	}
	
	public String getOUserName() {
		return OUserName;
	}
	public void setOUserName(String userName) {
		OUserName = userName;
	}
	
	public String getOTime() {
		return OTime;
	}
	public void setOTime(String time) {
		OTime = time;
	}
}
