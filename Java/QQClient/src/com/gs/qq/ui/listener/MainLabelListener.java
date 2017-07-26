package com.gs.qq.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.gs.bean.Message;
import com.gs.common.DateUtil;
import com.gs.dao.AccountDAO;
import com.gs.dao.AccountDAOImpl;
import com.gs.qq.client.Client;
import com.gs.qq.ui.MainFrame;
import com.gs.qq.ui.MainFrame.ReqThread;

public class MainLabelListener extends MouseAdapter {

	private MainFrame mainFrame;
	private Client client;
	
	/**
	 * 当用户点击系统消息图标的时候，需要显示dialog，该图标切换成默认的图标
	 * @param client
	 * @param mainFrame
	 */
	public MainLabelListener(Client client, MainFrame mainFrame) {
		this.client = client;
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel lbl = (JLabel) e.getSource();
		String name = lbl.getName();
		if (name.equals("reqMsg")) {
			ReqThread reqThread = mainFrame.getReqThread(); // 去获取是否已经启动的切换图标的线程
			if (reqThread != null) { // 如果已经启动了该线程（图标在闪动）
				mainFrame.getReqMsgLbl().setIcon(new ImageIcon("img/qme.png")); // 切换为默认图标
				reqThread.setNeedRunning(false); // 停止该线程
				Message message = mainFrame.getMessage();
				// 展开添加好友的对话框
				int result = JOptionPane.showConfirmDialog(mainFrame, message.getFromAccount().getNumber() + "想要添加你为好友", "添加好友", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (result == JOptionPane.OK_OPTION) { // 同意添加好友
					AccountDAO accountDAO = new AccountDAOImpl();
					accountDAO.addFriend(message.getToAccount().getNumber(), message.getFromAccount().getNumber());
					Message m = new Message(Message.REQUEST_REV_MSG,message.getToAccount(), message.getFromAccount(), DateUtil.getDate(), message.getToAccount().getNickname() + "已添加你为好友");
					client.sendMessage(m);
					mainFrame.updateFriendList(message.getFromAccount());
				}
			}
		}
	}

}
