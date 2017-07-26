package com.gs.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
	
	public Client2() {
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
					byte[] bytes = new byte[1024];
					int total = in.read(bytes);
					System.out.println("客户端读取服务端消息");
					System.out.println(new String(bytes, 0, total));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
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
					out.write("hello".getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Client2();
	}

}
