package com.jh.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.DateUtil;
import com.jh.ui.common.UIUtil;

public class ExitListener extends MouseAdapter {

	private Client client;
	private Account account;
	
	public ExitListener(Client client, Account account) {
		this.client = client;
		this.account = account;
	}
	
	// 鼠标单击时调用
	@Override
	public void mouseClicked(MouseEvent e) {
		if (client != null) {
			Message m = new Message(Message.LOGOUT_MSG, account, account, DateUtil.getDate(), "退出");
			client.sendMessage(m);
		}
		System.exit(0);
	}
	
	// 鼠标经过时调用
	@Override
	public void mouseEntered(MouseEvent e) {
		UIUtil.change(e, this.getClass().getResource("/images/exit_over.png"));
	}
	
	// 鼠标离开时调用
	public void mouseExited(MouseEvent e) {
		UIUtil.change(e, this.getClass().getResource("/images/exit_def.png"));
	}
}
