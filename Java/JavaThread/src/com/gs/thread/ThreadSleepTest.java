package com.gs.thread;

import java.io.IOException;

public class ThreadSleepTest {

	public static void main(String[] args) throws IOException {
		try {
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread thread1 = new Thread(new ThreadSleep1());
		//thread1.start();
		//thread1.interrupt(); // thread1线程先运行一遍，接着进入了休眠状态，接着调用interrupt使线程中断，从休眠状态改变为非阻塞状态，也就是运行态，所以线程再次重新执行
		
		Thread thread2 = new Thread(new ThreadSleep2());
		thread2.start();
		
		System.in.read();
		thread2.interrupt(); // 唤醒在休眠的thread2
	}
}

class ThreadSleep1 implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("thread1 running...");
				Thread.sleep(5 * 1000 * 60);
			} catch (InterruptedException e) {
				System.out.println("线程被中断"); // 只有线程被中断，才会捕捉到InterruptedException
			}
		}
	}
	
}

class ThreadSleep2 implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("thread2 running....");
				Thread.yield(); // 当前线程应该暂停执行（不确定暂停多长时间，一旦cpu是空闲的，此线程就会重新获取cpu时间），把cpu时间让给其他线程
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				System.out.println("线程被中断");
			}
		}
	}
	
}
