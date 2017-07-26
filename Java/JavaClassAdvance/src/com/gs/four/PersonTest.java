package com.gs.four;

public class PersonTest {
	public static void main(String[] args) {
		Student stu = new Student(); // 也可以直接用Person来引用子类对象
		stu.sleep();
		stu.read();
		stu.run();
		
		Person teacher = new Teacher();
		teacher.sleep();
		teacher.read();
	}
}
