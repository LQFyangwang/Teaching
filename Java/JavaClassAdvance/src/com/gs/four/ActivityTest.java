package com.gs.four;

public class ActivityTest {
	public static void main(String[] args) {
		StudentActivity sa = new StudentActivity();
		sa.sing();
		sa.dance();
		Activity aa = new ActorActivity(); // 用接口来引用某个具体的类对象
		aa.sing();
		aa.dance();
	}

}
