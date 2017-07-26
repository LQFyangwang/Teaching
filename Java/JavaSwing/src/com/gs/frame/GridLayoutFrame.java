package com.gs.frame;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GridLayoutFrame extends Frame {
	
	public GridLayoutFrame() {
		setTitle("网格布局");
		setSize(300, 300);
		setLocation(200, 200);
		
		setLayout(new GridLayout(3, 3)); // 设置网格布局管理器
		
		for (int i = 0; i < 9; i++) {
			Button btn = new Button("按钮" + (i + 1));
			add(btn);
		}
		
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new GridLayoutFrame();
	}

}
