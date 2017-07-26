package com.xk.qq.ui.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import com.xk.bean.Account;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;

public class AutographKeyListener implements KeyListener{

	private Account accounts;
	private JTextField autographTxt;
	public AutographKeyListener(JTextField autographTxt, Account accounts) {
		
		this.autographTxt = autographTxt;
		this.accounts = accounts;
	}
	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if(autographTxt.getText() != null && !autographTxt.getText().equals("")){
			if (key == KeyEvent.VK_ENTER) {
				AccountDAO account = new AccountDAOImpl();
				String strAgh = autographTxt.getText();
				accounts.setAutograph(strAgh);
				accounts.setNickname(accounts.getNickname());
				account.update(accounts);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {		}
	@Override
	public void keyTyped(KeyEvent e) {		}

}


