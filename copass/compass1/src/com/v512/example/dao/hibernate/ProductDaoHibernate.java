package com.v512.example.dao.hibernate;

import java.util.List;

import com.v512.example.dao.ProductDao;
import com.v512.example.model.Product;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
public class ProductDaoHibernate extends HibernateDaoSupport implements ProductDao {

	public void create(Product p) {
		getHibernateTemplate().save(p);

	}

	public Product getProduct(String id) {
		return (Product)getHibernateTemplate().get(Product.class, id);
	}

	public List getProducts() {
			return getHibernateTemplate().find("from Product order by id desc");
	}

	public void remove(String id) {
	getHibernateTemplate().delete(getProduct(id));

	}

	public void update(Product product) {
		getHibernateTemplate().saveOrUpdate(product);

	}

}
