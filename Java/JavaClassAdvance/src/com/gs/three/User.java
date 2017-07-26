package com.gs.three;

public class User {

	private String name;
	private Address address; // 一对一关联关系，一个用户对应一个地址
	private Car[] cars; // 一对多关联关系
	
	public User() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Car[] getCars() {
		return cars;
	}

	public void setCars(Car[] cars) {
		this.cars = cars;
	}
	
}
