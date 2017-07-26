package com.jh.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.Constants;
import com.jh.common.DateUtil;
import com.jh.ui.LoginFrame;
import com.jh.ui.MainFrame;
import com.jh.ui.SelectPasswordFrame;

public class FunctionActionListener implements ActionListener {

	private MainFrame mainFrame;
	private Client client;
	private Account account;
	
	public FunctionActionListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.client = mainFrame.getClient();
		this.account = mainFrame.getAccount();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("select")) {
			if (Constants.isSelectPasswordFrameOpen) {
				new SelectPasswordFrame(mainFrame);
			}
		} else {
			if (client != null) {
				Message m = new Message(Message.LOGOUT_MSG, account, account, DateUtil.getDate(), "ÍË³ö");
				client.sendMessage(m);
			}
			mainFrame.dispose();
			new LoginFrame();
		}
	}
}
