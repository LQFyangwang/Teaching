package com.gs.datastructure;

import org.junit.Test;

public class MyArrayListTest {
	
	@Test
	public void testAdd() {
		MyArrayList<String> list = new MyArrayList<String>();
		for (int i = 0; i < 40; i++) {
			list.add("aaaaa" + i);
		}
//		list.add("bbbbb");
//		list.add("cccc");
//		list.add("dddd");
		for (int i = 0, size = list.size(); i < size; i++) {
			System.out.println(list.get(i));
		}
	}

}
