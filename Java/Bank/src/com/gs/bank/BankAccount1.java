package com.gs.bank;

import java.util.Random;

public class BankAccount1 {
	
	String name;
	int number;
	String password;
	double money;
	
	public BankAccount1() {
		
	}
	
	public BankAccount1(String name, String password) {
		this.name = name;
		this.password = password;
		this.number = new Random().nextInt(50000);
	}
	
	public String toString() {
		return "’À∫≈√˚£∫" + name + ", ’À∫≈£∫ " + number + ", ”‡∂Ó£∫ " + money;
	}
	
}
