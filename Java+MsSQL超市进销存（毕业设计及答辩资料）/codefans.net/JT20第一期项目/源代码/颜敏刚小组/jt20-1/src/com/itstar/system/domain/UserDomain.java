package com.itstar.system.domain;

public interface UserDomain {
	
	public boolean isExist(String username);
	
	public boolean isExist(String username,String upassword);
	
	public boolean Add(String username,String upassword);
	
	public String[][] queryForDelete();
	
	public boolean deleteUser(String username);
	
	public String[] queryUser();
	
	public boolean updatepwd(String username,String upassword);
}
