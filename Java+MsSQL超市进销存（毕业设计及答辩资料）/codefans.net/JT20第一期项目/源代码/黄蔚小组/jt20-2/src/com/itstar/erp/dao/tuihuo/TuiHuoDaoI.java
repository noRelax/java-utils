package com.itstar.erp.dao.tuihuo;

//download by http://www.codefans.net
import com.itstar.erp.vo.sell.SellBean;

public interface TuiHuoDaoI {
	

	
	public SellBean getSellBean(int sellid);
	public String getkehuName(int kehuID);
	public String getywyName(int ywyid);
	


}
