package com.gs.two;

public class Mouse {
	
	/**
	 * 使用final修饰的方法不能被子类重写
	 */
	public final void click() {
		System.out.println("mouse click...");
	}

}
