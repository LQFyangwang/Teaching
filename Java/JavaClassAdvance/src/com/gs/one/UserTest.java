package com.gs.one;

public class UserTest {

	public static void main(String[] args) {
		User user = new User("AAAA", 20, 170, 100);
		System.out.println(user.getName()); // 不能去访问user对象的private成员变量，所以让User类提供get[属性名称]()
		System.out.println(user.getAge());
		user.setName("BBBB");
		System.out.println(user.getName());
	}

}
