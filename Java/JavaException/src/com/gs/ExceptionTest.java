package com.gs;

public class ExceptionTest {
	public static void main(String[] args) {
		String a = null;
		// a.equals("abc"); // java.lang.NullPointerException
		int[] b = new int[10];
		// b[10] = 10; // java.lang.ArrayIndexOutOfBoundsException
		String c = "abc";
		// int d = Integer.parseInt(c);
		int e = 0;
		int f = 5 / e;
		Son son = new Son();
		Person p = son;
		Person p1 = new Person(); // 创建父类对象
		Son son1 = (Son) p1; //　强制把父类对象转化为子类
	}
}

class Person {
	public void test() {
		
	}
}

class Son extends Person {
	
}
