package com.jh.ui.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UIUtil {

	public static Font myFont(int size) {
		return new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, size);
	}
	
	public static Color myColor(Color color) {
		return color;
	}
	
	public static void change(MouseEvent e, URL url) {
		JLabel label = (JLabel) e.getSource();
		label.setIcon(new ImageIcon(url));
		label.repaint();
	}
}
