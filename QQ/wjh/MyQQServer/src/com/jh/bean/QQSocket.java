package com.jh.bean;

import java.net.Socket;

/**
 * 用来保存登入到服务端的QQ用户和其对应的建立的Socket连接关系，一个QQ用户登入好对应一个Socket连接
 * @author Administrator
 *
 */
public class QQSocket {

	private Account account;
	private Socket socket;
	
	public QQSocket() {}
	
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
