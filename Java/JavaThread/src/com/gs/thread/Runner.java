package com.gs.thread;

public class Runner implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("正在跑第" + (i + 1) + "圈");
			try {
				Thread.sleep(2 * 1000); // 让线程休眠2000ms，单位为ms
			} catch (InterruptedException e) { // java.lang.InterruptedException， 线程中断异常
				e.printStackTrace();
			} 
		}
	}
	
	public static void main(String[] args) {
		new Thread(new Runner()).start();
	}

}
