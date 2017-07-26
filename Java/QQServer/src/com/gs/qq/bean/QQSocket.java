package com.gs.qq.bean;

import java.net.Socket;

import com.gs.bean.Account;

/**
 * 用于保存登录到服务端的QQ用户与建立的Socket连接的关系，一个QQ用户登录后，对应于一个socket连接
 *
 */
public class QQSocket {
	
	private Account account;
	private Socket socket;
	
	public QQSocket() {

	}
	public QQSocket(Account account, Socket socket) {
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
