package com.gs.bank1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyBank implements Bank {

	private Account currentAccount;
	private List<Account> accounts;

	public MyBank() {
		accounts = new ArrayList<Account>();
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	private int getRandomNumber() {
		return new Random().nextInt(10000);
	}

	public Account getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(Account currentAccount) {
		this.currentAccount = currentAccount;
	}

	/**
	 * 把从面板中获取的数据保存在一个Account对象中，并且把该对象存储在List列表中
	 */
	@Override
	public Account openAccout(String name, String pwd) {
		Account account = new Account();
		account.setNumber(getRandomNumber());
		account.setName(name);
		account.setPassword(pwd);
		accounts.add(account);
		return account;
	}

	@Override
	public void save(double money) {
		currentAccount.setMoney(currentAccount.getMoney() + money);
		List<Account> accounts = BankFile.read();
		accounts.set(accounts.indexOf(currentAccount), currentAccount);
		BankFile.save(accounts);
	}

	@Override
	public Account query(String number, String pwd) {
		List<Account> accounts = BankFile.read();
		for (Account a : accounts) {
			if (a.getNumber() == Integer.valueOf(number) && pwd.equals(a.getPassword())) {
				return a;			}
		}
		return null;
	}

	@Override
	public void transfer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void get() {
		// TODO Auto-generated method stub

	}

}
