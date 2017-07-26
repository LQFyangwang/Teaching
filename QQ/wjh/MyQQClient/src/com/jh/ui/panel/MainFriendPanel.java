package com.jh.ui.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jh.bean.Account;
import com.jh.client.Client;
import com.jh.ui.ChatFrame;
import com.jh.ui.MainFrame;

public class MainFriendPanel extends JPanel {

	private static final long serialVersionUID = -4523251512810571833L;
	
	private DefaultListModel<Account> listModel;
	private int index = -1;
	
	public JList<Account> fList;

	public MainFriendPanel(MainFrame mainFrame, Client client, Account account, List<Account> accounts) {
		setBounds(0, 0, 260, 360);
		setOpaque(false);
		setBorder(null);
		fList = new JList<Account>();
		fList.setFixedCellHeight(80);
		fList.setFixedCellWidth(280);
		fList.setVisibleRowCount(4);
		fList.setBorder(null);
		listModel = new DefaultListModel<Account>();
		if (accounts != null) {
			for (Account a : accounts) {
				listModel.addElement(a);
			}
		}
		fList.setModel(listModel);
		fList.setCellRenderer(new FriendListCellRenderer(mainFrame));
		fList.setOpaque(false);
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
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) { // 如果点击的是左键，并且点击了两下，BUTTON1指鼠标左键，BUTTON2指滚轮,BUTTON3指鼠标右键
					@SuppressWarnings("rawtypes")
					JList list = (JList)e.getSource();
					Account toAccount = (Account) list.getModel().getElementAt(index); // 获取到点击是哪一个账号
					if (!mainFrame.getChatFrames().containsKey(toAccount)) { // 如果这个键没有对应的聊天窗体，则new一个聊天窗体
						ChatFrame chatFrame = new ChatFrame(client, account, toAccount);
						mainFrame.getChatFrames().put(toAccount, chatFrame); // 把好友和好友所对应的聊天窗体put进map里面
					} else { // 如果已经有对应的聊天窗体了
						mainFrame.getChatFrames().get(toAccount).setVisible(true); // 把toAcoount所对应的窗体设置为可见
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		JScrollPane scroll = new JScrollPane(fList);
		scroll.setBounds(10, 0, 260, 360);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.setBorder(null);
		add(scroll);
	}
	
	public void updateFriendList(Account account) {
		listModel.addElement(account);
	}
	
	public void updateFriendData(Account account) {
		int index = listModel.indexOf(account); // 根据账号获取到好友的索引
		listModel.removeElement(account); // 把原来的好友remove掉
		listModel.insertElementAt(account, index); // 把最新的account插入到原来的位置上
	}
}
