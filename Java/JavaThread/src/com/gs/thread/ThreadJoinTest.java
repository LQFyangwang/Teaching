package com.gs.thread;

public class ThreadJoinTest {
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new ThreadJoin1());
		Thread thread2 = new Thread(new ThreadJoin2());
		thread1.start();
		try {
			thread1.join(); // 等待thread1线程执行完毕，当thread1执行完毕后，其他线程再执行
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.start(); // 其他线程的启动放在join后
	}

}

class ThreadJoin1 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("aaaaaaa");
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class ThreadJoin2 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			System.out.println("bbbbbbb");
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
