package com.jh.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.Constants;
import com.jh.common.DateUtil;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.ui.MainFrame;
import com.jh.ui.common.CommonMethod;

public class StatusActionListener implements ActionListener {

	private JFrame frame;
	private JLabel statusLbl;
	private Account account;
	private Client client;
	
	public StatusActionListener(JFrame frame, JLabel statusTxt, Account account, Client client) {
		this.frame = frame;
		this.statusLbl = statusTxt;
		this.account = account;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String itemName = e.getActionCommand();
		if (itemName.equals("online")) {
			CommonMethod.IMG_URL = "online";
			setLoginFrameState();
			setMainFrameState();
			setStatus(CommonMethod.IMG_URL);
			CommonMethod.setTray(frame);
		} else if (itemName.equals("qme")) {
			CommonMethod.IMG_URL = "qme";
			setLoginFrameState();
			setMainFrameState();
			setStatus(CommonMethod.IMG_URL);
			CommonMethod.setTray(frame);
		} else if (itemName.equals("leave")) {
			CommonMethod.IMG_URL = "leave";
			setLoginFrameState();
			setMainFrameState();
			setStatus(CommonMethod.IMG_URL);
			CommonMethod.setTray(frame);
		} else if (itemName.equals("busy")) {
			CommonMethod.IMG_URL = "busy";
			setLoginFrameState();
			setMainFrameState();
			setStatus(CommonMethod.IMG_URL);
			CommonMethod.setTray(frame);
		} else if (itemName.equals("dont")) {
			CommonMethod.IMG_URL = "dont";
			setLoginFrameState();
			setMainFrameState();
			setStatus(CommonMethod.IMG_URL);
			CommonMethod.setTray(frame);
		} else if (itemName.equals("hidden")) {
			CommonMethod.IMG_URL = "hidden";
			setLoginFrameState();
			setMainFrameState();
			setStatus(CommonMethod.IMG_URL);
			CommonMethod.setTray(frame);
		}
	}
	
	private void setMainFrameState() {
		if (Constants.mainOk) {
			MainFrame.stateLbl.setIcon(CommonMethod.getImg(frame, CommonMethod.IMG_URL + ".png"));
			MainFrame.stateLbl.repaint();
		}
		
	}
	
	private void setLoginFrameState() {
		statusLbl.setIcon(CommonMethod.getImg(frame, CommonMethod.IMG_URL + ".png"));
		statusLbl.repaint();
	}
	
	private void setStatus(String statu) {
		if (client != null) {
			AccountDAO accountDAO = new AccountDAOImpl();
			account.setStatus(statu);
			accountDAO.update(account);
			Message m = new Message(Message.UPDATE_FRIEND_MSG, account, account, DateUtil.getDate(), "״̬�޸�Ϊ" + account.getStatus());
			client.sendMessage(m);
		}
	}
}
