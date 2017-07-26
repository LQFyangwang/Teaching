package com.jh.ui.panel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.jh.bean.Account;
import com.jh.ui.common.CommonMethod;

public class GroupListCellRenderer implements ListCellRenderer<Account> {
	
	private JFrame frame;
	
	public GroupListCellRenderer(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Account> list, Account value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 260, 40);
		panel.setBorder(null);
		panel.setOpaque(false);
		// 头像
		JLabel headLbl = new JLabel(CommonMethod.getImg(frame, value.getHead() + ".png"));
		headLbl.setBounds(30, 20, 40, 40);
		panel.add(headLbl);
		// 群、讨论组名称
		JLabel nicknameLbl = new JLabel(value.getNickname());
		nicknameLbl.setBounds(90, 30, 100, 20);
		panel.add(nicknameLbl);
		if (cellHasFocus) { // 鼠标点击
			panel.setOpaque(true);
			panel.setBackground(new Color(0.15f, 0.15f, 0.15f, 0.2f));
		} else {
			panel.setOpaque(false);
		}
		if (isSelected) { // 鼠标经过
			panel.setOpaque(true);
			panel.setBackground(new Color(0.15f, 0.15f, 0.15f, 0.2f));
		} else {
			panel.setOpaque(false);
		}
		return panel;
	}

}
