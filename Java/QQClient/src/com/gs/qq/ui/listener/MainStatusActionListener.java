package com.gs.qq.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.gs.bean.Account;
import com.gs.bean.Message;
import com.gs.common.Constants;
import com.gs.common.DateUtil;
import com.gs.qq.client.Client;

public class MainStatusActionListener implements ActionListener {

	private Client client;
	private Account account;
	private JLabel statusLbl;
	
	public MainStatusActionListener(Client client, Account account, JLabel statusLbl) {
		this.statusLbl = statusLbl;
		this.client = client;
		this.account = account;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("online")) {
			statusLbl.setIcon(new ImageIcon("img/online.png"));
			account.setStatus(Constants.STATUS_ONLINE);
			statusLbl.repaint();
		} else if (action.equals("qme")) {
			statusLbl.setIcon(new ImageIcon("img/qme.png"));
			statusLbl.repaint();
		} else if (action.equals("busy")) {
			statusLbl.setIcon(new ImageIcon("img/busy.png"));
			account.setStatus(Constants.STATUS_BUSY);
			Message m = new Message(Message.STATUS_CHANGE_MSG, account, account, DateUtil.getDate(), "״̬�޸�Ϊ" + account.getStatus());
			client.sendMessage(m);
			statusLbl.repaint();
		}
	}

}
