package com.xk.qq.ui.common;

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MouseEnterExitIconUtil {
	public static void change(MouseEvent e, String path){
		JLabel lbl = (JLabel)e.getSource();
		lbl.setIcon(new ImageIcon(path));
		lbl.repaint();
	}
}
