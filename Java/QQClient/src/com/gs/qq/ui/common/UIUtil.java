package com.gs.qq.ui.common;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UIUtil {
	
	public static Font myFont(int size) {
		return new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, size);
	}
	
	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[3].getClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
