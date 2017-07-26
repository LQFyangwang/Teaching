package com.xk.qq.ui.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.common.DateUtil;
import com.xk.qq.client.Client;

public class MainWindowListener extends WindowAdapter{
	private Account account;
	private Client client;
	public MainWindowListener(Account account ,Client client){
		this.account = account;
		this.client = client;
	}
	@Override
	public void windowClosing(WindowEvent e) {
		Message m = new Message(Message.LOGOUT_MSG,DateUtil.getDate(),account,account,"ÍË³ö");
		client.sendMessage(m);
		System.exit(0);
	}
	
}
