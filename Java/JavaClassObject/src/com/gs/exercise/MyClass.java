package com.gs.exercise;

public class MyClass {
	
	String name;
	static int a;
	
	public void test() {
		test1(); // 实例方法可以调用类方法
		name = "name";
		a = 10; // 实例方法可以调用类变量
	}
	
	public static void test1() {
		// test(); // 类方法不可调用实例方法
		// name = "name"; // 类方法不可以调用实例变量
		a = 10;
	}

}
