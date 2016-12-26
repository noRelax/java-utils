package com.itstar.erp;

public class Ware {

	private int id;
	private String number=null;
	private String name=null;
	private String price=null;
	private String count=null;
	private String time=null;
	public String getPrice() {
		return price;
	}
	public void setPrice(String pirce) {
		this.price = pirce;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String toString(){
		return this.getCount()+this.getId()+this.getName()+this.getNumber();
	}*/

}
