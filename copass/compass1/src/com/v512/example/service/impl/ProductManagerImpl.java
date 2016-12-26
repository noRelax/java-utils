package com.v512.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.compass.core.CompassHits;
import org.compass.core.CompassSession;
import org.compass.core.CompassTemplate;
import org.compass.core.CompassTransaction;

import com.v512.example.dao.ProductDao;
import com.v512.example.model.Product;
import com.v512.example.service.ProductManager;
import org.compass.core.Compass;
public class ProductManagerImpl implements ProductManager {
	private ProductDao productDao;
	private CompassTemplate compassTemplate;

	
	
	
	public void setCompassTemplate(CompassTemplate compassTemplate){
		this.compassTemplate=compassTemplate;
	}
	
	public void setProductDao(ProductDao productDao){
		this.productDao=productDao;
	}

	public Product findProdcut(String id) {
		return productDao.getProduct(id);		
	}

	public void insertProduct(Product p) {
	productDao.create(p);

	}

	public List searchProducts(String queryString) {
		Compass compass = compassTemplate.getCompass();
		CompassSession session=compass.openSession();
		List list = new ArrayList();
		
		CompassHits hits= session.queryBuilder().queryString("name:"+queryString).toQuery().hits();
		System.out.println("queryString:"+queryString);
		System.out.println("hits:"+hits.getLength());
		for(int i=0;i<hits.length();i++){
			Product hit=(Product)hits.data(i);
			list.add(hit);
		}
		
		return list;
	}



	public CompassTemplate getCompassTemplate() {
		return compassTemplate;
	}

}
