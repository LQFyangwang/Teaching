package com.gs.exercise;

public class ThisTest {
	
	String name;
	
	public ThisTest() {
		
	}
	
	public ThisTest(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
		// return this.name; //可以省略this.
	}
	
	public void read() {
		
	}
	
	public void plan() {
		read(); // this.read(); this.read()可以省略this.
		this.read();
	}

}
