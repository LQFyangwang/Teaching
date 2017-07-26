package com.gs.bean;

import java.util.Arrays;

public class User {
	
	private String name;
	private String pwd;
	private String gender;
	private String[] hobby;
	private String address;
	private String[] cars;
	private String info;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public void setCars(String[] cars) {
		this.cars = cars;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", pwd=" + pwd + ", gender=" + gender + ", hobby=" + Arrays.toString(hobby)
				+ ", address=" + address + ", cars=" + Arrays.toString(cars) + ", info=" + info + "]";
	}

}
