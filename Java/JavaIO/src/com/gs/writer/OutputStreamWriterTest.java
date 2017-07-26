package com.gs.writer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class OutputStreamWriterTest {

	public static void main(String[] args) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(new File("d:/javaio/osw.txt")), "utf-8");
			osw.write("ÄãºÃ");
			osw.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("d:/javaio/osw.txt")), "utf-8"));
			System.out.println(br.readLine());
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
