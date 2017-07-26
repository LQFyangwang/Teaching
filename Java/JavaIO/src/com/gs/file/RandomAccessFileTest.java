package com.gs.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
	
	public static void main(String[] args) {
		File file = new File("d:/javaio/abc.txt");
		if (file.exists()) {
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "r");
//				int a = raf.read(); // 读入文件中第一个字符，返回此字符所对应的ascii
//				System.out.println(a);
				byte[] bytes = new byte[(int) file.length()];
				raf.read(bytes); // 从文件中读入字节放到自定义的字节数组中
				for (byte b : bytes) {
					System.out.println(b);
				}
			
//				String s = raf.readUTF(); // java.io.EOFException 文件结尾异常 end of file
//				System.out.println(s);
				String s = new String(bytes, "unicode"); // 把bytes数组转化成一个字符串，第二个参数表示使用什么编码来转化
				System.out.println(s);
				
				System.out.println("*********************");
				byte[] bytes1 = new byte[10];
				raf.seek(10); // 在读取前先定位到想要的位置
				raf.read(bytes1, 5, 5); // 第一个参数是目标byte数组，第二个参数指从目标数组的第几个位置开始存储，第三个参数是存储的长度
				String s1 = new String(bytes1, "unicode");
				System.out.println(s1);
				raf.close();
				
				System.out.println("**************输出******************");
				File f = new File("d:/javaio/rafoutput.txt");
				if (!f.exists()) {
					f.createNewFile();
				}
				RandomAccessFile rafOut = new RandomAccessFile(f, "rw");
				// rafOut.writeChars("ajflkdjfldskjfkdlfjdskl"); // 每个字符间自动加了空格
				String str = "ajflkdjfldskjfkdlfjdskl中文\r\nafdsfdsfds"; // \r\n进行文件的换行操作
				rafOut.write(str.getBytes("unicode")); // str.getBytes("unicode")表示把字符串通过指定的编码格式转化成字节数组
				rafOut.writeInt(100); // 100是asscii码，对应字符d
				rafOut.writeDouble(100.0);
				rafOut.close();
			} catch (FileNotFoundException e) { // 文件未找到异常，运行时异常
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
