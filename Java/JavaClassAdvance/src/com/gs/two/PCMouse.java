package com.gs.two;

/**
 * 被final修饰的类不能被继承
 * 
 * String是final的
 *
 */
public final class PCMouse extends Mouse {
	
	public static final int COUNT = 100;
	
	public static void main(String[] args) {
		PCMouse m = new PCMouse();
		System.out.println("鼠标工作区域的最大范围宽度：" + Constants.MAX_WIDTH);
		m.click();
	}

}
