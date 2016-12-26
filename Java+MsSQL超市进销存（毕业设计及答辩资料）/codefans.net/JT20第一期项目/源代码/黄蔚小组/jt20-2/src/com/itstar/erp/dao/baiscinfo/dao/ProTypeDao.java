package com.itstar.erp.dao.baiscinfo.dao;
//download by http://www.codefans.net
import com.itstar.erp.vo.producttype.ProTypeBean;

public interface ProTypeDao {
	void insert(ProTypeBean bean);
	ProTypeBean Query(String value);
	void update(ProTypeBean bean,String value);
	void delete(String value);
	void update(String danwei,int ywid,String remark, String value);
}
