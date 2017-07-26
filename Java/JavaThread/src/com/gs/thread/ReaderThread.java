package com.gs.thread;

public class ReaderThread extends Thread {
	
	
	
	public ReaderThread() {
		super();
	}

	public ReaderThread(String name) {
		super(name);
	}

	// 重写run方法，线程所跑的代码都在此run方法内
	@Override
	public void run() {
		while (true) {
			System.out.println(this.getName());
			System.out.println("read1在读书...");
		}
	}
	
	public static void main(String[] args) {
		ReaderThread rt = new ReaderThread("reader1");
		Reader2Thread rt2 = new Reader2Thread();
		rt.start(); // 启动线程
		rt2.start();
	}

}

class Reader2Thread extends Thread {
	
	@Override
	public void run() {
		while (true) {
			System.out.println("reader2在读书...");
		}
	}
	
}
