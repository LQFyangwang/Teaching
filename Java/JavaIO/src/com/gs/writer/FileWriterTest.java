package com.gs.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {

	public static void main(String[] args) {
		try {
			FileWriter fw = new FileWriter(new File("d:/javaio/fwriter.txt"));
			fw.write("abcedfg\r\nafdsfds\r\nÖÐÎÄ");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
