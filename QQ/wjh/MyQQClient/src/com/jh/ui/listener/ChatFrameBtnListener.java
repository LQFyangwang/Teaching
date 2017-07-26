package com.jh.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.DateUtil;
import com.jh.ui.ChatFrame;

public class ChatFrameBtnListener implements ActionListener {

	private Client client;
	private ChatFrame chatFrame;
	
	public ChatFrameBtnListener(ChatFrame chatFrame, Client client) {
		this.chatFrame = chatFrame;
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("close")) { // 关闭
			chatFrame.dispose();
		} else if (action.equals("send")) { // 发送
			String msg = chatFrame.getInputArea().getText(); // 获取到输入的值
			Message m = new Message(Message.NORMAL_MSG, chatFrame.getAccount(), chatFrame.getToAccount(), DateUtil.getDate(), msg);
			JTextArea area = chatFrame.getMessageArea(); // 获取到输入的JTextArea
			String text = area.getText() + "\n" + m.getFromAccount().getNickname() + "  " + DateUtil.getDateStr(DateUtil.getDate()) + "\n" + msg; // 在自己的界面显示的话
			area.setText(text);
			client.sendMessage(m);
			chatFrame.getInputArea().setText("");
		}
	}

}
