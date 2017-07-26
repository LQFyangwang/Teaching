package com.gs.qq.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import com.gs.bean.Account;
import com.gs.bean.Message;
import com.gs.qq.client.Client;
import com.gs.qq.ui.common.UIUtil;
import com.gs.qq.ui.listener.ChatFrameBtnListener;
import com.gs.qq.ui.listener.FrameDragListener;

public class ChatFrame extends JFrame {

	private static final long serialVersionUID = -8539940165466016056L;
	private JTextArea area;
	private JTextArea chatArea;
	private Client client;
	private Account account;
	private Account toAccount;
	
	public Account getAccount() {
		return account;
	}
	
	public Account getToAccount() {
		return toAccount;
	}
	
	public JTextArea getArea() {
		return area;
	}
	
	public JTextArea getChatArea() {
		return chatArea;
	}
	
	/**
	 * 实例化一个聊天窗口
	 * @param client
	 * @param account
	 * @param toAccount
	 */
	public ChatFrame(Client client, Account account, Account toAccount) {
		this.client = client;
		this.account = account;
		this.toAccount = toAccount;
		setSize(400, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setUndecorated(true);
		FrameDragListener dragLtn = new FrameDragListener(this);
		addMouseListener(dragLtn);
		addMouseMotionListener(dragLtn);
		UIUtil.setLookAndFeel();
		center();
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setVisible(true);
	}
	
	private void top() {
		// 对方好友的信息
	}
	
	private void center() {
		area = new JTextArea();
		area.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.setBounds(0,0,380,200);
		add(scrollPane);
		chatArea = new JTextArea();
		JScrollPane scrollPane1 = new JScrollPane(chatArea);
		scrollPane1.setBounds(0,210,380,100);
		add(scrollPane1);
		JButton btn = new JButton("发送");
		btn.setBounds(330, 330, 60, 30);
		btn.setActionCommand("send");
		ChatFrameBtnListener btnListener = new ChatFrameBtnListener(client, this);
		btn.addActionListener(btnListener);
		add(btn);
		
	}

	public void updateMessage(Message message) {
		area.setText(area.getText() + "\n" + message.getFromAccount().getNickname() + "说" + message.getMessage());
	}
	
	

}
