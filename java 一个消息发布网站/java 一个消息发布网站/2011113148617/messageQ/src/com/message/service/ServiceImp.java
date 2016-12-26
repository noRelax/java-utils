package com.message.service;
import java.util.List;
import com.message.dao.DataDAOImp;
public interface ServiceImp {
	public boolean addContent(String title,String content,int admin,DataDAOImp con);
	public boolean updateContent(int id,String content,DataDAOImp condao);
	public List LookAll(int now,int pagerow,DataDAOImp condao);
	public List Looktitle(String keyword,int now,int pagerow,DataDAOImp condao);
	public List LookContent(String keyword,int now,int pagerow,DataDAOImp condao);
	public List Lookid(int id,int now,int pagerow,DataDAOImp condao);
}
