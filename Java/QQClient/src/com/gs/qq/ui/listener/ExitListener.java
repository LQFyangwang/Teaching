package com.gs.qq.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.gs.bean.Account;
import com.gs.bean.Message;
import com.gs.common.DateUtil;
import com.gs.qq.client.Client;
import com.gs.qq.ui.common.MouseEnterExitIconUtil;

public class ExitListener extends MouseAdapter {

	private Client client;
	private Account account;
	
	public ExitListener(Client client, Account account) {
		this.client = client;
		this.account = account;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (client != null) {
			Message m = new Message(Message.LOGOUT_MSG, account, account, DateUtil.getDate(), "ÍË³ö");
			client.sendMessage(m);
		}
		System.exit(0);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		MouseEnterExitIconUtil.change(e, "img/exit_over.png");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		MouseEnterExitIconUtil.change(e, "img/exit_def.png");
	}

}
