package com.gs.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
	
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader(new File("d:/javaio/test.txt"));
			char[] chars = new char[1024];
			int total = fr.read(chars); // 返回读取的总个数
			for (int i = 0; i < total; i++) {
				System.out.println(chars[i]);
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
