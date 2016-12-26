package com.itstar.erp.dao.baiscinfo.dao;

//download by http://www.codefans.net

import com.itstar.erp.vo.product.ProBean;

public interface ProDao {
	public void insert(ProBean bean);
	public ProBean Query(String value);
	public void update(int ywyid,String value);
	public void update(int ywyid,double d,String value);
	public void delete(String value);
}
