package com.itstar.erp.vo.sell;

public class SellBean {
	public int getSellID() {
		return sellID;
	}
	public void setSellID(int sellID) {
		this.sellID = sellID;
	}
	public String getSellDateTime() {
		return sellDateTime;
	}
	public void setSellDateTime(String sellDateTime) {
		this.sellDateTime = sellDateTime;
	}
	public int getProID() {
		return proID;
	}
	public void setProID(int proID) {
		this.proID = proID;
	}
	public int getSellAcount() {
		return sellAcount;
	}
	public void setSellAcount(int sellAcount) {
		this.sellAcount = sellAcount;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public int getYwyID() {
		return ywyID;
	}
	public void setYwyID(int ywyID) {
		this.ywyID = ywyID;
	}
	public String getSellRemark() {
		return sellRemark;
	}
	public void setSellRemark(String sellRemark) {
		this.sellRemark = sellRemark;
	}
	public int getKehuID() {
		return kehuID;
	}
	public void setKehuID(int kehuID) {
		this.kehuID = kehuID;
	}
	private int sellID;
	private String sellDateTime;
	private int proID;
	private int sellAcount;
	private double sellPrice;
	private int ywyID;
	private String sellRemark;
	private int kehuID;
}
