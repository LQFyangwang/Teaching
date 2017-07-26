package com.jh.thread;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jh.common.Constants;
import com.jh.ui.common.CommonMethod;

/**
 * 切换背景图片的线程
 * @author Administrator
 *
 */
public class BackgroundChangeThread implements Runnable {
	
	private JFrame frame;
	private JLabel label;
	
	public BackgroundChangeThread(JLabel label, JFrame frame) {
		this.frame = frame;
		this.label = label;
	}
	
	@Override
	public void run() {
		int count = 2;
		while (true) {
			try {
				Thread.sleep(Constants.BACKGROUND_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			label.setIcon(CommonMethod.getImg(frame, "background" + count + ".png"));
			count++;
			if (count > 3) { // 让图片在规定的三张图片中来回切换
				count = 1;
			}
		}
	}
}
