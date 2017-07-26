package com.jh.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.Constants;
import com.jh.common.DateUtil;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;
import com.jh.ui.common.MessageMusics;
import com.jh.ui.listener.ExitListener;
import com.jh.ui.listener.MainListener;
import com.jh.ui.listener.MinListener;
import com.jh.ui.listener.StatusListener;
import com.jh.ui.listener.TxtFocusListener;
import com.jh.ui.listener.WindowCloseListener;
import com.jh.ui.panel.MainDialoguePanel;
import com.jh.ui.panel.MainFriendPanel;
import com.jh.ui.panel.MainGroupPanel;

/**
 * 主窗体
 * @author Administrator
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 6495052281715015592L;
	
	private Account account; // 登入的账号
	private Client client; // 登入的客户端
	private JLabel messageLbl; // 喇叭标签
	private Message message; // 消息对象
	private RequstThread requstThread; // 喇叭闪动的线程
	private MainFriendPanel mainFriendPanel; // 好友列表的面板
	private MainDialoguePanel mainDialoguePanel; // 会话面板
	private AccountDAO accountDAO; // 用来操作数据库
	
	public static JLabel bgLbl; // 背景标签
	public static JLabel stateLbl; // 状态标签
	
	private Map<Account, ChatFrame> chatFrames; // 键值对，一个账号对应一个聊天窗口
	
	public Map<Account, ChatFrame> getChatFrames() {
		return chatFrames;
	}

	public void setChatFrames(Map<Account, ChatFrame> chatFrames) {
		this.chatFrames = chatFrames;
	}

	public MainFrame(Account account, Client client) {
		this.account = account;
		this.client = client;
		client.setMainFrame(this); // 把主窗体传递给客户端
		Constants.mainOk = true;
		CommonMethod.client = client;
		CommonMethod.account = account;
		chatFrames = new HashMap<Account, ChatFrame>();
		mm = new MessageMusics();
		LoginFrameCommon.setStyle(this);
		try {
			LookAndFeelInfo[] feel = UIManager.getInstalledLookAndFeels(); // 获取所有风格
			UIManager.setLookAndFeel(feel[0].getClassName()); // 设置Windows风格
		} catch (Exception e) {
			e.printStackTrace();
		}
		accountDAO = new AccountDAOImpl();
		account.setStatus(CommonMethod.IMG_URL);
		accountDAO.update(account);
		Message m = new Message(Message.UPDATE_FRIEND_MSG, account, account, DateUtil.getDate(), "状态修改为" + account.getStatus());
		client.sendMessage(m);
		CommonMethod.setTray(this);
		initTopBtn();
		setBounds(950, 30, 280, 600);
		setBackground();
		initTop();
		initCenter();
		initBottom();
		addWindowListener(new WindowCloseListener(client, account));
	}

	/**
	 * 设置背景
	 */
	private void setBackground() {
		Icon bgIcon = CommonMethod.getImg(this, account.getSkin() + ".png");
		bgLbl = new JLabel(bgIcon);
		bgLbl.setBounds(0, 0, 280, 600);
		add(bgLbl);
	}
	
	/**
	 * 初始化窗体头部的按钮
	 */
	private void initTopBtn() {
		Icon qqIconIcon = CommonMethod.getImg(this, "main_qq_icon.png");
		JLabel qqIconLbl = new JLabel(qqIconIcon);
		qqIconLbl.setBounds(0, 0, 60, 30);
		add(qqIconLbl);
		Icon minIcon = CommonMethod.getImg(this, "min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setBounds(220, 0, 30, 30);
		minLbl.setToolTipText("最小化");
		minLbl.setName("task_min");
		minLbl.addMouseListener(new MinListener(this));
		add(minLbl);
		Icon exitIcon = CommonMethod.getImg(this, "exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setBounds(250, 0, 30, 30);
		exitLbl.setToolTipText("关闭");
		exitLbl.addMouseListener(new ExitListener(client, account));
		add(exitLbl);
	}
	
	private JLabel headLbl; // 头像
	private JLabel nicknameLbl; // 昵称
	private JTextField autographTxt; // 个性签名
	private SelectDataFrame selectData;
	private JLabel newsLbl; // 消息盒子
	

	public SelectDataFrame getSelectData() {
		return selectData;
	}

	public void setSelectData(SelectDataFrame selectData) {
		this.selectData = selectData;
	}
	
	public void updaDataAutograph(Account account) {
		if (selectData != null) {
			selectData.updateAutograph(account);
		}
	}

	/**
	 * 设置主窗体上面部分信息
	 */
	private void initTop() {
		// 头像
		Icon headIcon = CommonMethod.getImg(this, account.getHead() + ".png");
		headLbl = new JLabel(headIcon);
		headLbl.setBounds(5, 30, 80, 80);
		headLbl.setName("head");
		headLbl.addMouseListener(new MainListener(this, headLbl));
		bgLbl.add(headLbl);
		// 昵称
		nicknameLbl = new JLabel(account.getNickname());
		nicknameLbl.setBounds(90, 40, 50, 20);
		nicknameLbl.setFont(new Font("微软雅黑", Font.BOLD, 16));
		bgLbl.add(nicknameLbl);
		// 在线状态
		JLabel stateGroupLbl = new JLabel();
		stateGroupLbl.setBounds(140, 40, 40, 20);
		stateGroupLbl.addMouseListener(new StatusListener(this, stateGroupLbl, account, client));
		Icon stateIcon = CommonMethod.getImg(this, account.getStatus() + ".png");
		stateLbl = new JLabel(stateIcon);
		stateLbl.setBounds(0, 0, 20, 20);
		stateGroupLbl.add(stateLbl);
		Icon triangleIcon = CommonMethod.getImg(this, "triangle.png");
		JLabel triangleLbl = new JLabel(triangleIcon);
		triangleLbl.setBounds(20, 0, 20, 20);
		stateGroupLbl.add(triangleLbl);
		bgLbl.add(stateGroupLbl);
		// 等级
		JLabel leaveLbl = new JLabel(CommonMethod.getImg(this, "leave89.png"));
		leaveLbl.setBounds(180, 40, 30, 20);
		leaveLbl.setName("grade");
		leaveLbl.addMouseListener(new MainListener(this, leaveLbl));
		bgLbl.add(leaveLbl);
		JLabel vipLbl = new JLabel(CommonMethod.getImg(this, "svipAdd.png"));
		vipLbl.setBounds(210, 40, 34, 20);
		vipLbl.setName("grade");
		vipLbl.addMouseListener(new MainListener(this, vipLbl));
		bgLbl.add(vipLbl);
		// 靓字
		Icon beautifulIcon = CommonMethod.getImg(this, "beautiful.png");
		JLabel beautifulLbl = new JLabel(beautifulIcon);
		beautifulLbl.setBounds(240, 40, 20, 20);
		beautifulLbl.setName("beautiful");
		beautifulLbl.addMouseListener(new MainListener(this, beautifulLbl));
		bgLbl.add(beautifulLbl);
		// 空JTextField
		JTextField nullTxt = new JTextField();
		nullTxt.setBounds(10, 0, 0, 0);
		bgLbl.add(nullTxt);
		// 个性签名
		autographTxt = new JTextField();
		autographTxt.setBounds(90, 60, 150, 30);
//		autographTxt.setBackground(new Color(0, 0, 0, 0)); // 设置为透明
		autographTxt.setOpaque(false);
		autographTxt.setBorder(null);
		if (account.getAutograph() == null) {
			autographTxt.setText(" 编辑个性签名");
		} else {
			autographTxt.setText(account.getAutograph());
		}
		autographTxt.setName("autograph");
		autographTxt.addFocusListener(new TxtFocusListener(this, autographTxt, account));
		autographTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					nullTxt.setFocusable(true);
					nullTxt.requestFocus();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
		});
//		autographTxt.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0))); // 设置边框颜色
		bgLbl.add(autographTxt);
		// QQ空间
		Icon spaceIcon = CommonMethod.getImg(this, "space.png");
		JLabel spaceLbl = new JLabel(spaceIcon);
		spaceLbl.setBounds(90, 85, 20, 20);
		spaceLbl.setName("space");
		spaceLbl.addMouseListener(new MainListener(this, spaceLbl));
		spaceLbl.setToolTipText("QQ空间");
		bgLbl.add(spaceLbl);
		// 邮箱
		Icon emailIcon = CommonMethod.getImg(this, "email.png");
		JLabel emailLbl = new JLabel(emailIcon);
		emailLbl.setBounds(110, 85, 20, 20);
		emailLbl.setName("email");
		emailLbl.addMouseListener(new MainListener(this, emailLbl));
		emailLbl.setToolTipText("QQ邮箱");
		bgLbl.add(emailLbl);
		// 微博
		Icon groupIcon = CommonMethod.getImg(this, "wblog.png");
		JLabel groupLbl = new JLabel(groupIcon);
		groupLbl.setBounds(130, 85, 20, 20);
		groupLbl.setName("group");
		groupLbl.addMouseListener(new MainListener(this, groupLbl));
		groupLbl.setToolTipText("微博");
		bgLbl.add(groupLbl);
		// 拍拍
		Icon paiIcon = CommonMethod.getImg(this, "paipai_top.png");
		JLabel paiLbl = new JLabel(paiIcon);
		paiLbl.setBounds(150, 85, 20, 20);
		paiLbl.setName("paipai");
		paiLbl.addMouseListener(new MainListener(this, paiLbl));
		paiLbl.setToolTipText("拍拍");
		bgLbl.add(paiLbl);
		// 财付通
		Icon purseIcon = CommonMethod.getImg(this, "purse.png");
		JLabel purseLbl = new JLabel(purseIcon);
		purseLbl.setBounds(170, 85, 20, 20);
		purseLbl.setName("purse");
		purseLbl.addMouseListener(new MainListener(this, purseLbl));
		purseLbl.setToolTipText("财付通");
		bgLbl.add(purseLbl);
		// 搜搜
		Icon soIcon = CommonMethod.getImg(this, "soso.png");
		JLabel soLbl = new JLabel(soIcon);
		soLbl.setBounds(190, 85, 20, 20);
		soLbl.setName("sousou");
		soLbl.addMouseListener(new MainListener(this, soLbl));
		soLbl.setToolTipText("搜搜");
		bgLbl.add(soLbl);
		// 皮肤
		Icon skinIcon = CommonMethod.getImg(this, "skin.png");
		JLabel skinLbl = new JLabel(skinIcon);
		skinLbl.setBounds(240, 85, 20, 20);
		skinLbl.setName("skin");
		skinLbl.addMouseListener(new MainListener(this, skinLbl));
		skinLbl.setToolTipText("更改外观");
		bgLbl.add(skinLbl);
		// 消息盒子
		Icon newsIcon = CommonMethod.getImg(this, "news_def.png");
		newsLbl = new JLabel(newsIcon);
		newsLbl.setBounds(260, 85, 20, 20);
		newsLbl.setToolTipText("消息盒子");
		newsLbl.setName("news");
		newsLbl.addMouseListener(new MainListener(this, newsLbl));
		bgLbl.add(newsLbl);
		// 搜索框
		JTextField seekTxt = new JTextField();
		seekTxt.setBounds(0, 110, 280, 30);
		seekTxt.setText("  搜索：联系人、讨论组、群、企业");
		seekTxt.setOpaque(false);
		seekTxt.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		seekTxt.setName("seek1");
		seekTxt.addFocusListener(new TxtFocusListener(this, seekTxt, null));
		Icon seekIcon = CommonMethod.getImg(this, "seek.png");
		JLabel seekLbl = new JLabel(seekIcon);
		seekLbl.setBounds(250, 5, 20, 20);
		seekTxt.add(seekLbl);
		bgLbl.add(seekTxt);
	}
	
	/**
	 * 设置中间好友面板
	 */
	private void initCenter() {
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setBounds(10, 140, 260, 400);
		tabPane.setBorder(null);
		UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE); // 设置选项卡可以设置透明
		tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); // 设置选项卡的布局方式
		mainFriendPanel = new MainFriendPanel(this, client, account, accountDAO.queryFriends(account.getNumber()));
		mainDialoguePanel = new MainDialoguePanel(this, null);
		tabPane.addTab("", CommonMethod.getImg(this, "contacts_def.png"), mainFriendPanel, "联系人");
		tabPane.addTab("", CommonMethod.getImg(this, "dialogue_def.png"), mainDialoguePanel, "会话消息");
		tabPane.addTab("", CommonMethod.getImg(this, "group_def.png"), new MainGroupPanel(this, null), "群、讨论组");
		bgLbl.add(tabPane);
	}
	
	/**
	 * 设置主窗体底部的一些信息
	 */
	private void initBottom() {
		// 腾讯微博
		Icon groupIcon = CommonMethod.getImg(this, "QQGroup.png");
		JLabel groupLbl = new JLabel(groupIcon);
		groupLbl.setBounds(5, 545, 30, 20);
		groupLbl.setToolTipText("腾讯微博");
		groupLbl.setName("group");
		groupLbl.addMouseListener(new MainListener(this, groupLbl));
		bgLbl.add(groupLbl);
		// QQ游戏
		Icon gameIcon = CommonMethod.getImg(this, "QQGame.png");
		JLabel gameLbl = new JLabel(gameIcon);
		gameLbl.setBounds(35, 545, 30, 20);
		gameLbl.setToolTipText("QQ游戏");
		gameLbl.setName("game");
		gameLbl.addMouseListener(new MainListener(this, gameLbl));
		bgLbl.add(gameLbl);
		// QQ音乐
		Icon musicIcon = CommonMethod.getImg(this, "music.png");
		JLabel musicLbl = new JLabel(musicIcon);
		musicLbl.setBounds(65, 545, 30, 20);
		musicLbl.setToolTipText("QQ音乐");
		musicLbl.setName("music");
		musicLbl.addMouseListener(new MainListener(this, musicLbl));
		bgLbl.add(musicLbl);
		// 腾讯视频
		Icon videoIcon = CommonMethod.getImg(this, "video.png");
		JLabel videoLbl = new JLabel(videoIcon);
		videoLbl.setBounds(95, 545, 30, 20);
		videoLbl.setToolTipText("腾讯视频");
		videoLbl.setName("video");
		videoLbl.addMouseListener(new MainListener(this, videoLbl));
		bgLbl.add(videoLbl);
		// QQ宠物
		Icon petIcon = CommonMethod.getImg(this, "QQPet.png");
		JLabel petLbl = new JLabel(petIcon);
		petLbl.setBounds(125, 545, 30, 20);
		petLbl.setToolTipText("QQ宠物");
		petLbl.setName("pet");
		petLbl.addMouseListener(new MainListener(this, petLbl));
		bgLbl.add(petLbl);
		// 应用管理器
		Icon menuIcon = CommonMethod.getImg(this, "menu.png");
		JLabel menuLbl = new JLabel(menuIcon);
		menuLbl.setBounds(245, 545, 30, 20);
		menuLbl.setToolTipText("打开应用管理器");
		menuLbl.setName("menu");
		menuLbl.addMouseListener(new MainListener(this, menuLbl));
		bgLbl.add(menuLbl);
		
		Icon functionIcon = CommonMethod.getImg(this, "function.png");
		JLabel functionLbl = new JLabel(functionIcon);
		functionLbl.setBounds(5, 570, 30, 20);
		functionLbl.setToolTipText("主菜单");
		functionLbl.setName("function");
		functionLbl.addMouseListener(new MainListener(this, functionLbl));
		bgLbl.add(functionLbl);
		Icon setIcon = CommonMethod.getImg(this, "set.png");
		JLabel setLbl = new JLabel(setIcon);
		setLbl.setBounds(35, 570, 30, 20);
		setLbl.setToolTipText("打开系统设置");
		setLbl.setName("set");
		setLbl.addMouseListener(new MainListener(this, setLbl));
		bgLbl.add(setLbl);
		Icon messageIcon = CommonMethod.getImg(this, "message_def.png");
		messageLbl = new JLabel(messageIcon);
		messageLbl.setBounds(65, 570, 30, 20);
		messageLbl.setToolTipText("打开消息管理器");
		messageLbl.setName("message");
		messageLbl.addMouseListener(new MainListener(this, messageLbl));
		bgLbl.add(messageLbl);
		Icon safeIcon = CommonMethod.getImg(this, "QQSafe.png");
		JLabel safeLbl = new JLabel(safeIcon);
		safeLbl.setBounds(95, 570, 30, 20);
		safeLbl.setToolTipText("账号管理");
		safeLbl.setName("safe");
		safeLbl.addMouseListener(new MainListener(this, safeLbl));
		bgLbl.add(safeLbl);
		Icon seachIcon = CommonMethod.getImg(this, "group_seach.png");
		JLabel seachLbl = new JLabel("查找账号", seachIcon, JLabel.LEFT);
		seachLbl.setBounds(125, 570, 80, 20);
		seachLbl.setToolTipText("查找账号");
		seachLbl.setName("seachNumber");
		seachLbl.addMouseListener(new MainListener(this,seachLbl));
		bgLbl.add(seachLbl);
		Icon useIcon = CommonMethod.getImg(this, "use.png");
		JLabel useLbl = new JLabel("应用宝", useIcon, JLabel.LEFT);
		useLbl.setBounds(210, 570, 80, 20);
		useLbl.setToolTipText("应用宝");
		useLbl.setName("use");
		useLbl.addMouseListener(new MainListener(this, useLbl));
		bgLbl.add(useLbl);
	}
	
	public MessageMusics mm;
	public MusicThread musicThread;
	private MessageThread messageThread;
	/**
	 * 启动添加好友图标闪动和播放音乐的线程的线程
	 */
	public void requstAddFriend(Message message) {
		this.message = message;
		musicThread = new MusicThread();
		new Thread(musicThread).start();
		requstThread = new RequstThread(this);
		new Thread(requstThread).start();
	}
	
	/**
	 * 启动普通消息闪动的线程
	 */
	public void requstMessage(Message message) {
		this.message = message;
		messageThread = new MessageThread(this);
		new Thread(messageThread).start();
	}
	/**
	 * 消息对象get方法
	 * @return
	 */
	public Message getMessage() {
		return this.message;
	}
	
	/**
	 * 客户端对象get方法
	 * @return
	 */
	public Client getClient() {
		return this.client;
	}
	
	/**
	 * 获取account对象get方法
	 * @return
	 */
	public Account getAccount() {
		return this.account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	/**
	 * 线程的get方法
	 * @return
	 */
	public RequstThread getRequstThread() {
		return this.requstThread;
	}
	
	public MusicThread getMusicThread() {
		return this.musicThread;
	}
	
	public MessageThread getMessageThread() {
		return this.messageThread;
	}
	
	/**
	 * 消息图标闪动的线程
	 * @author Administrator
	 *
	 */
	public class RequstThread implements Runnable {

		private MainFrame mainFrame; // 主界面的窗体
		private boolean needRunning = true; // 是否一直闪动
		private boolean isConversion = false; // 是否更换图片
		
		public RequstThread(MainFrame mainFrame) {
			this.mainFrame = mainFrame;
		}
		
		// 如果说，用户用鼠标点击了消图标，则可以不用闪动了
		public void setNeedRunning(boolean needRunning) {
			this.needRunning = needRunning;
		}
		
		@Override
		public void run() {
			while (needRunning) {
				try {
					Thread.sleep(Constants.MESSAGE_SLEEP); // 休眠1s
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!isConversion) {
					messageLbl.setIcon(CommonMethod.getImg(mainFrame, "message_over.png"));
					isConversion = true;
				} else {
					messageLbl.setIcon(CommonMethod.getImg(mainFrame, "message_def.png"));
					isConversion = false;
				}
			}
		}
	}
	
	/**
	 * 消息盒子闪动
	 * @author Administrator
	 *
	 */
	public class MessageThread implements Runnable {

		private MainFrame mainFrame; // 主界面的窗体
		private boolean needRunning = true; // 是否一直闪动
		private boolean isConversion = false; // 是否更换图片
		
		public MessageThread(MainFrame mainFrame) {
			this.mainFrame = mainFrame;
		}
		
		// 如果说，用户用鼠标点击了消图标，则可以不用闪动了
		public void setNeedRunning(boolean needRunning) {
			this.needRunning = needRunning;
		}
		
		public boolean getNeedRunning() {
			return this.needRunning;
		}
		@Override
		public void run() {
			while (needRunning) {
				try {
					Thread.sleep(Constants.MESSAGE_SLEEP); // 休眠1s
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!isConversion) {
					newsLbl.setIcon(CommonMethod.getImg(mainFrame, "news_over.png"));
					isConversion = true;
				} else {
					newsLbl.setIcon(CommonMethod.getImg(mainFrame, "news_def.png"));
					isConversion = false;
				}
			}
		}
	}
	/**
	 * 收到消息时播放音乐的线程
	 * @author Administrator
	 *
	 */
	public class MusicThread implements Runnable {

		private boolean needRunning = true;
		
		public void setNeedRunning(boolean needRunning) {
			this.needRunning = needRunning;
		}
		
		@Override
		public void run() {
			while (needRunning) {
				try {
					mm.loadSound();
					Thread.sleep(500); // 休眠1s
					mm.myStop();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 更新好友列表
	 * @param account
	 */
	public void updateFriendList(Account account) {
		mainFriendPanel.updateFriendList(account);
	}
	
	/**
	 * 更新用户资料
	 */
	public void updateUserData(Account a) {
		account.setHead(a.getHead());
		account.setAutograph(a.getAutograph());
		account.setNickname(a.getNickname());
		account.setAge(a.getAge());
		account.setArea(a.getArea());
		account.setCity(a.getCity());
		account.setGender(a.getGender());
		account.setMobile(a.getMobile());
		account.setProvice(a.getProvice());
		headLbl.setIcon(CommonMethod.getImg(this, a.getHead() + ".png"));
		nicknameLbl.setText(a.getNickname());
		String value = a.getAutograph();
		if (value == null || value.equals("")) {
			autographTxt.setText(" 编辑个性签名");
		} else {
			autographTxt.setText(value);
		}
	}
	
	/**
	 * 更新好友列表的数据
	 * @param account
	 */
	public void updateFriendData(Account account) {
		mainFriendPanel.updateFriendData(account);
	}
	
	public void selectPwdPrompt() {
		JOptionPane.showMessageDialog(this, "你已经修改密码", "修改密码提醒", JOptionPane.INFORMATION_MESSAGE);
		Message m = new Message(Message.LOGOUT_MSG, account, account, DateUtil.getDate(), "退出");
		client.sendMessage(m);
		this.dispose();
		new LoginFrame();
	}
}
