package com.gs.qq.ui;

import javax.swing.SwingUtilities;

public class QQStarter {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new LoginFrame();
			}

		});
	}
}
