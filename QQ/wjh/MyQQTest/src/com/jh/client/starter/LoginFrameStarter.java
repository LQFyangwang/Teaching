package com.jh.client.starter;

import javax.swing.SwingUtilities;

import com.jh.ui.LoginFrame;

public class LoginFrameStarter {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LoginFrame();
			}
		});
	}
}
