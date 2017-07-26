package com.xk.qq.ui.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.xk.bean.Account;
import com.xk.qq.client.Client;
import com.xk.qq.ui.HeadUpdateDataFrame;
import com.xk.qq.ui.MainFrame;

public class HeadUpdateDataListener extends MouseAdapter {
	
	private JLabel lbl;
	private Account account;
	private MainFrame mainFrame;
	private Client client;
	public HeadUpdateDataListener(JLabel lbl,Account account,MainFrame mainFrame,Client client){
		this.lbl = lbl; 
		this.account = account;
		this.mainFrame = mainFrame;
		this.client = client;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!mainFrame.isUpdateData()){
			new HeadUpdateDataFrame(account,mainFrame,client);
			mainFrame.setUpdateData(true);
		}
		

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		lbl.setBorder(BorderFactory.createLineBorder(new Color(30,144,255)));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		lbl.setBorder(null);
	}

}
