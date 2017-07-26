package com.gs.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowListener extends WindowAdapter {
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

}
