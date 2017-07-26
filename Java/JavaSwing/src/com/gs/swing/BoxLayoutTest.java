package com.gs.swing;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BoxLayoutTest extends JPanel {
	
	public BoxLayoutTest() {
		setSize(400, 400);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.RED);
		
		JButton btn1 = new JButton("°´Å¥1");
		add(btn1);
		JButton btn2 = new JButton("°´Å¥2");
		add(btn2);
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.add(new BoxLayoutTest());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
