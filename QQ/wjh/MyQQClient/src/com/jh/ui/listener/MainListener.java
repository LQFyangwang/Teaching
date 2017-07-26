package com.jh.ui.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.Constants;
import com.jh.common.DateUtil;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.ui.AlterSkinFrame;
import com.jh.ui.ApplyManageFrame;
import com.jh.ui.ChatFrame;
import com.jh.ui.MainFrame;
import com.jh.ui.MainFrame.MessageThread;
import com.jh.ui.MainFrame.MusicThread;
import com.jh.ui.MainFrame.RequstThread;
import com.jh.ui.SeekFriendFrame;
import com.jh.ui.SelectDataFrame;
import com.jh.ui.common.CommonMethod;

public class MainListener extends MouseAdapter {
	
	private String name;
	private MainFrame mainFrame;
	private Client client;
	private Account account;
	private JLabel label;
	private RequstThread requstThread;
	
	public MainListener(MainFrame mainFrame, JLabel label) {
		this.mainFrame = mainFrame;
		this.label = label;
		this.client = mainFrame.getClient();
		this.account = mainFrame.getAccount();
	}

	// 监听鼠标按下事件
	@Override
	public void mouseClicked(MouseEvent e) {
		name = CommonMethod.getLabelName(e);
		if (name != null) {
			if (name.equals("head")) { // 头像
				if (Constants.select) {
					SelectDataFrame selectData = new SelectDataFrame(mainFrame, account);
					mainFrame.setSelectData(selectData);
				}
			} else if (name.equals("space")) { // QQ空间
				CommonMethod.shortcutOpen("http://i.qq.com/");
			} else if (name.equals("email")) { // 邮箱
				CommonMethod.shortcutOpen("https://mail.qq.com/");
			} else if (name.equals("skin")) { // 皮肤
				if (Constants.isAlterSkinFrameOpen) {
					new AlterSkinFrame(mainFrame);
				}
			} else if (name.equals("news")) { // 消息盒子
				MessageThread messageThread = mainFrame.getMessageThread();
				if (messageThread.getNeedRunning()) { // 只有启动了线程才可以弹出聊天窗体
					messageThread.setNeedRunning(false); // 停止闪动
					label.setIcon(CommonMethod.getImg(mainFrame, "news_def.png"));
					Message message = mainFrame.getMessage();
					Account toAccount = message.getFromAccount();
					if (!mainFrame.getChatFrames().containsKey(toAccount)) { // 如果这个键没有对应的聊天窗体，则new一个聊天窗体
						ChatFrame chatFrame = new ChatFrame(client, account, toAccount);
						mainFrame.getChatFrames().put(toAccount, chatFrame); // 把好友和好友所对应的聊天窗体put进map里面
					} else { // 如果已经有对应的聊天窗体了
						mainFrame.getChatFrames().get(toAccount).setVisible(true); // 把toAcoount所对应的窗体设置为可见
					}
					ChatFrame chatFrame = mainFrame.getChatFrames().get(toAccount);
					chatFrame.updateMessage(message);
				}
			} else if (name.equals("group")) { // 微博
				CommonMethod.shortcutOpen("http://t.qq.com/");
			} else if (name.equals("paipai")) { // 拍拍
				CommonMethod.shortcutOpen("http://www.paipai.com/");
			} else if (name.equals("purse")) { // 财付通
				CommonMethod.shortcutOpen("https://www.tenpay.com/v3/");
			} else if (name.equals("sousou")) { // 搜搜
				CommonMethod.shortcutOpen("http://www.sogou.com/");
			} else if (name.equals("game")) { // QQ游戏
				CommonMethod.shortcutOpen("http://qqgame.qq.com/");
			} else if (name.equals("music")) { // QQ音乐
				CommonMethod.shortcutOpen("http://y.qq.com/#type=index");
			} else if (name.equals("video")) { // 腾讯视频
				CommonMethod.shortcutOpen("http://v.qq.com/");
			} else if (name.equals("pet")) { // QQ宠物
				
			} else if (name.equals("menu")) { // 应用管理器
				if (Constants.isApplyManageFrameOpen) {
					new ApplyManageFrame();
				}
			} else if (name.equals("function")) { // 主菜单
				JPopupMenu menu = new JPopupMenu();
				JMenuItem selectItem = new JMenuItem("修改密码");
				selectItem.setActionCommand("select");
				selectItem.addActionListener(new FunctionActionListener(mainFrame));
				menu.add(selectItem);
				JMenuItem changeItem = new JMenuItem("切换账号");
				changeItem.setActionCommand("change");
				changeItem.addActionListener(new FunctionActionListener(mainFrame));
				menu.add(changeItem);
				JMenuItem logoutItem = new JMenuItem("注销登入");
				logoutItem.setActionCommand("logout");
				logoutItem.addActionListener(new FunctionActionListener(mainFrame));
				menu.add(logoutItem);
				label.add(menu);
				menu.show(label, 25, -65);
				
			} else if (name.equals("set")) { // 系统设置
				
			} else if (name.equals("message")) { // 消息管理器
				requstThread = mainFrame.getRequstThread();
				MusicThread musicThread = mainFrame.getMusicThread();
				if (requstThread != null || musicThread != null) {
					requstThread.setNeedRunning(false); // 停止闪动
					musicThread.setNeedRunning(false);
					label.setIcon(CommonMethod.getImg(mainFrame, "message_def.png"));
					Message message = mainFrame.getMessage(); // 获取到消息对象
					AccountDAO accountDAO = new AccountDAOImpl();
					boolean isOk = accountDAO.queryFriend(message.getFromAccount().getNumber(), message.getToAccount().getNumber()); // 每次弹窗之前去查询一遍数据库，看看是否已经是自己的好友
					if (!isOk) {
						int result = JOptionPane.showConfirmDialog(mainFrame,
								message.getFromAccount().getNickname() + " 想添加您为好友", "添加好友",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE); // 弹窗
						if (result == JOptionPane.OK_OPTION) { // 如果用户点的是确定
							accountDAO.addFriends(message.getToAccount().getNumber(),
									message.getFromAccount().getNumber()); // 把数据添加到t_friends表中
							Message m = new Message(Message.REQUST_REV_MSG, message.getToAccount(),
									message.getFromAccount(), DateUtil.getDate(),
									message.getToAccount().getNickname() + "已添加您为好友");
							client.sendMessage(m);
							mainFrame.updateFriendList(message.getFromAccount());
						}
					}
					
				}
			} else if (name.equals("safe")) { // 账号设置
				
			} else if (name.equals("use")) { // 应用宝
				
			} else if (name.equals("seachNumber")) { // 查找账号
				if (Constants.isSeekFirendFrameOpen) {
					new SeekFriendFrame(mainFrame, account,  client);
				}
			}
		}
	}

	// 监听鼠标经过事件
	@Override
	public void mouseEntered(MouseEvent e) {
		name = CommonMethod.getLabelName(e);
		if (name.equals("head")) {
			label.setBorder(BorderFactory.createLineBorder(new Color(0, 238, 118)));
		} else {
			label.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190)));
		}
	}

	// 监听鼠标离开事件
	@Override
	public void mouseExited(MouseEvent e) {
		label.setBorder(null);
	}
}
