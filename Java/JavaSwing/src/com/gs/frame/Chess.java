package com.gs.frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Chess extends Frame {
	
	public Chess() {
		setTitle("象棋");
		setSize(400, 400);
		setLocation(200, 200);
		
		setLayout(new BorderLayout()); // 设置边框布局管理器
		
		Label titleLbl = new Label("中国国际象棋");
		titleLbl.setAlignment(Label.CENTER);
		add(titleLbl, BorderLayout.NORTH);
		
		Panel chessPanel = new Panel();
		chessPanel.setLayout(new GridLayout(8, 8));
		chessPanel.setBackground(Color.LIGHT_GRAY);
		for (int i = 0; i < 64; i++) {
			Button button = new Button();
			chessPanel.add(button);
		}
		add(chessPanel, BorderLayout.CENTER);
		
		Panel rightPanel = new Panel();
		rightPanel.setLayout(new GridLayout(3, 1));
		rightPanel.setSize(120, 350);
		Label comLbl = new Label("COM");
		comLbl.setSize(100, 30);
		Label ipLbl = new Label("IP1");
		ipLbl.setSize(100, 30);
		Button btn = new Button("悔棋");
		btn.setSize(100, 30);
		
		rightPanel.add(comLbl);
		rightPanel.add(ipLbl);
		rightPanel.add(btn);
		
		add(rightPanel, BorderLayout.EAST);
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new Chess();
	}

}
