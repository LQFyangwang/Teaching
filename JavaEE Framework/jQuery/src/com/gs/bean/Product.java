package com.gs.bean;

public class Product {
	
	private String title;
	private float price;
	
	public Product() {
	}
	
	public Product(String title, float price) {
		this.title = title;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [title=" + title + ", price=" + price + "]";
	}
	
}
