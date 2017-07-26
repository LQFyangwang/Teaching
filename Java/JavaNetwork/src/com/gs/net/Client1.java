package com.gs.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client1 {
	
	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket();
			byte[] bytes = "你好".getBytes();
			DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("localhost"), 8888);
			ds.send(dp); // 发送数据报
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
