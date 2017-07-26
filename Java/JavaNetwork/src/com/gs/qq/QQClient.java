package com.gs.qq;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class QQClient extends JFrame implements ActionListener {

	private static final long serialVersionUID = 2784422471275147379L;
	
	private JTextArea msgTxt;
	private QUser user;
	
	private QClient client;

	public QQClient() {
		setTitle("QQ client");
		setSize(400, 400);
		getContentPane().setLayout(new BorderLayout());
		msgTxt = new JTextArea(5, 5);
		JButton sendBtn = new JButton("·¢ËÍ");
		sendBtn.setActionCommand("send");
		sendBtn.addActionListener(this);
		add(msgTxt, BorderLayout.CENTER);
		add(sendBtn, BorderLayout.SOUTH);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		user = new QUser();
		user.setQq("10000");
		user.setName("10000");
		client = new QClient(user);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("send")) {
			String message = msgTxt.getText();
			user.setMessage(message);
			client.sendMsg(user);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new QQClient();
			}
			
		});
	}
}
