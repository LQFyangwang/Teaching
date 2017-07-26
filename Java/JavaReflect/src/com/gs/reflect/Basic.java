package com.gs.reflect;

import org.junit.Test;

public class Basic {
	
	private String id;
	protected String name;
	public int age;
	
	public Basic() {
		
	}
	
	public Basic(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public void method(String a, int b) {
		System.out.println("public void method...");
		System.out.println(a + ", " + b);
	}
	
	private void method1() {
		System.out.println("private void method1...");
	}
	
	public void test() {
		if (getClass() == Basic.class) {
			System.out.println("这是一个com.gs.reflect.Basic类");
		}
	}
	
	public static void main(String[] args) {
		Class clazz = Basic.class;
		
		System.out.println(clazz.getName()); // 获取类名称
		Basic b = new Basic();
		b.test();
	}

}
