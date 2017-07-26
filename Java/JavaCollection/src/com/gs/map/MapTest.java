package com.gs.map;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class MapTest {
	
	public static void main(String[] args) {
		Hashtable<String, String> map = new Hashtable<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value1"); // 把元素添加到Map中，每个元素可以通过指定的键找到
		
		System.out.println(map.get("key3")); // get(Object key)根据指定的键找到其对应的值
		map.remove("key3");
		if (map.containsKey("key3")) { // 判断是否包含有某个键
			System.out.println(map.get("key3")); // 根据键remove后，则再次获取，已经不存在了，所以返回为null
		}
		if (map.containsValue("value1")) { // 判断是否包含有某个值
			System.out.println("有value1值");
		}
		// 先获取每一个条目放在set中的对象
		Set<Entry<String, String>> entrySet = map.entrySet(); // Entry是指一个键值对，表示一个条目
		// 对存储有所有条目的Set集合获取迭代器
		Iterator<Entry<String, String>> ite = entrySet.iterator();
		while (ite.hasNext()) {
			Entry<String, String> entry = ite.next(); // 此迭代器中的每一个项目就是一个条目，该条目包含有key和key所对应的value
			System.out.println("Key: " + entry.getKey() + ", value: " + entry.getValue()); // 使用Entry条目类的getKey()获取此条目的键，getValue()获取此条目的值
		}
	}

}
