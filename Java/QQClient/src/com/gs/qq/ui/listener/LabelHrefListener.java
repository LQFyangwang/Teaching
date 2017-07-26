package com.gs.qq.ui.listener;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import com.gs.qq.ui.RegisterFrame;
import com.gs.qq.ui.common.WindowJustifier;

public class LabelHrefListener extends MouseAdapter {
	
	// private RegisterFrame regFrame;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel lbl = (JLabel) e.getSource();
		String name = lbl.getName();
		if (name.equals("reg")) {
			if (!WindowJustifier.isRegisterFrameOpened) {
//				if (regFrame == null) {
//				 regFrame = new RegisterFrame();
//				} else {
//					regFrame.setVisible(true);
//				}
				new RegisterFrame();
				WindowJustifier.isRegisterFrameOpened = true;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel lbl = (JLabel) e.getSource();
		lbl.setForeground(Color.RED);
		lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 当鼠标进入到Lable时，把鼠标变成手形
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel lbl = (JLabel) e.getSource();
		lbl.setForeground(Color.BLUE);
		lbl.setCursor(Cursor.getDefaultCursor()); // 当鼠标移开时，变成默认的鼠标
	}

}
