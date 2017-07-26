package com.gs.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.junit.Test;

public class EncryptTest {
	
	@Test
	public void testEncode(){
		System.out.println(Base64.encodeToString("hello".getBytes()));
		System.out.println(new String(Base64.decode("aGVsbG8=")));
		System.out.println(Hex.encodeToString("hello".getBytes()));
		System.out.println(new String(Hex.decode("68656c6c6f")));
	}
	
	@Test
	public void testMD5() {
		System.out.println(new Md5Hash("123456").toString()); // hex
		System.out.println(new Md5Hash("123456").toBase64());
		System.out.println(new Md5Hash("123456").toHex());
		System.out.println(new Md5Hash(new Md5Hash("123456").toBase64()));
		
		System.out.println(new Md5Hash("123456", "abc").toBase64());
		System.out.println(new Md5Hash("123456", "abcd").toBase64());
		
		System.out.println("*********************************");
		System.out.println(new Sha256Hash("123456").toString());
		System.out.println(new Sha1Hash("123456").toString());
		System.out.println(new Sha512Hash("123456").toString());
	}

}
