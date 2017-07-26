package com.gs.bean;

import java.util.Date;
import java.util.Set;

public class Order {
	
	private int id;
	private Date oTime;
	
	private User user;
	
	private Set<OrderProduct> ops;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getoTime() {
		return oTime;
	}
	public void setoTime(Date oTime) {
		this.oTime = oTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderProduct> getOps() {
		return ops;
	}
	public void setOps(Set<OrderProduct> ops) {
		this.ops = ops;
	}

}
