package com.itstar.query.ingood;

public class Gysbean {
	/**
	 * 
	 */
	private String Did; // 入库编号
	public String getDid() {
		return Did;
	}
	public void setDid(String did) {
		Did = did;
	}
	public String getPgid() {
		return Pgid;
	}
	public void setPgid(String pgid) {
		Pgid = pgid;
	}
	public String getGname() {
		return Gname;
	}
	public void setGname(String gname) {
		Gname = gname;
	}
	public String getGtype() {
		return Gtype;
	}
	public void setGtype(String gtype) {
		Gtype = gtype;
	}
	public String getPprice() {
		return Pprice;
	}
	public void setPprice(String pprice) {
		Pprice = pprice;
	}
	public String getPnumber() {
		return Pnumber;
	}
	public void setPnumber(String pnumber) {
		Pnumber = pnumber;
	}
	public String getGunit() {
		return Gunit;
	}
	public void setGunit(String gunit) {
		Gunit = gunit;
	}
	public String getDtotal() {
		return Dtotal;
	}
	public void setDtotal(String dtotal) {
		Dtotal = dtotal;
	}
	public String getDsname() {
		return Dsname;
	}
	public void setDsname(String dsname) {
		Dsname = dsname;
	}
	public String getDdate() {
		return Ddate;
	}
	public void setDdate(String ddate) {
		Ddate = ddate;
	}
	public String getDsquare() {
		return Dsquare;
	}
	public void setDsquare(String dsquare) {
		Dsquare = dsquare;
	}
	public String getDcode() {
		return Dcode;
	}
	public void setDcode(String dcode) {
		Dcode = dcode;
	}
	private String Pgid;// 商品编号
	private String Gname;// 商品名称
	private String Gtype;// 商品类型
	private String Pprice;// 进货单价
	private String Pnumber;// 数量
	private String Gunit;//计量单位
	private String Dtotal;// 总计金额
	private String Dsname;// 供应商名称
	private String Ddate;// 入库时间
	private String Dsquare;// 结算方式
	private String Dcode;// 预留
	

}
