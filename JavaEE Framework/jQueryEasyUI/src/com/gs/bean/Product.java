package com.gs.bean;

import java.util.Date;

public class Product {
	
	private long id;
	private String title;
	private double price;
	private String des;
	private int type;
	private String typeName;
	
	private Date pdate;
	private String pDateStr;
	private Date enterDate;
	private String enterDateStr;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public String getpDateStr() {
		return pDateStr;
	}
	public void setpDateStr(String pDateStr) {
		this.pDateStr = pDateStr;
	}
	public Date getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}
	public String getEnterDateStr() {
		return enterDateStr;
	}
	public void setEnterDateStr(String enterDateStr) {
		this.enterDateStr = enterDateStr;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", price=" + price + ", des=" + des + "]";
	}

}
