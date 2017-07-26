package com.xk.qq.ui.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.xk.bean.Account;
import com.xk.common.ReflectUtil;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;

public class HeadItemListener implements ItemListener {
	
	private JComboBox<String> accBox;

	private JLabel headLbl;
	public HeadItemListener(JLabel headLbl,JComboBox<String> accBox){
		this.headLbl = headLbl;
		this.accBox = accBox;
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		String number = accBox.getItemAt(0);
		if(number == null){
			number = (String) ReflectUtil.getFieldValue(JComboBox.class, accBox, "selectedItemReminder");
		}
		AccountDAO accountDAO = new AccountDAOImpl();
		Account a = accountDAO.queryFriend(number);
		if(a != null){
			headLbl.setIcon(new ImageIcon("images/"+a.getHeadIcon()+".png"));
		}
	}

}
