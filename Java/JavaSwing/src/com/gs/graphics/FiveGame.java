package com.gs.graphics;

import java.awt.Frame;

import com.gs.listener.MyWindowListener;

public class FiveGame extends Frame {
	
	public FiveGame() {
		setSize(440, 460);
		setLocationRelativeTo(null);
		add(new FiveGamePanel());
		setResizable(false);
		setVisible(true);
		addWindowListener(new MyWindowListener());
	}
	
	public static void main(String[] args) {
		new FiveGame();
	}

}
