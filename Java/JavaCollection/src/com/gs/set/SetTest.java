package com.gs.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.gs.bean.User;

public class SetTest {
	
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("234"); // 使用add方法添加到集合内
		set.add("123");
		set.add("345"); 
		set.add("123"); // Set不允许添加重复数据，如果有多个重复数据，则只会添加一个
		String[] strr = new String[]{"124","124","12"};
		for(int i = 0 ,len = strr.length; i< len; i++){
			set.add(strr[i]);
		}
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()){
			String st = iter.next();
			System.out.println(st);
		}
		System.out.println("---------------");
		if (!set.isEmpty()) { // 判断集合内部是否有元素
			Object[] strObj = set.toArray();
			for (int i = 0, size = set.size(); i < size; i++) { //　size获取元素个数
				System.out.println(strObj[i]);
			}
		}
		System.out.println("*********************************");
		// set.clear(); // 清空集合内的所有元素
		set.remove("456"); // 从集合中移除指定的元素
		if (set.contains("123")) { // 判断是否包含某个元素
			System.out.println("包含有‘123’");
		}
		if (!set.isEmpty()) {
			Object[] strObj = set.toArray();
			for (int i = 0, size = set.size(); i < size; i++) { //　size获取元素个数
				System.out.println(strObj[i]);
			}
		}
		
		// 迭代器号日发出
		System.out.println("***********************************");
		Iterator<String> ite = set.iterator(); // 获取迭代器，此迭代器可以对set内部的元素进行循环操作
		while(ite.hasNext()) { // 判断迭代器中是否还有下一个元素
			String str = ite.next(); // 获取迭代器里的下一个元素
			System.out.println(str);
		}
		
		System.out.println("******************TreeSet******************");
		TreeSet<String> set1 = new TreeSet<String>();
		set1.add("234"); // 使用add方法添加到集合内
		set1.add("123");
		set1.add("345"); 
		set1.add("123"); // Set不允许添加重复数据，如果有多个重复数据，则只会添加一个
		set1.last(); // 获取TreeSet中的最后 
		Iterator<String> ite1 = set1.iterator();
		while(ite1.hasNext()) {
			String str = ite1.next();
			System.out.println(str);
		}
		
		Set<User> set2 = new HashSet<User>();
		set2.add(new User("1003", "1003"));
		set2.add(new User("1001", "1001"));
		// Set<User> set3 = new TreeSet<User>();
		// set3.add(new User("1003", "1003")); // 这里必须指定User对象的排序规则后才能添加到TreeSet中
		// set3.add(new User("1001", "1001"));
		Iterator<User> set2Ite = set2.iterator();
		// Iterator<User> set3Ite = set3.iterator();
		while(set2Ite.hasNext()) {
			User user = set2Ite.next();
			System.out.println(user);
		}
//		while(set3Ite.hasNext()) {
//			User user = set3Ite.next();
//			System.out.println(user);
//		}
		
	}

}
