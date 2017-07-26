package com.jh.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;

import com.jh.bean.Account;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.ui.MainFrame;
import com.jh.ui.common.CommonMethod;

public class SkinFrameListener extends MouseAdapter {
	
	private String name;
	private JLabel label;
	private MainFrame mainFrame;
	private Account account;
	
	public SkinFrameListener(MainFrame mainFrame, JLabel label) {
		this.label = label;
		this.mainFrame = mainFrame;
		this.account = mainFrame.getAccount();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		name = CommonMethod.getLabelName(e);
		if (name.equals("background")) {
			setBackground("main_background");
		} else if (name.equals("background1")) {
			setBackground("main_background1");
		} else if (name.equals("background2")) {
			setBackground("main_background2");
		} else if (name.equals("background3")) {
			setBackground("main_background3");
		} else if (name.equals("background4")) {
			setBackground("main_background4");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		CommonMethod.setLblCursor(label);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		CommonMethod.setLblCursorDef(label);
	}
	
	private void setBackground(String url) {
		Icon bgIcon = CommonMethod.getImg(mainFrame, url + ".png");
		MainFrame.bgLbl.setIcon(bgIcon);
		AccountDAO accountDAO = new AccountDAOImpl();
		account.setSkin(url);
		accountDAO.update(account);
		mainFrame.repaint();
	}

}
