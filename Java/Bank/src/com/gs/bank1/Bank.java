package com.gs.bank1;

public interface Bank {
	
	public Account openAccout(String name, String pwd);
	public void save(double money);
	public Account query(String number, String pwd);
	public void transfer();
	public void get();

}
