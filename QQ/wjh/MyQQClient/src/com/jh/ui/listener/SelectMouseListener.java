package com.jh.ui.listener;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.jh.ui.SelectPasswordFrame;
import com.jh.ui.common.CheckCode;
import com.jh.ui.common.CheckCodeGenerator;

public class SelectMouseListener extends MouseAdapter {

	private SelectPasswordFrame selectPasswordFrame;
	private JLabel label;
	
	public SelectMouseListener(SelectPasswordFrame selectPasswordFrame, JLabel label) {
		this.selectPasswordFrame = selectPasswordFrame;
		this.label = label;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		CheckCode cc = CheckCodeGenerator.getCheckCode();
		selectPasswordFrame.verLbl.setIcon(new ImageIcon(cc.getBuffImg()));
		selectPasswordFrame.checkCode = cc.getCheckCode(); // 及时更新验证码
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		label.setCursor(Cursor.getDefaultCursor());
	}

	
}
