package com.gs.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {
	
	public static void main(String[] args) {
		List<Integer> numbers = new LinkedList<Integer>();
		numbers.add(1001);
		numbers.add(1002);
		numbers.add(1001);
		numbers.add(1003);
		for (int i = 0, size = numbers.size(); i < size; i++) {
			Integer num = numbers.get(i); // get(int index)获取指定索引上的元素
			System.out.println(num);
		}
		System.out.println("set 1001***********");
		numbers.set(2, new Integer(10001));
		for (int i = 0, size = numbers.size(); i < size; i++) {
			Integer num = numbers.get(i); // get(int index)获取指定索引上的元素
			System.out.println(num);
		}
		System.out.println("remove **************");
		if (numbers.contains(new Integer(1003))) {
			numbers.remove(new Integer(1003)); // 移除指定的对象
		}
		numbers.remove(1); // 根据索引移除元素
		for (int i = 0, size = numbers.size(); i < size; i++) {
			Integer num = numbers.get(i); // get(int index)获取指定索引上的元素
			System.out.println(num);
		}

		System.out.println("1001的位置*****");
		System.out.println(numbers.indexOf(new Integer(1001)));// 不存在则返回-1，存在则返回该元素对应的索引
		
		numbers.clear();
		System.out.println("clear *********");
		if (!numbers.isEmpty()) {
			Iterator<Integer> ite = numbers.iterator();
			while (ite.hasNext()) {
				Integer num = ite.next();
				System.out.println(num);
			}
		}
	}

}
