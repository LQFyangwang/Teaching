package com.gs.frame;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;

public class MyFrame extends Frame {
	
	public MyFrame() {
		setTitle("我的窗口"); // 设置标题
		setSize(300, 200); // 设置窗口大小， 默认0， 0
		setLocation(200, 200);
		setBackground(Color.BLUE); // 设置背景色
		
		Panel panel = new Panel();
		panel.setSize(100, 100);
		panel.setLocation(20, 80);
		panel.setBackground(Color.GREEN);
		
		Panel panel1 = new Panel();
		panel1.setSize(50, 50);
		panel1.setLocation(20, 20);
		panel1.setBackground(Color.RED);
		panel.setLayout(null); // 给panel设置布局管理器
		panel.add(panel1); // 把panel1面板对象添加到panel面板上
		
		this.setLayout(null); // 给当前窗体对象设置布局管理器为null
		add(panel); // 把panel面板对象添加到当前frame窗口
		setVisible(true); // 设置该窗口可见， 默认不可见
		
		// 给当前窗体添加窗体监听器
		addWindowListener(new WindowAdapter() {
			
			// 当窗体正在被关闭时，接收到此正在关闭事件，则在此时可以把整个程序退出
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("窗口正在关闭...");
				System.exit(0);
				// dispose(); // 释放当前窗口所占用的屏幕资源
			}
			
		});
	}

}
