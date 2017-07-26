package com.gs.qq.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.gs.bean.Account;
import com.gs.dao.AccountDAO;
import com.gs.dao.AccountDAOImpl;
import com.gs.qq.client.Client;

public class FriendListPanel extends JPanel {
	
	private static final long serialVersionUID = -7322896082253788192L;
	
	private DefaultListModel<Account> listModel;
	private int index = -1;
	
	/**
	 * 好友列表需要接收client对象，
	 * 主界面对象，
	 * 当前账户
	 * 所有好友
	 * @param client
	 * @param mainFrame
	 * @param account
	 * @param accounts
	 */
	public FriendListPanel(Client client, MainFrame mainFrame, Account account) {
		JList<Account> fList = new JList<Account>();
		fList.setFixedCellHeight(60);
		fList.setFixedCellWidth(260);
		fList.setVisibleRowCount(5);
		fList.setOpaque(false);
		listModel = new DefaultListModel<Account>();
		AccountDAO accountDAO = new AccountDAOImpl();;
		List<Account> accounts = accountDAO.queryFriends(account.getNumber());
		if (accounts != null) {
			for (Account a : accounts) {
				listModel.addElement(a);
			}
		}
		fList.setModel(listModel);
		fList.setCellRenderer(new FriendListCellRenderer()); // 每一个项通过自定义的渲染器显示出来
		fList.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {}

			@Override
			public void mouseMoved(MouseEvent e) {
				@SuppressWarnings("rawtypes")
				JList list = (JList) e.getSource(); // 获取当前鼠标的位置
				index = list.locationToIndex(e.getPoint()); // locationToIndex(Point)
				list.setSelectedIndex(index); // 把正好在鼠标位置的那个项目的索引设置为被选中，一旦设置为被选中，则CellRenderer里的isSelected则为true
			}
			
		});
		fList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) { // 如果点击了鼠标左键，并且点击了两下
					@SuppressWarnings("rawtypes")
					JList list = (JList) e.getSource();
					Account toAccount = (Account) list.getModel().getElementAt(index);// 获取点击的是哪一个账号
					if (!mainFrame.getChatFrames().containsKey(toAccount)) {
						ChatFrame chatFrame = new ChatFrame(client, account, toAccount);
						mainFrame.getChatFrames().put(toAccount, chatFrame);
					} else {
						mainFrame.getChatFrames().get(toAccount).setVisible(true);
					}
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(fList);
		scrollPane.setBounds(0, 0, 200, 180);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		setOpaque(false);
		add(scrollPane);
	}
	
	public void updateFriendList(Account account) {
		listModel.addElement(account); // 直接给liStMode添加一个Account，JList会自动知道已经添加了一个新的account，自动完成列表的更新
	}
	
	public void updateFriendStatus(Account account) {
		int index = listModel.indexOf(account);
		listModel.removeElement(account);
		listModel.insertElementAt(account, index);
	}


}
