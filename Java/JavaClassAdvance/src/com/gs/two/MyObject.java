package com.gs.two;

public class MyObject {
	
	private String id;
	
	public MyObject() {
		
	}
	
	public MyObject(String id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	/**
	public String toString() {
		// getClass()获取一个Class对象
		// Class类的getName()方法可以获取到指定类对象的类名全限定名
		// hashCode()方法返回此对象在内存中的地址（哈希码）
		Class clazz = getClass();
		return getClass().getName() + "@" + Integer.toHexString(hashCode());
	}
	*/
	
	/**
	 * 重写Object类里的toString方法
	 */
	public String toString() {
		return "MyObject id: " + id;
	}
	
	/**
	 * 重写equals方法，主要用于判断对象的数据内容是否一致，如果一致，则返回true，否则返回false
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true; // 如果本类对象同需要比较的对象指向同一个引用，则是同一个对象
		}
		if (obj instanceof MyObject) { // 如果需要比较的对象属于本类，那么对对象所持有的属性进行相等判断，如果对属性判断为真，则表示是同一个对象
			if (((MyObject) obj).getId().equals(this.getId())) {
				return true;
			}
		} else { // 如果需要比较的对象不属于本类，直接返回false
			return false;
		}
		return false;
	}
	
	public static void main(String[] args) {
		MyObject obj = new MyObject("aa");
		MyObject obj1 = new MyObject("aa");
		MyObject obj2 = obj; // obj2与obj指向同一个引用
		System.out.println(obj);
		System.out.println("obj == obj1: " + (obj == obj1)); // false
		System.out.println("obj == obj2: " + (obj == obj2)); // true
		String a = new String("aa");
		String b = new String("aa");
		String c = a;
		System.out.println("a == b: " + (a == b)); // false
		System.out.println("a == c: " + (a == c)); // true
		
		// 未重写equals，则直接使用 == 判断
		System.out.println("obj equals obj1: " + obj.equals(obj1)); // false==》true
		System.out.println("obj equals obj2: " + obj.equals(obj2)); // true
		
		// String类重写了继承过来的equals方法， 对字符串中的每一个字符进行判断
		System.out.println("a equals b: " + a.equals(b)); // true
		System.out.println("a equals c: " + a.equals(c)); // true
	}

}
