package com.gs.qq.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StatusActionListener implements ActionListener {

	private JLabel statusLbl;
	
	public StatusActionListener(JLabel statusLbl) {
		this.statusLbl = statusLbl;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("online")) {
			statusLbl.setIcon(new ImageIcon("img/online.png"));
			statusLbl.repaint();
		} else if (action.equals("qme")) {
			statusLbl.setIcon(new ImageIcon("img/qme.png"));
			statusLbl.repaint();
		} else if (action.equals("busy")) {
			statusLbl.setIcon(new ImageIcon("img/busy.png"));
			statusLbl.repaint();
		}
	}

}
