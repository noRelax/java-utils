package com.itstar.pd.jdbc;

public class StType {
	public StType(){}
	private int	Sid;
	private String	Sgname;
	private String	Ssname;
	private String	Sstotal;
	private String	Stype;
	private String	Snumber;
	public String getSgname() {
		return Sgname;
	}
	public void setSgname(String sgname) {
		Sgname = sgname;
	}
	public int getSid() {
		return Sid;
	}
	public void setSid(int sid) {
		Sid = sid;
	}
	public String getSnumber() {
		return Snumber;
	}
	public void setSnumber(String snumber) {
		Snumber = snumber;
	}
	public String getSsname() {
		return Ssname;
	}
	public void setSsname(String ssname) {
		Ssname = ssname;
	}
	public String getSstotal() {
		return Sstotal;
	}
	public void setSstotal(String sstotal) {
		Sstotal = sstotal;
	}
	public String getStype() {
		return Stype;
	}
	public void setStype(String stype) {
		Stype = stype;
	}

}
