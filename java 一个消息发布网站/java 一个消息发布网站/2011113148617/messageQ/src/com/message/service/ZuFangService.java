package com.message.service;

import java.util.List;

import com.message.bean.ZhaoGongZuo;
import com.message.bean.ZuFang;
import com.message.dao.DataDAOImp;

public class ZuFangService implements ServiceImp{
	public boolean addContent(String title,String content,int admin,DataDAOImp con){
		ZuFang cont=new ZuFang();
		cont.setAdmin(admin);
		cont.setContent(content);
		cont.setTitle(title);
		return con.Insert(cont);
	}
	public boolean updateContent(int id,String content,DataDAOImp condao){
		ZuFang con=new ZuFang();
		con.setId(id);
		con=(ZuFang)condao.SelectWhere(con, 1, 2).iterator().next();
		con.setContent(content);
		return condao.update(con);
	}
	public List LookAll(int now,int pagerow,DataDAOImp condao){
		int first=(now-1)*pagerow;
		return condao.SelectAll(first, pagerow);
	}
	public List Looktitle(String keyword,int now,int pagerow,DataDAOImp condao){
		ZuFang con=new ZuFang();
		con.setTitle(keyword);
		int first=(now-1)*pagerow;
		return condao.SelectWhere(con, first, pagerow);
	}
	public List LookContent(String keyword,int now,int pagerow,DataDAOImp condao){
		ZuFang con=new ZuFang();
		con.setContent(keyword);
		int first=(now-1)*pagerow;
		return condao.SelectWhere(con, first, pagerow);
	}
	public List Lookid(int id, int now, int pagerow, DataDAOImp condao) {
		// TODO Auto-generated method stub
		ZuFang con=new ZuFang();
		con.setId(id);
		int first=(now-1)*pagerow;
		return condao.SelectWhere(con, first, pagerow);
	}
}
