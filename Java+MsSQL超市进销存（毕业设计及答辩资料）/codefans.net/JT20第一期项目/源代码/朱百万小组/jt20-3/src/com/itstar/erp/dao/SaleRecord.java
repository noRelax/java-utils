package com.itstar.erp.dao;

public class SaleRecord {
	int  OId;     //销售单号
	int  OGId;   //商品编号
	String goodName;// 商品名称
	int OGNumber;  //商品数量
	String OUserName; //销售员
	String OTime;  //销售时间
	   
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
