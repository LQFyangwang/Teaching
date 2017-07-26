package com.gs.qq;

import java.net.Socket;

public class MySocket {
	
	private Socket socket;
	private QUser user;
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public QUser getUser() {
		return user;
	}
	public void setUser(QUser user) {
		this.user = user;
	}
	
}
