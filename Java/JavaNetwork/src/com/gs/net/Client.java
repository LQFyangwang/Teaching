package com.gs.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) {
		InetAddress address;
		try {
			address = InetAddress.getByName("localhost"); // 获取服务器地址
			System.out.println(new String(address.getAddress()));
			Socket socket = new Socket(address, 8888);
			System.out.println(socket.getInetAddress().getHostName()); // 在客户端调用 获取的是服务端的IP地址
			System.out.println(socket.getLocalAddress().getHostName()); // 在客户端调用 获取的是客户端自己的IP地址
			InputStream is = socket.getInputStream();
			byte[] bytes = new byte[1024];
			int total = is.read(bytes);
			System.out.println(new String(bytes, 0, total));
			OutputStream out = socket.getOutputStream();
			out.write("hello".getBytes());
		} catch (UnknownHostException e) { // 未知的服务器异常
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
