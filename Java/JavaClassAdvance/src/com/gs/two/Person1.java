package com.gs.two;

public class Person1 {

	String name;
	int age;
	
	/**
	 * 静态方法块
	 */
	static {
		System.out.println("Person1 static方法块");
	}
	
	/**
	 * 动态方法块
	 */
	{
		System.out.println("Person1 动态方法块");
	}
	
	public Person1() {
		System.out.println("Person1()");
	}
	
	public Person1(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public void read() {
		System.out.println("Person1 read()");
	}
	
	public void run() {
		
	}
	
}
