package com.xk.qq.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.common.DateUtil;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;
import com.xk.qq.client.Client;
import com.xk.qq.ui.BlackListPanel;
import com.xk.qq.ui.LookFrame;
import com.xk.qq.ui.MainFrame;

public class AddListener extends  MouseAdapter implements ActionListener{
	
	private AccountDAO accountDAO;
	private Client client;
	private Account toAccount;
	private BlackListPanel blackList;
	private Account account;
	private MainFrame mainFrame;
	public  AddListener(BlackListPanel balckList,Client client,Account account,MainFrame mainFrame){
		this.blackList = balckList;
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
		toAccount = (Account) list.getModel().getElementAt(index);
		if(e.getButton() == MouseEvent.BUTTON3){
			JPopupMenu menu = new JPopupMenu();
			JMenuItem addItem = new JMenuItem("添加好友");
			addItem.setActionCommand("add");
			addItem.addActionListener(this);
			JMenuItem lookItem = new JMenuItem("查看资料");
			lookItem.setActionCommand("look");
			lookItem.addActionListener(this);
			menu.add(addItem);
			menu.add(lookItem);
			menu.show(list, e.getX(), e.getY());
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("add")){
			Account tooAccount = accountDAO.queryFriend(toAccount.getNumber());
			if(!accountDAO.queryFriend(account.getNumber(), tooAccount.getNumber())){
				Message message = new Message(Message.REQUEST_MSG,DateUtil.getDate(),account,toAccount,"请求加你为好友");
				client.sendMessage(message);
			}
		}else if(action.equals("look")){
			Account a = accountDAO.queryFriend(toAccount.getNumber());
			if(a != null ){
				if(!mainFrame.getLookFrames().containsKey(a)){
					LookFrame lookFrame = new LookFrame(a,null);
					lookFrame.setVisible(true);
					lookFrame.updateLook(a);
					mainFrame.getLookFrames().put(a,lookFrame);
				}else {
					mainFrame.getLookFrames().get(a).setVisible(true);
				}
				
				// TODO 查看好友资料的窗体
			}
		}
		
	}

	
}
