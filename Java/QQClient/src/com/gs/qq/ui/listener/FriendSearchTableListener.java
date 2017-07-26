package com.gs.qq.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import com.gs.bean.Account;
import com.gs.bean.Message;
import com.gs.common.DateUtil;
import com.gs.dao.AccountDAO;
import com.gs.dao.AccountDAOImpl;
import com.gs.qq.client.Client;

public class FriendSearchTableListener extends MouseAdapter implements ActionListener {

	private String number;
	private Account account;
	private AccountDAO accountDAO;
	private Client client;
	
	public FriendSearchTableListener(Client client, Account account) {
		this.client = client;
		this.account = account;
		accountDAO = new AccountDAOImpl();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table = (JTable) e.getSource();
		int index = table.getSelectedRow(); // 获取鼠标点击的这一行的索引，通过该索引可以从TableModel中获取指定的account信息
		if (e.getButton() == MouseEvent.BUTTON3 && index > -1) { // MouseEvent.BUTTON3鼠标右键 BUTTON1鼠标左键 BUTTON2滚轮
			number = (String) table.getModel().getValueAt(index, 0); // 获取指定行的账号信息
			JPopupMenu menu = new JPopupMenu();
			JMenuItem viewItem = new JMenuItem("查看资料");
			viewItem.setActionCommand("view");
			viewItem.addActionListener(this);
			JMenuItem addItem = new JMenuItem("添加好友");
			addItem.setActionCommand("add");
			addItem.addActionListener(this);
			menu.add(viewItem);
			menu.add(addItem);
			table.add(menu);
			menu.show(table, e.getX(), e.getY()); // show方法把该右键菜单显示出来
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("view")) {
			Account a = accountDAO.query(number);
			if (a != null) {
				// TODO 弹出新窗口显示出用户的信息
			}
		} else if (action.equals("add")) {
			// TODO 发送消息到对方账号，请求添加好友
			Account toAccount = accountDAO.query(number); // 先通过选中行的number去数据库中查找该账户的所有信息
			Message m = new Message(Message.REQUEST_MSG, account, toAccount, DateUtil.getDate(), account.getNickname() + "请求添加您为好友");
			client.sendMessage(m);
		}
	}

}
