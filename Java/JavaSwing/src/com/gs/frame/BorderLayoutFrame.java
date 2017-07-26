package com.gs.frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BorderLayoutFrame extends Frame {
	
	public BorderLayoutFrame() {
		setTitle("边框布局");
		setSize(300, 300);
		setLocation(200, 200);
		
		setLayout(new BorderLayout()); // 设置边框布局管理器
		
		Button button1 = new Button("北");
		add(button1, BorderLayout.NORTH);
		
		Button button2 = new Button("西");
		add(button2, BorderLayout.WEST);
		
		Button button3 = new Button("东");
		add(button3, BorderLayout.EAST);
		
		Button button4 = new Button("南");
		add(button4, BorderLayout.SOUTH);
		
		Button button5 = new Button("中");
		add(button5, BorderLayout.CENTER);
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new BorderLayoutFrame();
	}

}
