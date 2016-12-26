package com.itstar.erp.dao.ruku;
//download by http://www.codefans.net
import com.itstar.erp.vo.ruku.RuKuBean;

public interface RuKuDaoI {
	RuKuBean Query(String value);
	public int getproID(String proName);
	void insert(RuKuBean bean);
}
