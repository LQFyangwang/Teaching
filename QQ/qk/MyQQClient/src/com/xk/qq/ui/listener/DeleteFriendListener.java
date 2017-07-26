package com.xk.qq.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.common.DateUtil;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;
import com.xk.qq.client.Client;
import com.xk.qq.ui.FinedListPanel;
import com.xk.qq.ui.MainFrame;

public class DeleteFriendListener extends  MouseAdapter implements ActionListener{
	
	private AccountDAO accountDAO;
	private Client client;
	private Account toAccount;
	private FinedListPanel finedList;
	private Account account;
	private MainFrame mainFrame;
	public  DeleteFriendListener(FinedListPanel finedList,Client client,Account account,MainFrame mainFrame){
		this.finedList = finedList;
		this.mainFrame = mainFrame;
		this.client = client;
		this.account = account;
		accountDAO = new AccountDAOImpl();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int index = -1;
		@SuppressWarnings("rawtypes")
		JList list = (JList)e.getSource();
		index = list.locationToIndex(e.getPoint());
		if(index >= 0){
			toAccount = (Account) list.getModel().getElementAt(index);
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			JPopupMenu menu = new JPopupMenu();
			JMenuItem dataItem = new JMenuItem("删除好友");
			dataItem.setActionCommand("delete");
			dataItem.addActionListener(this);
			JMenuItem fiendItem = new JMenuItem("添加黑名单");
			fiendItem.setActionCommand("blackList");
			fiendItem.addActionListener(this);
			menu.add(dataItem);
			menu.add(fiendItem);
			menu.show(list, e.getX(), e.getY());
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("delete")){
			Message m = new Message(Message.DELETE_MSG,DateUtil.getDate(),account,toAccount,"dalete");
			client.sendMessage(m);
			accountDAO.deleteFriend(account.getNumber(), toAccount.getNumber());
			finedList.delteFriedndList(toAccount);
		}else if(action.equals("blackList")){
				Message m = new Message(Message.BLACKLIST_MSG,DateUtil.getDate(),account,toAccount,"添加 黑名单");
				client.sendMessage(m);
				accountDAO.deleteFriend(account.getNumber(), toAccount.getNumber());
				finedList.delteFriedndList(toAccount);
				
				accountDAO.addBlack(account.getNumber(), toAccount.getNumber());
				mainFrame.getBlackListPanel().updateFriendList(toAccount);
		}
		
	}

	
}
