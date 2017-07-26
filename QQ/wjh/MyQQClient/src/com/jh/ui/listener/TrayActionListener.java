package com.jh.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.DateUtil;
import com.jh.ui.common.CommonMethod;

public class TrayActionListener implements ActionListener {
	
	private JFrame frame;
	private Client client;
	private Account account;
	
	public TrayActionListener(JFrame frame) {
		this.frame = frame;
		client = CommonMethod.client;
		account = CommonMethod.account;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		if (name.equals("boundary")) {
			frame.setVisible(true);
		} else if (name.equals("exit")) {
			if (client != null) {
				Message m = new Message(Message.LOGOUT_MSG, account, account, DateUtil.getDate(), "ÍË³ö");
				client.sendMessage(m);
			}
			System.exit(0);
		}
	}
}
