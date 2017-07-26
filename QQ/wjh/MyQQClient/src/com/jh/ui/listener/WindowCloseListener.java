package com.jh.ui.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.DateUtil;

public class WindowCloseListener extends WindowAdapter {

	private Client client;
	private Account account;
	
	public WindowCloseListener(Client client, Account account) {
		this.client = client;
		this.account = account;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Message m = new Message(Message.LOGOUT_MSG, account, account, DateUtil.getDate(), "ÍË³ö");
		client.sendMessage(m);
	}
}
