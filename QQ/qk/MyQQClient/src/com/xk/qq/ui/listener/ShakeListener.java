package com.xk.qq.ui.listener;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.xk.bean.Message;
import com.xk.common.DateUtil;
import com.xk.qq.client.Client;
import com.xk.qq.ui.ChatFrame;

public class ShakeListener extends MouseAdapter {
	private JLabel lbl;
	private ChatFrame chatFrame;
	private Client client;
	public ShakeListener(JLabel lbl,ChatFrame chatFrame,Client client) {
		this.lbl = lbl;
		this.chatFrame = chatFrame;
		this.client  = client;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		String name = label.getName();
		if (name.equals("bottom4")) {
			chatFrame.getShakeLbl().setText("你发送了一个抖动");
			chatFrame.updateShake();
			Message m = new Message(Message.SHAKE_MSG,DateUtil.getDate(),chatFrame.getAccount(),chatFrame.getToAccount(),"抖动");
			client.sendMessage(m);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		lbl.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0.3f)));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		lbl.setBorder(null);
	}

}
