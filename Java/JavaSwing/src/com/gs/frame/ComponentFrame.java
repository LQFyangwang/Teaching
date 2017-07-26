package com.gs.frame;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ComponentFrame extends Frame {
	
	public ComponentFrame() {
		setTitle("我的按钮窗口");
		setSize(500, 500);
		setLocation(300, 200);
		
		Button b1 = new Button("按钮1"); // 实例化一个按钮
		// b1.setLabel("按钮1"); // 设置按钮显示的文本
		b1.setSize(100, 100); // 设置按钮大小 
		b1.setLocation(20, 50); // 设置按钮位置
		
		add(b1);
		
		Label label = new Label(); // 实例化一个标签组件
		label.setText("标签");
		label.setSize(100, 100);
		label.setLocation(120, 50);
		
		add(label);
		
		TextField text = new TextField();
		text.setText("文本区域显示的文本");
		text.setSize(200, 50);
		text.setLocation(220, 50);
		add(text);
		
		
		setLayout(null);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

}
