package com.gs.file;

import java.io.File;
import java.io.IOException;

public class FileTest {
	
	public static void main(String[] args) {
		
		System.out.println("此系统下的路径的分隔符为：" + File.separator);
		
		File file = new File("d:" + File.separator + "javaio" + File.separator + "javaio.txt"); // java 中的File指定文件路径的时候，直接使用/，而不是windows系统中的\
		if (file.exists()) {// 判断文件是否存在
			System.out.println("文件的绝对路径： " + file.getAbsolutePath()); // 获取文件的绝对路径
			System.out.println("文件的名称： " + file.getName());
			System.out.println("文件分区的总大小： "  + file.getTotalSpace()); //存储当前文件的盘符（分区）的总 大小，  返回的单位是字节
			System.out.println("文件分区的可用大小： "  + file.getUsableSpace());
			System.out.println("文件大小： " + file.length() + "字节"); // 单位为字节
			
			file.delete(); // 删除文件，不会放回收站
		}
		System.out.println("***********************************");
		File file1 = new File("d:/javaio/a");
		if (file1.exists()) {
			if (file1.isFile()) { // 判断是否为普通文件
				System.out.println(file1.length());
			} else if (file1.isDirectory()) { // 判断是否为目录
				System.out.println(file1.length()); // 目录是没有大小的
				System.out.println("这是目录");
			}
		}
		System.out.println("*********以下是创建目录**********");
		File file2 = new File("d:/javaio/b");
		if (!file2.exists()) {
			file2.mkdir(); // 创建目录，只支持一个层级
		}
		File file3 = new File("d:/javaio/c/c1");
		if (!file3.exists()) {
			file3.mkdirs(); // 创建目录，支持多个层级
		}
		
		System.out.println("*********以下是创建文件**********");
		File file4 = new File("d:/javaio/java创建的文件.txt");
		if (!file4.exists()) {
			try {
				file4.createNewFile(); // 创建新文件 
				
			} catch (IOException e) { // java.io.IOException表示输入输出异常，运行时异常
				e.printStackTrace();
			}
		}
		file4.renameTo(new File("d:/javaio/java创建的文件1.txt"));
		
		System.out.println("**********读取javaio最外层的目录************");
		File file5 = new File("d:" + File.separator + "javaio");
		if (file5.exists()) {
			String[] fileList = file5.list(); // 获取某个目录下的所有目录和文件的名称，返回所有名称所组成的字符串数组
			File[] files = file5.listFiles(); // 获取某个目录下的所有目录和文件的File对象，返回所有File对象组成的数组
			for (String str : fileList) {
				System.out.println(str);
			}
			for (File f : files) {
				if (f.isFile()) {
					System.out.println("文件名称： " + f.getName() + ", 大小 ： " + f.length());
				} else if (f.isDirectory()) {
					System.out.println("目录名称 ：" + f.getName());
				}
			}
		}
		
	}

}
