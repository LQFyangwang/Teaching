package com.gs;

public class StringBufferTest {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("abc");
		StringBuffer sb1 = sb.append("abc");
		System.out.println(sb1);

	}
}
