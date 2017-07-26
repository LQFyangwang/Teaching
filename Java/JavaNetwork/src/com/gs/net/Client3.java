package com.gs.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client3 {
	
	public Client3() {
		try {
			Socket socket = new Socket(InetAddress.getByName("localhost"), 8888);
			new Thread(new ReadThread(socket)).start();
			new Thread(new WriteThread(socket)).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class ReadThread implements Runnable {

		private Socket socket;
		
		public ReadThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < 20; i++) {
				try {
					Thread.sleep(5000);
					InputStream in = socket.getInputStream();
					ObjectInputStream oin = new ObjectInputStream(in);
					System.out.println("服务端读取客户端消息");
					Object obj = oin.readObject();
					if (obj instanceof QUser) {
						QUser user = (QUser) obj;
						System.out.println(user.getQq() + ", " + user.getName());
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	class WriteThread implements Runnable {

		private Socket socket;
		
		public WriteThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < 20; i++) {
				try {
					Thread.sleep(5000);
					OutputStream out = socket.getOutputStream();
					System.out.println("客户端向服务端发送消息");
					QUser user = new QUser();
					user.setQq("10000");
					user.setName("马化腾");
					ObjectOutputStream oout = new ObjectOutputStream(out); // 获取对象输出流
					oout.writeObject(user);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Client3();
	}

}
