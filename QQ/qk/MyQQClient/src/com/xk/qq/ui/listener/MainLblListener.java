package com.xk.qq.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.xk.bean.Message;
import com.xk.common.DateUtil;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;
import com.xk.qq.client.Client;
import com.xk.qq.ui.MainFrame;
import com.xk.qq.ui.MainFrame.RepThread;

public class MainLblListener extends MouseAdapter {
	
	private Client client;
	private MainFrame mainFrame;
	
	
	/**
	 * 当用户点击系统消息图标的时候，需要显示dialog，该图标切换成默认的图标
	 * @param client
	 * @param mainFrame
	 */
	public MainLblListener(Client client,MainFrame mainFrame){
		this.client = client;
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel lbl = (JLabel)e.getSource();
		String name = lbl.getName();
		if(name.equals("repLbl")){
			AccountDAO accountDAO = new AccountDAOImpl();
			RepThread repThread = mainFrame.getRepThread(); // 获取已经启动该线程切换图标
			if(repThread != null){  // 如果已经启动则图标在闪动
				mainFrame.getVoiceLbl().setIcon(new ImageIcon("images/voice_def.png")); // 设置图标为默认图标
				repThread.setNeedRunning(false); 		// 让线程停止
				Message message = mainFrame.getMessage(); 
					// 弹出添加好友对话框
				
				if(!accountDAO.queryFriend(message.getFromAccount().getNumber(),message.getToAccount().getNumber())){
					int result = JOptionPane.showConfirmDialog(mainFrame, message.getFromAccount().getNumber() + "想要添加你为好友","添加好友",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.OK_OPTION){ // 如果同意该好友
						mainFrame.getVoiceLbl().setIcon(new ImageIcon("images/voice_def.png"));
						accountDAO.addFriend(message.getToAccount().getNumber(), message.getFromAccount().getNumber()); // 把好友添加到数据库
						Message m = new Message(Message.REQUEST_REV_MSG,DateUtil.getDate(),message.getToAccount(),message.getFromAccount(),"已添加你为好友");
						// 由该好友getToAccount返回消息说，已添加你为好友
						client.sendMessage(m);
						mainFrame.updateFriendList(message.getFromAccount());
						accountDAO.deleteBalck(message.getToAccount().getNumber(), message.getFromAccount().getNumber());
						mainFrame.deleteBlckList(message.getFromAccount());
					}
				}
				
			}
		}
	}


}
