package com.gs.bank;

import java.util.Random;
import java.util.Scanner;

public class BankAccount {
	
	String name; // 账号名称
	int number; // 账号
	String password; // 账号密码
	double money; //　账号余额
	
	Scanner scanner = new Scanner(System.in);
	
	public void openAccount() {
		System.out.println("用户名");
		this.name = scanner.next();
		System.out.println("密码");
		String pwd1 = scanner.next();
		System.out.println("确认密码");
		String pwd2 = scanner.next();
		if (pwd1.equals(pwd2)) { // 对两个String字符串进行内容比较
			this.password = pwd1;
			Random random = new Random(); // java.util.Random 生成随机数的类
			this.number = random.nextInt(50000); // random.nextInt()指定生成一个整数的随机数 nextInt(50000)表示从0-50000间生成随机数
			System.out.println("成功开户，账号：" + this.number);
		} else {
			System.out.println("开户失败，两次密码不一致");
		}
	}
	
	public boolean check() {
		System.out.println("输入账号");
		int number = scanner.nextInt();
		System.out.println("输入密码");
		String pwd = scanner.next();
		if (number == this.number && pwd.equals(this.password)) {
			return true;
		}
		System.out.println("账号或密码有误，请重试");
		return false;
	}
	
	public void save() {
		if (check()) {
			System.out.println("存款数：");
			this.money += scanner.nextDouble();
			System.out.println(this);
		} 
	}
	
	public void get() {
		if (check()) {
			System.out.println("取款数： ");
			double money = scanner.nextDouble();
			if (money > this.money) {
				System.out.println("智商不足");
			} else {
				this.money -= money;
				System.out.println("取款成功");
				System.out.println(this);
			}
		}
	}
	
	public void query() {
		if (check()) {
			System.out.println(this);
		}
	}
	
	public void transfer() {
		if (check()) {
			// TODO 以后再实现
		}
	}
	
	public void exit() {
		scanner.close(); 
		System.exit(-1); // System.exit(int status) // 关闭当前应用程序
	}
	
	public String toString() {
		return "账号名：" + name + ", 账号： " + number + ", 余额： " + money;
	}
	
	public static void main(String[] args) {
		BankAccount acc = new BankAccount();
		int choice = 0;
		do {
			System.out.println("Welcome to my Bank");
			System.out.println("1、开户\t2、存款\t3、取款\t4、查询\t5、转账\t6、退出");
			choice = acc.scanner.nextInt();
			switch (choice) {
			case 1:
				acc.openAccount();
				break;
			case 2:
				acc.save();
				break;
			case 3:
				acc.get();
				break;
			case 4:
				acc.query();
				break;
			case 5:
				acc.transfer();
				break;
			case 6:
				acc.exit();
				break;
			default:
				break;
			}
		} while (choice != 6);
	}
}
