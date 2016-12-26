package com.itstar.erp.dao.tuiku;


import com.itstar.erp.vo.ruku.RuKuBean;
import com.itstar.erp.vo.tuiku.TuiKuBean;

public interface TuiKuDaoI {
	public RuKuBean getRuKuBean(int rukuid);
	public void insert(TuiKuBean bean);
	public void update(int sellid, int rukuleft);

	
}
