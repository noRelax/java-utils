package com.v512.example.dao;

import java.util.List;

import com.v512.example.model.Product;

public interface ProductDao {
	public void create(Product p);
	public Product getProduct(String id);
	public List getProducts();
	public void update(Product product);
	public void remove(String id);

}
