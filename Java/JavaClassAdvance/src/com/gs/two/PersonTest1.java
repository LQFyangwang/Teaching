package com.gs.two;

public class PersonTest1 {
	
	public static void main(String[] args) {
		Person1 person = new Person1();
		person.age = 20;
		System.out.println(person.age);
		Worker1 worker = new Worker1("worker1", 15, "ABC", 3000); // 子类构造方法前先执行父类的构造方法
		System.out.println(person.age);
		worker.read(); // Worker1重写了Person1父类中的read()方法 ，当worker对象调用read方法时，使用子类里重写的方法
	}

}
