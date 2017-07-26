package com.gs.qq.ui.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.gs.qq.ui.common.WindowJustifier;

public class QQWindowListener extends WindowAdapter {
	
	@Override
	public void windowClosing(WindowEvent e) {
		WindowJustifier.isRegisterFrameOpened = false;
	}
	

}
