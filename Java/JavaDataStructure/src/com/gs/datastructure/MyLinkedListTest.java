package com.gs.datastructure;

import org.junit.Test;

public class MyLinkedListTest {

	@Test
	public void testAdd() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		list.add("aaa");
		list.add("bbbb");
		list.add("ccc");
		list.add("dddd");
		System.out.println("·µ»ØµÄÖµ£º" + list.remove(3));
		list.removeAll();
		for (int i = 0, size = list.size(); i < size; i++) {
			System.out.println(list.get(i));
		}
	}
	
}
