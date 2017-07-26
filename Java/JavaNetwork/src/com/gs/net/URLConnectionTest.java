package com.gs.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionTest {
	
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://www.baidu.com");
			URLConnection conn = url.openConnection(); // 连接上baidu.com
			InputStream in = conn.getInputStream(); // 获取连接到此主机的输入流，可以从页面中获取数据
			byte[] bytes = new byte[1024 * 1000];
			int total = in.read(bytes, 0, bytes.length);
			System.out.println(total);
			System.out.println(new String(bytes, 0, total));
			int length = conn.getContentLength();
			System.out.println(length);
			String type = conn.getContentType();
			System.out.println(type);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
