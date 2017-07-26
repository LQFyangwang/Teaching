package com.gs.output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayOutputStreamTest {
	
	public static void main(String[] args) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			baos.write("afdsfdsf".getBytes()); // 把字节数组数据放入到ByteArrayOutputStream输出流中，此输出流可以进一步被操作
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
