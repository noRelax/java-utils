package com.itstar.erp.dao;

public interface AdminDao {
	public  void addAdmin(Admin admin);

	public void modifyAdmin(Admin admin);

	public void delAdmin(Admin admin);
	
	public boolean findAdmin(Admin admin);

	public boolean findLogin(Admin admin);
}
