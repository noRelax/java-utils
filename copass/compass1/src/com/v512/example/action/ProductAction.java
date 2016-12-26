package com.v512.example.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.v512.example.model.Product;
import com.v512.example.service.ProductManager;
import org.apache.struts2.ServletActionContext;

public class ProductAction extends ActionSupport {
	
	private static final long serialVersionUID = 3795048906805728732L;
	private ProductManager productManager;
	private Product product;
	private String queryString;
	
	public void setQueryString(String queryString){
		this.queryString=queryString;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProductManager(ProductManager productManager){
		this.productManager=productManager;
	}
	
	public String insert(){
		productManager.insertProduct(product);
		return SUCCESS;
	}
	public String search(){
		List results=productManager.searchProducts(queryString);
		System.out.println(results.size());
		ServletActionContext.getRequest().setAttribute("searchresults", results);
		return SUCCESS;
	}
	
	
}
