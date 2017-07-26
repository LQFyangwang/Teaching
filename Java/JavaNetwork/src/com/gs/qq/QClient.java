package com.gs.qq;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class QClient {
	
	private QUser user;
	private Socket s;
	
	public QClient(QUser user) {
		this.user = user;
		try {
			s = new Socket(InetAddress.getByName("localhost"), 8888);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(user);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMsg(QUser user) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
