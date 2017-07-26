package com.gs.qq.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class StatusListener extends MouseAdapter {

	private JLabel lbl;
	private JLabel statusLbl;
	public StatusListener(JLabel lbl, JLabel statusLbl) {
		this.lbl = lbl;
		this.statusLbl = statusLbl;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JPopupMenu menu = new JPopupMenu();
		StatusActionListener sal = new StatusActionListener(statusLbl);
		JMenuItem item1 = new JMenuItem(new ImageIcon("img/01.png"));
		item1.setActionCommand("online");
		item1.addActionListener(sal);
		menu.add(item1);
		JMenuItem item2 = new JMenuItem(new ImageIcon("img/02.png"));
		item2.setActionCommand("qme");
		item2.addActionListener(sal);
		menu.add(item2);
		JMenuItem item3 = new JMenuItem(new ImageIcon("img/03.png"));
		item3.setActionCommand("leave");
		item3.addActionListener(sal);
		menu.add(item3);
		JMenuItem item4 = new JMenuItem(new ImageIcon("img/04.png"));
		item4.setActionCommand("busy");
		item4.addActionListener(sal);
		menu.add(item4);
		lbl.add(menu);
		menu.show(lbl, 80, 80);
	}

}
