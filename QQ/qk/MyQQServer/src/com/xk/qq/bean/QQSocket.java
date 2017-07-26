package com.xk.qq.bean;

import java.net.Socket;

import com.xk.bean.Account;

/**
 * 用于保存登录到服务端的QQ用户与建立的Socket连接的关系，一个QQ用户登录后，对应于一个socket连接
 *
 */

public class QQSocket {
	private Account account; // 用户
	private Socket socket;	// socket
	
	public QQSocket(){
		
	}
	
	public QQSocket(Account account,Socket socket) {
		this.account = account;
		this.socket = socket;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
}
