package com.gs.bean;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Product {
	
	private int id;
	private String title;
	private double price;
	
	private Set<OrderProduct> ops;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@JSON(serialize=false) 
	public Set<OrderProduct> getOps() {
		return ops;
	}
	public void setOps(Set<OrderProduct> ops) {
		this.ops = ops;
	}

}
