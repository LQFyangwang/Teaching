package com.xk.qq.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.common.Constants;
import com.xk.common.DateUtil;
import com.xk.qq.client.Client;

public class MainStatusActionListner implements ActionListener{
	private JLabel statusLbl;
	private Account account;
	private Client client;

	public MainStatusActionListner(Account account, Client client,JLabel statusLbl){
		this.account = account;
		this.client = client;
		this.statusLbl = statusLbl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("online")){
			statusLbl.setIcon(new ImageIcon("images/online.png"));
			account.setState(Constants.STATUS_ONLINE);
			Message m = new Message(Message.STATES_CHANGE,DateUtil.getDate(),account,account,"状态改变为"+account.getState());
			client.sendMessage(m);
			statusLbl.repaint();
		}else if(action.equals("qme")){
			statusLbl.setIcon(new ImageIcon("images/qme.png"));
			account.setState(Constants.STATUSS_QME);
			Message m = new Message(Message.STATES_CHANGE,DateUtil.getDate(),account,account,"状态改变为"+account.getState());
			client.sendMessage(m);
			statusLbl.repaint();
		}else if(action.equals("leave")){
			statusLbl.setIcon(new ImageIcon("images/leave.png"));
			account.setState(Constants.STATUS_LEAVE);
			Message m = new Message(Message.STATES_CHANGE,DateUtil.getDate(),account,account,"状态改变为"+account.getState());
			client.sendMessage(m);
			statusLbl.repaint();
		}else if(action.equals("busy")){
			statusLbl.setIcon(new ImageIcon("images/busy.png"));
			account.setState(Constants.STATUS_BUSY);
			Message m = new Message(Message.STATES_CHANGE,DateUtil.getDate(),account,account,"状态改变为"+account.getState());
			client.sendMessage(m);
			statusLbl.repaint();
		}else if(action.equals("dont")){
			statusLbl.setIcon(new ImageIcon("images/dont.png"));
			account.setState(Constants.STATUS_DONT);
			Message m = new Message(Message.STATES_CHANGE,DateUtil.getDate(),account,account,"状态改变为"+account.getState());
			client.sendMessage(m);
			statusLbl.repaint();
		}else if(action.equals("hidden")){
			statusLbl.setIcon(new ImageIcon("images/hidden.png"));
			account.setState(Constants.STATUS_HIDDEN);
			Message m = new Message(Message.STATES_CHANGE,DateUtil.getDate(),account,account,"状态改变为"+account.getState());
			client.sendMessage(m);
			statusLbl.repaint();
		}
		
	}
}
