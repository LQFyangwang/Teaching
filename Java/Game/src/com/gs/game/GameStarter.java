package com.gs.game;

import javax.swing.SwingUtilities;

public class GameStarter {

	/**
	 * main方法使用了SwigUtilities.invokeLater方法，详细请自行查阅资料
	 * 
	 * @param args
	 *            在CMD命令提示符下使用java命令执行main方法时可以传递进来的参数
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameFrame();
			}
		});
	}

}
