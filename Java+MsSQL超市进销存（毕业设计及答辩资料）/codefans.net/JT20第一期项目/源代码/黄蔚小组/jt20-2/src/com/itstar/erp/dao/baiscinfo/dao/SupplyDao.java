package com.itstar.erp.dao.baiscinfo.dao;
//download by http://www.codefans.net
import com.itstar.erp.vo.supply.SupplyBean;



public interface SupplyDao {
	void insert(SupplyBean bean);
	void update(SupplyBean bean,String value);
	void Query(SupplyBean bean);
	SupplyBean Query(String value);
	void delete(String value);

	
}
