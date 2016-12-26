package com.itstar.erp.dao.baiscinfo.dao;


import com.itstar.erp.vo.yewuyuan.YwyBean;

public interface YwyDao {
	void insert(YwyBean bean);
	public void update(YwyBean bean, String value);
	public YwyBean Query(String value);
	public void delete(String value);
}
