package com.itstar.erp.dao.kucun;


import com.itstar.erp.vo.product.ProBean;

public interface KuCunDaoI {
	
	public int getkucunAcount(int proID);
	void update(int proid,int total);
	public boolean test(int proID);
	public void insert(int proid,int total);
	public ProBean getProBean(int proid);
}
