package com.gs.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileCopy {
	
	/**
	 * 把指定路径的源文件copy到指定路径的目标文件
	 * 目标文件需要判断是否存在，如果不存在，则创建新的文件
	 * 
	 * @param originalPath 源文件的路径
	 * @param desPath 目标文件的路径
	 */
	public static void copy(String originalPath, String desPath) {
		copy(new File(originalPath), new File(desPath));
	}
	
	public static void copy(File originalFile, File desFile) {
		try {
			RandomAccessFile oRaf = new RandomAccessFile(originalFile, "r");
			RandomAccessFile desRaf = new RandomAccessFile(desFile, "rw");
			// 开始读取源文件数据
			byte[] oBytes = new byte[(int) originalFile.length()];
			oRaf.readFully(oBytes);
			desRaf.write(oBytes);
			oRaf.close();
			desRaf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FileCopy.copy("d:/javaio/abc.txt", "d:/javaio/abc-copy.txt");
	}

}
