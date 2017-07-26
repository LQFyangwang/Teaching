package com.gs.thread;

public class Reader {
	
	public static void reader1() {
		while (true) {
			System.out.println("reader1在读书....");
		}
	}
	
	public static void reader2() {
		while (true) {
			System.out.println("reader2在读书....");
		}
	}
	
	public static void main(String[] args) {
		reader1(); // 由于reader1一直在读书，则reader2根本没有机会获取cpu时间，所以reader2永远不可能进入到读书方法，所以需要借助线程来解决
		reader2();
	}

}
