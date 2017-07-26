package com.gs.thread;

public class ReaderThreadRun implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println("reader1在读书...");
		}
	}
	
	public static void main(String[] args) {
		ReaderThreadRun rtr = new ReaderThreadRun();
		Thread rtrThread = new Thread(rtr, "reader1"); // 使用Thread类来创建实现了Runnable接口的线程类
		rtrThread.start();
		Reader2ThreadRun rtr2 = new Reader2ThreadRun();
		Thread rtr2Thread = new Thread(rtr2, "reader2");
		rtr2Thread.start();
	}

}

class Reader2ThreadRun implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println("reader2在读书...");
		}
	}
	
}
