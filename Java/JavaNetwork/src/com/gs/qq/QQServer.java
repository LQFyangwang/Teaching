package com.gs.qq;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class QQServer extends JFrame implements ActionListener {

	private static final long serialVersionUID = -701275455824750579L;
	
	private JTextArea msgTxt;

	public QQServer() {
		setTitle("QQ");
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
		new QServer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("send")) {
			String message = msgTxt.getText();
			
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new QQServer();
			}
			
		});
	}
	
}
