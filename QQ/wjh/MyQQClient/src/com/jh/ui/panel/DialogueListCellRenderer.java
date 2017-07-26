package com.jh.ui.panel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.jh.bean.Message;
import com.jh.ui.MainFrame;
import com.jh.ui.common.CommonMethod;

public class DialogueListCellRenderer implements ListCellRenderer<Message> {
	
	private MainFrame mainFrame;

	public DialogueListCellRenderer(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Message> list, Message value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 260, 60);
		panel.setBorder(null);
		panel.setOpaque(false);
		JLabel headLbl = new JLabel(CommonMethod.getImg(mainFrame, value.getToAccount().getHead() + ".png"));
		headLbl.setBounds(20, 10, 60, 60);
		panel.add(headLbl);
		JLabel nicknameLbl = new JLabel(value.getToAccount().getNickname());
		nicknameLbl.setBounds(90, 20, 100, 20);
		panel.add(nicknameLbl);
		JLabel autographLbl = new JLabel(value.getMessage());
		autographLbl.setBounds(90, 50, 200, 20);
		panel.add(autographLbl);
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
