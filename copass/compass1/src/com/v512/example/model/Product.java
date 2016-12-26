package com.v512.example.model;
import org.compass.annotations.*;
/**
 * Product entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Searchable
public class Product implements java.io.Serializable {

	// Fields

	@SearchableId
	private String id;
	@SearchableProperty(name="name")
	private String name;
	@SearchableProperty(name="price")
	private Double price;
	@SearchableProperty(name="brand")
	private String brand;
	@SearchableProperty(name="description")
	private String description;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(String name, Double price, String brand, String description) {
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.description = description;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}