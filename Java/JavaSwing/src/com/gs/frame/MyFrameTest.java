package com.gs.frame;

public class MyFrameTest {
	public static void main(String[] args) {
		MyFrame mf = new MyFrame();
		// getLocation()返回Point类的对象，Point是点对象，包含x, y, getX(), getY()
		System.out.println("location: " + mf.getX() + ", " + mf.getLocation().getY());
		// getSize()返回Dimension对象，表示宽和高的一个类，getWidth(), getHeight()
		System.out.println("size: " + mf.getSize().getWidth() + ", " + mf.getHeight());
		System.out.println("background: " + mf.getBackground());
	}
}
