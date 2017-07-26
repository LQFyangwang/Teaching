package com.gs.exercise;

public class Method {
	
	public byte add(byte a, byte b) {
		System.out.println("add(byte, byte)");
		return (byte) (a + b);
	}
	
	public int add(int a, int b) {
		System.out.println("add(int, int)");
		return a + b;
	}
	
	public long add(long a, long b) {
		System.out.println("add(long, long)");
		return a + b;
	}
	
	public static void main(String[] args) {
		Method m = new Method();
		System.out.println(m.add(200, 200));
		System.out.println(m.add(200L, 200L));
		System.out.println(m.add((byte) 200, (byte) 200));
	}

}
