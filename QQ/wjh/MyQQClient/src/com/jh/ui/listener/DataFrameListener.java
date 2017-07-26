package com.jh.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.Constants;
import com.jh.common.DateUtil;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.ui.AlterHeadFrame;
import com.jh.ui.MainFrame;
import com.jh.ui.SelectDataFrame;

public class DataFrameListener implements ActionListener {
	
	private SelectDataFrame dataFrame;
	private MainFrame mainFrame; // 主界面
	private AccountDAO accountDAO; // AccountDAO用来更新数据库操作
	private Account a; // 更新后的Account
	private Client client;
	
	public DataFrameListener(SelectDataFrame dataFrame, MainFrame mainFrame) {
		this.dataFrame = dataFrame;
		this.mainFrame = mainFrame;
		this.client = mainFrame.getClient();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		accountDAO = new AccountDAOImpl();
		a = dataFrame.saveAccount();
		if (action.equals("head")) { // 头像
			if (Constants.isAlterHeadFrameOpen) {
				new AlterHeadFrame();
			}
		} else if (action.equals("save")) { // 保存
			updateUserData();
		} else if (action.equals("exit")) { // 关闭
			if (Constants.saveData) {
				if (!Constants.saveWin) {
					int result = JOptionPane.showConfirmDialog(dataFrame, "是否保存用户资料?", "保存",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.OK_OPTION) { // 如果用户点的是确定，则更新数据库
						updateUserData();
					}
				} else {
					dataFrame.dispose();
					Constants.select = true;
				}
			} else {
				dataFrame.dispose();
				Constants.select = true;
			}
		}
	}
	
	private void updateUserData() {
		accountDAO.update(a); // 更新数据库
		Constants.saveWin = true;
		Account account = accountDAO.query(a.getNumber()); // 查询数据库
		mainFrame.updateUserData(account); // 更新主界面数据
		Message m = new Message(Message.UPDATE_DATA_MSG, account, account, DateUtil.getDate(), account.getNumber() + "已经修改资料");
		client.sendMessage(m);
	}

}
