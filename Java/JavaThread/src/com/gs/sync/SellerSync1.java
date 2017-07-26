package com.gs.sync;

public class SellerSync1 implements Runnable {

	private int total = 100;
	
	@Override
	public void run() {
		while (true) {
			sell();
		}
	}
	
	private void sell() {
		synchronized (SellerSync1.class) {
			if (total > 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Thread.currentThread()获取当前正在执行的线程
				System.out.println(Thread.currentThread().getName() + "售出一张票，票号：" + total);
				total -= 1;
				System.out.println(Thread.currentThread().getName() + "剩余开始票号：" + total);
			}
		}
	}
	
	public static void main(String[] args) {
		SellerSync1 seller = new SellerSync1();
		Thread seller1 = new Thread(seller, "seller1");
		seller1.start();
		Thread seller2 = new Thread(seller, "seller2");
		seller2.start();
		Thread seller3 = new Thread(seller, "seller3");
		seller3.start();
		Thread seller4 = new Thread(seller, "seller4");
		seller4.start();
	}

}
