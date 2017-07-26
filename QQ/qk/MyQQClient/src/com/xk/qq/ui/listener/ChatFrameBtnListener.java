package com.xk.qq.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import com.xk.bean.Message;
import com.xk.common.DateUtil;
import com.xk.qq.client.Client;
import com.xk.qq.ui.ChatFrame;

public class ChatFrameBtnListener implements ActionListener {

	private Client client;
	private ChatFrame chatFrame;
	
	public ChatFrameBtnListener(Client client,ChatFrame chatFrame){
		this.client = client;
		this.chatFrame = chatFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("send")){
			String msg = chatFrame.getArea().getText();
			Message m = new Message(Message.NORMAL_MSG,DateUtil.getDate(),chatFrame.getAccount(),chatFrame.getToAccount(),msg);
			
//			System.out.println("account:"+chatFrame.getAccount());
			JTextArea centerArea = chatFrame.getCenterArea();
			String text = centerArea.getText() + "\n说:" + msg + "  发送时间： " +DateUtil.getTime();;
			centerArea.setText(text);
			client.sendMessage(m);
			chatFrame.getArea().setText(" ");
		}
	}

}
