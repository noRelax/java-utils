package com.itstar.erp.dao.sell;


import com.itstar.erp.vo.sell.SellBean;


public interface SellDaoI {
	public String getproName(int proID);
	
	public void insert(SellBean bean);
	public void update(int sellid,int selllesf);
}
