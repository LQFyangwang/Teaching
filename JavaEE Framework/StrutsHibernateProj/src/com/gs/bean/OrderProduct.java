package com.gs.bean;

public class OrderProduct {
	
	private int id;
	
	private Order orderr;
	private Product product;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Order getOrderr() {
		return orderr;
	}
	public void setOrderr(Order orderr) {
		this.orderr = orderr;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}
