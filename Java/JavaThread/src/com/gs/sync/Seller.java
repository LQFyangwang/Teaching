package com.gs.sync;

public class Seller extends Thread {

	private static int total = 100;
	
	public Seller(String name) {
		super(name);
	}

	@Override
	public void run() {
		sell();
	}
	
	private void sell() {
		while (total > 0) {
			System.out.println(getName() + "，售出一张票，票号：" + total);
			total -= 1; // 在seller1未执行此行代码前seller2就可能进入了run方法，并且已经把前一行输出了
			System.out.println(getName() + "，剩余开始票号：" + total);
		}
	}
	
	public static void main(String[] args) {
		Seller seller1 = new Seller("seller1");
		seller1.start();
		Seller seller2 = new Seller("seller2");
		seller2.start();
		Seller seller3 = new Seller("seller3");
		seller3.start();
		Seller seller4 = new Seller("seller4");
		seller4.start();
	}

}
