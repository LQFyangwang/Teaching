package com.jh.ui.listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.DateUtil;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.ui.MainFrame;

public class TxtFocusListener implements FocusListener {

	private JTextField txt;
	private Account account;
	private Client client;
	private String name;
	private String value;
	private String lastValue;
	private MainFrame mainFrame;
	
	public TxtFocusListener(MainFrame mainFrame, JTextField txt, Account account) {
		this.txt = txt;
		this.account = account;
		this.mainFrame = mainFrame;
		this.client = mainFrame.getClient();
	}

	// 获得焦点
	@Override
	public void focusGained(FocusEvent e) {
		name = ((JTextField) e.getSource()).getName();
		lastValue = txt.getText();
		if (lastValue.equals(" 编辑个性签名") || lastValue.equals("  搜索：联系人、讨论组、群、企业") || lastValue.equals("输入QQ号码/昵称查找好友")) {
			txt.setText("");
		}
	}

	// 失去焦点
	@Override
	public void focusLost(FocusEvent e) {
		name = ((JTextField) e.getSource()).getName();
		value = txt.getText();
		if (name.equals("seek")) { // 查找输入框
			setTxt("输入QQ号码/昵称查找好友");
		} else if (name.equals("autograph")) { // 个性签名
			if (value.equals("") || value == null) {
				txt.setText(" 编辑个性签名");
			} else {
				AccountDAO accountDAO = new AccountDAOImpl();
				account.setAutograph(txt.getText());
				accountDAO.update(account);
				mainFrame.updaDataAutograph(account);
				Message m = new Message(Message.UPDATE_DATA_MSG, account, account, DateUtil.getDate(), account.getNumber() + "已经更新了资料");
				client.sendMessage(m);
			}
		} else if (name.equals("seek1")) { // 搜索
			setTxt("  搜索：联系人、讨论组、群、企业");		
		}
	}
	
	private void setTxt(String str) {
		if (value.equals("") || value == null) {
			txt.setText(str);
		}
	}
}
