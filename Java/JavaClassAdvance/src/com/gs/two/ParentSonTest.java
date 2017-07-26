package com.gs.two;

public class ParentSonTest {
	
	public static void main(String[] args) {
		Parent parent = new Parent();
		Son son = new Son();
		parent.name = "parent";
		parent.age = 50;
//		son.name = "son";
//		son.age = 20;
		parent.read();
		parent.run();
		son.read();
		son.run();
		System.out.println(parent); // name: parent, age: 50
		System.out.println(son); // name: son, age: 20
	}

}
