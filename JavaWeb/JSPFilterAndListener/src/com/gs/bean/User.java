package com.gs.bean;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {
	
	private int id;
	private String name;
	private String pwd;
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", age=" + age + "]";
	}
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("user对象被绑定到Session");
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("user对象从session中移除");
	}

}
