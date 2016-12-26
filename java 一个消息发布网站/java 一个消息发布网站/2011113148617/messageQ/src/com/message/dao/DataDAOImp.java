package com.message.dao;

import java.util.List;

import com.message.bean.Content;

public interface DataDAOImp {
	public boolean Insert(Object con);
	public boolean update(Object con);
	public List SelectAll(int first,int end);
	public List SelectWhere(Object con,int first,int end);
	public boolean deleteContent(Object con);
}
