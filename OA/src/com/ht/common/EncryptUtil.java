package com.ht.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 密码MD5加密的一个工具类
 * @author Administrator
 *
 */
public class EncryptUtil {

	public static String encrypt(String str) {
		// md5不可逆加密算法
		try {
			MessageDigest md = MessageDigest.getInstance("md5"); // 获取md5加密算法
			byte[] bytes = md.digest(str.getBytes()); // 对字符串进行加密，返回一个加密后的字节数组
			return new String(Base64.getEncoder().encode(bytes)); // 把加密后的字节数组重新编码成字符串
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return null;
	}
}
