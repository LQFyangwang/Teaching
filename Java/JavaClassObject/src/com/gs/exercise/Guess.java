package com.gs.exercise;

import java.util.Scanner;

public class Guess {

	String name;
	int age;

	public Guess() {

	}

	public Guess(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void guess() {
		Scanner scanner = new Scanner(System.in);
		boolean flag = false; // 标记没有猜对
		do {
			System.out.println("请输入姓名");
			String name = scanner.next();
			System.out.println("请输入年龄");
			int age = scanner.nextInt();
			if (age == this.age && name.equals(this.name)) {
				flag = true;
				System.out.println("神了！");
			} else {
				System.out.println("笨了！");
			}
		} while (!flag);
		scanner.close();
	}

	public static void main(String[] args) {
		Guess guess = new Guess("AA", 20);
		guess.guess();
	}

}
