package com.gs.two;

public class Parent {
	
	String name;
	int age;
	
	public void read() {
		System.out.println("Parent read()");
	}
	
	public void run() {
		System.out.println("Parent run()");
	}
	
	public String toString() {
		return "name: " + name + ", age:¡¡" + age;
	}

}
