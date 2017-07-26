package com.gs.four;

public class Teacher extends Person {

	// @Override 表示该方法是重写的方法
	@Override
	public void sleep() {
		System.out.println("Teacher sleep()");
	}
	
	@Override
	public void read() {
		System.out.println("Teacher read()");
	}

}
