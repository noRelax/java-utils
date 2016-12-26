package com.itstar.erp.dao.baiscinfo.dao;
//download by http://www.codefans.net
import com.itstar.erp.vo.kehu.KeHuBean;

public interface KeHuDao {
	void insert(KeHuBean bean);
	KeHuBean Query(String value);
	void update(KeHuBean bean,String value);
	void delete(String value);
}
