package com.ht.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	
	public static String MD5(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return new String(md.digest(source.getBytes("utf-8")), "utf-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
