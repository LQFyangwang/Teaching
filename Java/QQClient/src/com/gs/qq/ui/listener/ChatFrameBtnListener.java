package com.gs.qq.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JTextArea;

import com.gs.bean.Message;
import com.gs.qq.client.Client;
import com.gs.qq.ui.ChatFrame;

public class ChatFrameBtnListener implements ActionListener {

	private ChatFrame chatFrame;
	private Client client;
	
	public ChatFrameBtnListener(Client client, ChatFrame chatFrame) {
		this.client = client;
		this.chatFrame = chatFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("send")) {
			String msg = chatFrame.getChatArea().getText();
			Message m = new Message(Message.NORMAL_MSG, chatFrame.getAccount(), chatFrame.getToAccount(), Calendar.getInstance().getTime(), msg);
			JTextArea area = chatFrame.getArea();
			String text = area.getText() + "\n˵" + msg;
			area.setText(text);
			client.sendMessage(m);
		}
	}

}
