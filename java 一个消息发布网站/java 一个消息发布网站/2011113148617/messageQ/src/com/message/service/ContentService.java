package com.message.service;

import java.util.List;

import com.message.bean.Content;
import com.message.dao.ContentDAO;
import com.message.dao.DataDAOImp;

public class ContentService implements ServiceImp{
	public boolean addContent(String title,String content,int admin,DataDAOImp con){
		Content cont=new Content();
		cont.setAdmin(admin);
		cont.setContent(content);
		cont.setTitle(title);
		return con.Insert(cont);
	}
	public boolean updateContent(int id,String content,DataDAOImp condao){
		Content con=new Content();
		con.setId(id);
		con=(Content)condao.SelectWhere(con, 1, 2).iterator().next();
		con.setContent(content);
		return condao.update(con);
	}
	public List LookAll(int now,int pagerow,DataDAOImp condao){
		int first=(now-1)*pagerow;
		return condao.SelectAll(first, pagerow);
	}
	public List Looktitle(String keyword,int now,int pagerow,DataDAOImp condao){
		Content con=new Content();
		con.setTitle(keyword);
		int first=(now-1)*pagerow;
		return condao.SelectWhere(con, first, pagerow);
	}
	public List LookContent(String keyword,int now,int pagerow,DataDAOImp condao){
		Content con=new Content();
		con.setContent(keyword);
		int first=(now-1)*pagerow;
		return condao.SelectWhere(con, first, pagerow);
	}
	public List Lookid(int id, int now, int pagerow, DataDAOImp condao) {
		// TODO Auto-generated method stub
		Content con=new Content();
		con.setId(id);
		int first=(now-1)*pagerow;
		return condao.SelectWhere(con, first, pagerow);
	}
}
