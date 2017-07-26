package com.gs.four;

public abstract class Person {
	
	public String name;
	String password;
	protected int age;
	private int height;

	public Person() {
		
	}
	
	public void read() {
		System.out.println("abstract Person read()");
		
	}
	
	public void run() {
		System.out.println("abstract Person run()");
	}
	
	public abstract void sleep(); // ³éÏó·½·¨

}
