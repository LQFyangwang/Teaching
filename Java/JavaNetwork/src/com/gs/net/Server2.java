package com.gs.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
	
	private ServerSocket ss;
	
	public Server2() {
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
					byte[] bytes = new byte[1024];
					int total = in.read(bytes);
					System.out.println("服务端读取客户端消息");
					System.out.println(new String(bytes, 0, total));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
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
					OutputStream out = socket.getOutputStream();
					System.out.println("服务端向客户端发送消息");
					out.write("你好".getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Server2();
	}

}
