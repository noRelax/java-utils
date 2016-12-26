package com.v512.example.service;

import java.util.List;

import com.v512.example.model.Product;

public interface ProductManager {
	public void insertProduct(Product p);
	public Product findProdcut(String id);
	public List searchProducts(String queryString);

}
