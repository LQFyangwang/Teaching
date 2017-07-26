package com.gs.bank;

public class BankTest {
	
	public static void main(String[] args) {
		Bank bank = new Bank(); // 实例化Bank对象，测试Bank类中的方法
		int choice = 0;
		do {
			System.out.println("Welcome to my Bank");
			System.out.println("1、开户\t2、存款\t3、取款\t4、查询\t5、转账\t6、退出");
			choice = bank.scanner.nextInt();
			switch (choice) {
			case 1:
				bank.openAccount();
				break;
			case 2:
				bank.save();
				break;
			case 3:
				bank.get();
				break;
			case 4:
				bank.query();
				break;
			case 5:
				bank.transfer();
				break;
			case 6:
				bank.exit();
				break;
			default:
				break;
			}
		} while (choice != 6);
	}

}
