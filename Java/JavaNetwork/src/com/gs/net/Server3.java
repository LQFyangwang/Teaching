package com.gs.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
	
	private ServerSocket ss;
	
	public Server3() {
		try {
			ss = new ServerSocket(8888);
			while (true) {
				System.out.println("等待连接...");
				Socket socket = ss.accept();
				System.out.println("连接成功...");
				new Thread(new ReadThread(socket)).start();
				new Thread(new WriteThread(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从客户端读取消息
	 */
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
	
	/**
	 * 向客户端写消息
	 */
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
					QUser user = new QUser();
					user.setQq("10000");
					user.setName("马化腾");
					OutputStream out = socket.getOutputStream();
					ObjectOutputStream oout = new ObjectOutputStream(out); // 获取对象输出流
					System.out.println("服务端向客户端发送消息");
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
		new Server3();
	}

}
