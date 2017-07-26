package com.gs.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtil {
	
	public static String encrpty(String str) {
		// md5不可逆的加密算法
		try {
			MessageDigest md = MessageDigest.getInstance("md5"); // 获取md5加密算法
			byte[] bytes = md.digest(str.getBytes()); // 对字符串进行加密，返回加密后的字节数组
			return new String(Base64.getEncoder().encode(bytes)); // 对加密后的字节数组重新编码成字符串
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
