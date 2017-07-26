package com.gs.exercise;

public class Student {
	
	String name;
	int age;
	float score;
	
	public int parseInt(String str) {
		return Integer.valueOf(str);
	}
	
	public float parseFloat(String str) {
		return Float.valueOf(str);
	}
	
	/**
	 * 如果一个类不重新实现toString方法，则在System.out.println语句中打印对象，输出的是
	 * 对象的简单类名+@+hashcode(哈希码)
	 * 
	 * 如果想要输出本类相关的一些属性值或其他信息，则重新实现toString方法
	 */
	public String toString() {
		return "name: " + name + ", age: " + age + ", score: " + score;
	}

}
