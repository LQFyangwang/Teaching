package com.gs.datastructure;

import org.junit.Test;

public class MyQueueTest {

	@Test
	public void testEnque() {
		MyQueue<String> queue = new MyQueue<String>();
		queue.enque("aaa");
		queue.enque("bbb");
		queue.enque("ccc");
		
		System.out.println(queue.size());
		
		System.out.println(queue.deque());
		
		System.out.println(queue.size());
		
		System.out.println(queue.deque());
	}
	
}
