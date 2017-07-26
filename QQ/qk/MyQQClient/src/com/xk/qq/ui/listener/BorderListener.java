package com.xk.qq.ui.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class BorderListener extends MouseAdapter{
	private JLabel lbl;
	
	public BorderListener(JLabel lbl) {
		this.lbl = lbl;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		lbl.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0.3f)));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		lbl.setBorder(null);
	}
	
	
}
