package com.gs.output;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamTest {

	public static void main(String[] args) {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File("d:/javaio/dos.txt")));
			dos.write("adfdkfjdsklfj".getBytes());
			dos.writeInt(65);
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
