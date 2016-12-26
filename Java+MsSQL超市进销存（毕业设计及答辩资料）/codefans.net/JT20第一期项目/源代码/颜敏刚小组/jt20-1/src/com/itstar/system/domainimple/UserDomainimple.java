package com.itstar.system.domainimple;

import java.util.Iterator;
import java.util.List;

import com.itstar.system.bean.UserBean;
import com.itstar.system.daoimple.UserDaoimple;
import com.itstar.system.domain.UserDomain;


public class UserDomainimple implements UserDomain {
	public List<UserBean> userlist = null;

//查询该用户是否存在。	
	 public boolean isExist(String username){
		Iterator<UserBean> it = userlist.iterator();
		while (it.hasNext()) {
			UserBean ub = it.next();
			if (ub.getUsername().equals(username))
				return true;
		}
		return false;
	}
		
	
//	查询该用户是否存在。（带密码）
	public boolean isExist(String username, String upassword) {
		Iterator<UserBean> it = userlist.iterator();
		while (it.hasNext()) {
			UserBean ub = it.next();
			if (ub.getUsername().equals(username)
					&& ub.getUpassword().equals(upassword))
				return true;
		}
		return false;
	}
// 添加用户是否成功	
	public boolean Add(String username,String upassword){
		return new UserDaoimple().insert(username, upassword);
	}
// 查询所有的用户 被上传到表格里
	public String[][] queryForDelete(){
		userlist = new UserDaoimple().queryall();
		String[][] st=new String[userlist.size()][3];
		Iterator<UserBean> it = userlist.iterator();
		for(int i=0;it.hasNext();i++){
			UserBean ub = it.next();
			st[i][0]=ub.getUsername();
			st[i][1]="******";
			st[i][2]="超级管理员";
		}
		return st;
	}
//	删除用户  是否成功	
	public boolean deleteUser(String username){
		return new UserDaoimple().delete(username);	
	}
//	查询所有用户	
	public String[] queryUser(){
		String[] st=new String[30];
		Iterator<UserBean> it = userlist.iterator();
		for(int i=0;it.hasNext();i++){
			UserBean ub = it.next();
			st[i]=ub.getUsername();
		}
		return st;
	}
//  更改用户的密码	
	public boolean updatepwd(String username,String upassword){
		return new UserDaoimple().update(username, upassword);
		
	}
	
	
	public UserDomainimple() {
		if(userlist==null)
			userlist = new UserDaoimple().queryall();
	}
}
