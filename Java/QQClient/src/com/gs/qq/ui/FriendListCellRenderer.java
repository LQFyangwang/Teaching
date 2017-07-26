package com.gs.qq.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.gs.bean.Account;
import com.gs.common.Constants;

public class FriendListCellRenderer implements ListCellRenderer<Account> {

	/**
	 * isSelected:当JList中的某个项被选中时
	 * cellHasFocus：当JList中的某个项获得焦点时
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends Account> list, Account value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(260, 60));;
		panel.setLayout(null);
		JLabel headLbl = new JLabel(new ImageIcon("img/head/" + value.getHeadIcon() + ".png"));
		headLbl.setBounds(10, 0, 20, 20);
		panel.add(headLbl);
		JLabel nicknameLbl = new JLabel(value.getNickname());
		nicknameLbl.setBounds(30, 5, 30, 10);
		panel.add(nicknameLbl);
		String statusImg = "";
		if (value.getStatus().equals(Constants.STATUS_ONLINE)) {
			statusImg = "img/online.png";
		} else if (value.getStatus().equals(Constants.STATUS_BUSY)) {
			statusImg = "img/busy.png";
		}
		JLabel statusLbl = new JLabel(new ImageIcon(statusImg));
		statusLbl.setBounds(80, 5, 20, 20);
		panel.add(statusLbl);
		JLabel lineLbl = new JLabel();
		lineLbl.setBounds(10, 55, 260, 1);
		lineLbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		if (cellHasFocus) {
			panel.setOpaque(true);
			panel.setBackground(Color.BLUE);
		} else {
			panel.setOpaque(false);
		}
		if (isSelected) {
			panel.setOpaque(true);
			panel.setBackground(new Color(0.15f, 0.15f, 0.15f, 0.2f));
		} else {
			panel.setOpaque(false);
		}
		panel.add(lineLbl);
		return panel;
	}

}
