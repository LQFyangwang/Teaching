package com.gs.four;

public interface Activity {
	
	public static final int a = 100; // 接口里的常量只能是public, 默认的，static, final来修饰
	
	public abstract void sing(); // 可以加interface和方法前加abstract，但是没必要
	
	public void dance();
	

}
