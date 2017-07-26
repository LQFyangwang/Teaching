package com.gs.one;

public class MethodAccess {
	
	public void eat() {
		System.out.println("eat();");
	}

	private void eat(int a) {
		
	}
	
	protected void sleep() {
		System.out.println("sleep()");
	}
	
	void run() {
		eat();
		eat(10);
		System.out.println("run()");
	}
	
	private void read() {
		System.out.println("read()");
	}
	
	public static void main(String[] args) {
		MethodAccess ma = new MethodAccess();
		ma.eat();
		ma.sleep();
		ma.run();
		ma.read();
	}

}
