package com.message.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.message.bean.Content;
import com.message.bean.ZhaoGongZuo;
import com.message.dao.DataDAOImp;
import com.message.factory.HibernateSessionFactory;

public class ZhaoGongZuoService implements ServiceImp{
	public boolean addContent(String title,String content,int admin,DataDAOImp con){
		ZhaoGongZuo cont=new ZhaoGongZuo();
		cont.setAdmin(admin);
		cont.setContent(content);
		cont.setTitle(title);
		return con.Insert(cont);
	}
	public boolean updateContent(int id,String content,DataDAOImp condao){
		ZhaoGongZuo con=new ZhaoGongZuo();
		con.setId(id);
		con=(ZhaoGongZuo)condao.SelectWhere(con, 1, 2).iterator().next();
		con.setContent(content);
		return condao.update(con);
	}
	public List LookAll(int now,int pagerow,DataDAOImp condao){
		int first=(now-1)*pagerow;
		return condao.SelectAll(first, pagerow);
	}
	public List Looktitle(String keyword,int now,int pagerow,DataDAOImp condao){
		ZhaoGongZuo con=new ZhaoGongZuo();
		con.setTitle(keyword);
		int first=(now-1)*pagerow;
		return condao.SelectWhere(con, first, pagerow);
	}
	public List LookContent(String keyword,int now,int pagerow,DataDAOImp condao){
		ZhaoGongZuo con=new ZhaoGongZuo();
		con.setContent(keyword);
		int first=(now-1)*pagerow;
		return condao.SelectWhere(con, first, pagerow);
	}
	public List Lookid(int id, int now, int pagerow, DataDAOImp condao) {
		// TODO Auto-generated method stub
		ZhaoGongZuo con=new ZhaoGongZuo();
		con.setId(id);
		int first=(now-1)*pagerow;
		return condao.SelectWhere(con, first, pagerow);
	}
}