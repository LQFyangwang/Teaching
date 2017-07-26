package com.gs.ui;

import javax.swing.SwingUtilities;

public class ContactStarter {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ContactFrame();
			}
			
		});
	}
}
