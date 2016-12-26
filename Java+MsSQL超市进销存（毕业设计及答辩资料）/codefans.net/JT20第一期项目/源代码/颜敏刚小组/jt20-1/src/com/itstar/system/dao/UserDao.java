package com.itstar.system.dao;

import java.util.List;

public interface UserDao {
	
	public List queryall();
	
	public boolean insert(String username,String upassword);
	
	public boolean delete(String username);
	
	public boolean update(String username,String upassword);
}
