package com.xk.qq.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.xk.bean.Account;
import com.xk.common.Constants;

public class BlackListCellRederer implements ListCellRenderer<Account> {

	
	/**
	 * isSelected:当JList中的某个项被选中时
	 * cellHasFocus：当JList中的某个项获得焦点时
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends Account> list, Account value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 260, 70);
		panel.setBorder(null);
		panel.setLayout(null);
		
		
		
		JLabel headLbl = new JLabel(new ImageIcon("images/head_black.png"));
		headLbl.setBounds(5, 5, 70, 70);
		panel.add(headLbl);
		
		
//		JLabel nickName = new JLabel(value.getNickname());
//		nickName.setBounds(90,0,80,30);
//		panel.add(nickName);
		
		
//		if(!value.getAutograph().equals("编辑个性签名")){
//			JLabel autograph = new JLabel(value.getAutograph());
//			autograph.setBounds(90,40,120,30);
//			panel.add(autograph);
//		}
		
		panel.setOpaque(false);
		
		if(isSelected){
			panel.setOpaque(true);
			panel.setBackground(new Color(0,0,0,0.2f));
		}else {
			panel.setOpaque(false);
		}
		
		if(cellHasFocus){
			panel.setOpaque(true);
			panel.setBackground(new Color(0,0,0,0.2f));
		}else {
			panel.setOpaque(false);
		}
		return panel;
	}
	
}
