package com.gs.two;

public class Worker1 extends Person1 {

	private String company; // 公司
	double salary; // 薪资
	
	static {
		System.out.println("Worker1 static方法块");
	}
	
	{
		System.out.println("Worker1 动态方法块");
	}
	
	public Worker1() {
		System.out.println("Worker1()");
	}
	
	public Worker1(String name, int age, String company, double salary) {
		// super关键字，用来表示父类对象， super();表示父类的构造方法
		// 使用super调用父类的构造方法，必须放在最前面
		super(name, age); // 给子类本身的name和age赋值
		System.out.println("Worker1(String, int, String, double)");
		this.company = company;
		this.salary = salary;
		// super.age = 10; // 给子类本身赋值，而不是父类
		System.out.println("");
		super.read(); // 用super来调用成员方法，则调用的父类的方法
		read();
	}
	
	public void work() {
		
	}
	
	public void read() {
		System.out.println("worker1 read()");
	}
	/**
	 * 子类中重写父类中的方法，子类方法的访问权限不能小于父类中方法定义的访问权限
	protected void run() {
		
	}
	*/
	
}
