package com.gs.qq.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.gs.bean.Account;
import com.gs.qq.client.Client;

public class MainStatusListener extends MouseAdapter {

	private Client client;
	private Account account;
	private JLabel lbl;
	private JLabel statusLbl;
	public MainStatusListener(Client client, Account account, JLabel lbl, JLabel statusLbl) {
		this.lbl = lbl;
		this.statusLbl = statusLbl;
		this.client = client;
		this.account = account;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JPopupMenu menu = new JPopupMenu();
		MainStatusActionListener sal = new MainStatusActionListener(client, account, statusLbl);
		JMenuItem item1 = new JMenuItem(new ImageIcon("img/online.png"));
		item1.setActionCommand("online");
		item1.addActionListener(sal);
		menu.add(item1);
		JMenuItem item2 = new JMenuItem(new ImageIcon("img/qme.png"));
		item2.setActionCommand("qme");
		item2.addActionListener(sal);
		menu.add(item2);
		JMenuItem item3 = new JMenuItem(new ImageIcon("img/leave.png"));
		item3.setActionCommand("leave");
		item3.addActionListener(sal);
		menu.add(item3);
		JMenuItem item4 = new JMenuItem(new ImageIcon("img/busy.png"));
		item4.setActionCommand("busy");
		item4.addActionListener(sal);
		menu.add(item4);
		lbl.add(menu);
		menu.show(lbl, 20, 20);
	}

}
