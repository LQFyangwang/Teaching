package com.gs.exercise;

public class User {

	String name;
	String pwd;
	static int count; // 所有User对象都共享的数据, 在构造方法中对count进行修改，也就意味着在实例化一个对象时就计数
	
	public User() {
		count++;
	}
	
	public User(String name) {
		count++;
		this.name = name;
	}
	
	public User(String name, String pwd) {
		count++;
		this.name = name;
		this.pwd = pwd;
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
	
	public static int getCount() {
		return count;
	}
	
	public String toString() {
		return "User Name: " + name + ", User pwd: " + pwd + ", count: " + count;
	}
	
	public static void main(String[] args) {
		User user1 = new User("user1", "user1");
		User user2 = new User();
		user2.setName("user2");
		user2.setPwd("pwd");
		
		System.out.println(User.getCount());
		System.out.println(user1);
		System.out.println(user2);
	}
	
}
