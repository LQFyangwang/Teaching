package com.gs.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		try {
		ServerSocket ss = new ServerSocket(8888);
			while (true) {
				Socket socket = ss.accept(); // 等待客户端的连接，返回客户端与服务端建立好的连接
				System.out.println(socket.getInetAddress().getHostName()); // 在服务端调用 获取的是客户端的IP地址
				System.out.println(socket.getLocalAddress().getHostName()); // 在服务端调用 获取的是服务端自己的IP地址
				OutputStream out = socket.getOutputStream(); // 获取此socket连接的输出流，可以从服务端发送数据到客户端
				out.write("你好".getBytes());
				InputStream is = socket.getInputStream();
				byte[] bytes = new byte[1024];
				int total = is.read(bytes);
				System.out.println(new String(bytes, 0, total));
				System.out.println("客户端已经连接");
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
