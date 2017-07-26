package com.gs.qq;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class QServer {
	private List<MySocket> sockets;
	
	public QServer() {
		sockets = new ArrayList<MySocket>();
		new Thread(new Connector()).start();
	}
	
	class Connector implements Runnable {
		
		public void run() {
			try {
				ServerSocket ss = new ServerSocket(8888);
				while (true) {
					System.out.println("等待...");
					Socket s = ss.accept();
					MySocket mySocket = new MySocket();
					mySocket.setSocket(s);
					ObjectInputStream in = new ObjectInputStream(s.getInputStream());
					QUser user = (QUser) in.readObject();
					mySocket.setUser(user);
					sockets.add(mySocket);
					new Thread(new ReadThread(s)).start();
					System.out.println(user.getQq() + "已经连接上");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	class ReadThread implements Runnable {

		private Socket socket;
		
		public ReadThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			while (true) {
				System.out.println("running...");
				try {
					InputStream in = socket.getInputStream();
					ObjectInputStream oin = new ObjectInputStream(in);
					System.out.println("服务端读取客户端消息");
					Object obj = oin.readObject();
					if (obj instanceof QUser) {
						QUser user = (QUser) obj;
						System.out.println(user);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
