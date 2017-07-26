package com.gs.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server1 {
	
	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket(8888, InetAddress.getByName("localhost"));
			byte[] bytes = new byte[1024];
			DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
			ds.receive(dp); // 接收数据报，接收到的数据会放在new DatagramPacket时所指定的字节数组里
			System.out.println(new String(bytes, 0, dp.getLength())); // getLength()方法可以获取数据报中数据的长度
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
