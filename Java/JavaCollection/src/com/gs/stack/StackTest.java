package com.gs.stack;

import java.util.Iterator;
import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.push("item1");
		stack.push("item2");
		stack.push("item3");
		
		String item = stack.pop(); // 把在栈顶端的数据移出来
		System.out.println(item);
		int index = stack.search("item1"); // contains返回是否包含有元素，而search返回该元素的索引
		System.out.println(index);
		System.out.println("************************");
		stack.add(0, "item0"); // 由0开始，从栈底到栈顶
		
		if (!stack.isEmpty()) {
			System.out.println(stack.peek()); // 选择栈顶的元素，但并不把其移出来
			Iterator<String> ite = stack.iterator();
			while (ite.hasNext()) {
				System.out.println(ite.next());
			}
		}
		
	}
}
