package com.gs.game;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class FlightGameFrame extends JFrame {

	private static final long serialVersionUID = 4826393934116339160L;

	public FlightGameFrame() {
		setTitle("Game");
		setSize(400, 600);
		setLocationRelativeTo(null);
		setContentPane(new FlightGamePanel());
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
