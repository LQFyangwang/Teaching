package com.gs.bank;

import java.util.Scanner;

public class Bank {
	
	BankAccount1 acc; // 声明BankAccount1对象
	
	Scanner scanner = new Scanner(System.in);
	
	public void openAccount() {
		System.out.println("用户名");
		String name = scanner.next();
		System.out.println("密码");
		String password = scanner.next();
		acc = new BankAccount1(name, password); // 实例化acc对象
		System.out.println("账号：" + acc.number);
	}
	
	public boolean check() {
		System.out.println("账号");
		int number = scanner.nextInt();
		System.out.println("密码");
		String pwd = scanner.next();
		if (acc.number == number && pwd.equals(acc.password)) {
			return true;
		}
		System.out.println("账号或密码有误，请重试");
		return false;
	}
	
	public void save() {
		if (check()) {
			System.out.println("存款数：");
			acc.money += scanner.nextDouble();
			System.out.println(acc);
		}
	}
	
	public void get() {
		if (check()) {
			System.out.println("取款数");
			acc.money -= scanner.nextDouble();
		}
	}
	
	public void query() {
		if (check()) {
			System.out.println(acc);
		}
	}
	
	public void transfer() {
		if (check()) {
			// TODO
		}
	}
	
	public void exit() {
		scanner.close();
		System.exit(-1);
	}
	
}
