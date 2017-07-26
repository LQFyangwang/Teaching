package com.gs.swingadvance;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class LookAndFeelTest extends JFrame {

	private static final long serialVersionUID = -5793033349903385667L;

	public LookAndFeelTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setSize(400, 400);
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		UIManager.setLookAndFeel(lookAndFeel);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		new LookAndFeelTest();
	}

}
