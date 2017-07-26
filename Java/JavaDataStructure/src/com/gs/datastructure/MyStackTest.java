package com.gs.datastructure;

import org.junit.Test;

public class MyStackTest {
	
	@Test
	public void testPush() {
		MyStack<String> stack = new MyStack<String>();
		stack.push("aaa");
		stack.push("bbb");
		stack.push("ccc");
		System.out.println(stack.peek());
		
		System.out.println(stack.pop());
		
		System.out.println(stack.size());

		System.out.println(stack.pop());
	}
	
}
