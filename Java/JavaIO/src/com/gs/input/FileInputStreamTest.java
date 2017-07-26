package com.gs.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest {
	
	public static void main(String[] args) {
			File file = new File("d:/javaio/abc.txt");
			try {
				FileInputStream in = new FileInputStream(file); // FileNotFoundExcepton 文件未找到异常，运行时异常
				FileInputStream in1 = new FileInputStream("d:/javaio/abc.txt"); // 直接指定文件的路径也可以
				byte[] bytes = new byte[(int) file.length()];
				@SuppressWarnings("unused") 
				int total = in1.read(bytes); // 返回共读取了多个字节
				in.close();
				in1.close();
				System.out.println(new String(bytes, "unicode"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
}
