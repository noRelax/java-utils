package com.itstar.erp.dao.tianjiayonghu;

import com.itstar.erp.vo.user.UserBean;

public interface TianJiaUserDaoI {
	public boolean test(String user,String pass);
	public void insert(UserBean bean);
	public void delete(int i);
}
