package com.gs.thread;

public class ThreadPriority {
	public static void main(String[] args) {
		Thread thread1 = new Thread(new Thread1());
		thread1.setPriority(Thread.MIN_PRIORITY);
		thread1.setDaemon(true);
		Thread thread2 = new Thread(new Thread2());
		thread2.setPriority(Thread.MAX_PRIORITY);
		// thread1设置了最低优先级，也就意味着哪怕是先start，但是不一定最先获取到cpu时间
		thread1.start();
		// thread2设置了最高优先级，也就意味着哪怕是后start，也是有更大的机会获取到cpu时间
		thread2.start();
	}
}

class Thread1 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("thread1 running....");
		}
	}
	
}

class Thread2 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("thread2 running....");
		}
	}
	
}
