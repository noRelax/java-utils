package com.message.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.message.bean.Content;
import com.message.bean.ErShou;
import com.message.bean.ErShouMai;
import com.message.factory.HibernateSessionFactory;

public class ErShouMaiDAO  implements DataDAOImp{
	public boolean Insert(Object con1){
		ErShouMai con=(ErShouMai)con1;
		boolean b=true;
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateSessionFactory.getSession();
			tx=session.beginTransaction();
			session.save(con);
			tx.commit();
		}catch(HibernateException e)
		{
			if(tx!=null)
			{
				tx.rollback();
			}
			b=false;
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return b;
	}
	public boolean update(Object con1){
		ErShouMai con=(ErShouMai)con1;
		boolean b=true;
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateSessionFactory.getSession();
			tx=session.beginTransaction();
			session.update(con);
			tx.commit();
		}catch(HibernateException e)
		{
			if(tx!=null)
			{
				tx.rollback();
			}
			b=false;
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return b;
	}
	/**
	 * ，传入数据返回分页集合
	 * @param con
	 * @param first
	 * @param end
	 * @return
	 */
	public List SelectAll(int first,int end){
		boolean b=true;
		Session session=null;
		List list=null;
		Query query;
		try{
			session=HibernateSessionFactory.getSession();	
			query=session.createQuery("from ErShouMai Order by  time desc");
			query.setFirstResult(first);
			query.setMaxResults(end);
			list=query.list();
		}catch(HibernateException e){
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return list;
	}
	public List SelectWhere(Object con1,int first,int end){
		ErShouMai con=(ErShouMai)con1;
		boolean b=true;
		Session session=null;
		List list=null;
		Query query;
		try{
			session=HibernateSessionFactory.getSession();
			Criteria criteria=session.createCriteria(ErShouMai.class);
			if(con.getId()!=0){
			query=session.createQuery("from ErShouMai where id="+con.getId());
			list=query.list();
			}else
				if(con.getTitle()!=null){
					criteria.add(Restrictions.like("title","%"+con.getTitle()+"%"));
					criteria.addOrder(Order.desc("time"));
					criteria.setFirstResult(first);
					criteria.setMaxResults(end);
					list=criteria.list();
				}else{
					criteria.add(Restrictions.like("content","%"+con.getContent()+"%"));
					criteria.addOrder(Order.desc("time"));
					criteria.setFirstResult(first);
					criteria.setMaxResults(end);
					list=criteria.list();
				}
		}catch(HibernateException e){
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return list;
	}
	public boolean deleteContent(Object con1){
		ErShouMai con=(ErShouMai)con1;
		boolean b=true;
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateSessionFactory.getSession();
			tx=session.beginTransaction();
			session.delete(con);
			tx.commit();
		}catch(HibernateException e){
			b=false;
			if(tx!=null)
			{
				tx.rollback();
			}
			throw e;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return b;
	}
}
