package com.gs.frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CardLayoutFrame extends Frame {

	public CardLayoutFrame() {
		setTitle("卡片布局");
		setSize(300, 300);
		setLocation(200, 200);
		
		setLayout(new CardLayout()); // 设置卡片布局
		Panel p3 = new Panel();
		p3.setSize(100, 100);
		p3.setBackground(Color.GREEN);
		add(p3);
		Panel p2 = new Panel();
		p2.setBackground(Color.YELLOW);
		add(p2);
		Panel p1 = new Panel();
		p1.setBackground(Color.RED);
		add(p1);
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	public static void main(String[] args) {
		new CardLayoutFrame();
	}
}
