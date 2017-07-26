package com.gs.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderTest {
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("d:/javaio/abc.txt")), "unicode"));
			// String str = br.readLine();// 只读入一行
			String str = null;
			String allStr = "";
			while ((str = br.readLine()) !=null) { // 每次都读，一直读到没有了
				allStr += str + "\r\n";
			}
			System.out.println(allStr.substring(0, allStr.length() - 2));
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
