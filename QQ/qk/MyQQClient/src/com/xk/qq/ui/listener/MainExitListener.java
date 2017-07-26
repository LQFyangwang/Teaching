package com.xk.qq.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.common.Constants;
import com.xk.common.DateUtil;
import com.xk.qq.client.Client;
import com.xk.qq.ui.common.MouseEnterExitIconUtil;

public class MainExitListener extends MouseAdapter{
	
	private Account account;
	private Client client;
	
	public MainExitListener(Account account,Client client){
		this.account = account;
		this.client = client;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(account != null){
			Message m = new Message(Message.LOGOUT_MSG,DateUtil.getDate(),account,account,"ÍË³ö");
			client.sendMessage(m);
		}
		System.exit(0);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		MouseEnterExitIconUtil.change(e, "images/exit_over.png");
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		MouseEnterExitIconUtil.change(e,"images/exit_def.png");
	}
	
}
