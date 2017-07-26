package com.gs.input;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataInputStreamTest {

	public static void main(String[] args) {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(new File("d:/javaio/test.txt")));
			byte[] bytes = new byte[1024];
			int total = dis.read(bytes);
			System.out.println(new String(bytes, 0, total));
			dis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
