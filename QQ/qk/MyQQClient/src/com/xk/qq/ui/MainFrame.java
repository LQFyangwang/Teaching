package com.xk.qq.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.common.Constants;
import com.xk.common.DateUtil;
import com.xk.common.QQFont;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;
import com.xk.qq.client.Client;
import com.xk.qq.ui.listener.AutographKeyListener;
import com.xk.qq.ui.listener.BorderListener;
import com.xk.qq.ui.listener.ClearFocusListener;
import com.xk.qq.ui.listener.FrameDragListener;
import com.xk.qq.ui.listener.HeadUpdateDataListener;
import com.xk.qq.ui.listener.MainExitListener;
import com.xk.qq.ui.listener.MainLblListener;
import com.xk.qq.ui.listener.MainStatusListener;
import com.xk.qq.ui.listener.MainWindowListener;
import com.xk.qq.ui.listener.MinListener;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -5092582245932091611L;

	private JLabel bjLbl;
	private Account account;
	private boolean isSearchOpen = false;
	private boolean isUpdateData = false;
	private boolean isAlterSkin = false;
	
	private RepThread repThread;
	private Client client;
	
	private JTextField autographTxt;
	private JLabel nickName;
	private JLabel headLbl; 

	public BlackListPanel getBlackListPanel() {
		return blackListPanel;
	}

	public JLabel getBjLbl() {
		return bjLbl;
	}


	private FinedListPanel finedListPanel;
	
	private Map<Account,ChatFrame> chatFrames;
	
	private Map<Account,LookFrame> lookFrames;
	
	
	public Map<Account, LookFrame> getLookFrames() {
		return lookFrames;
	}

	public void setLookFrames(Map<Account, LookFrame> lookFrames) {
		this.lookFrames = lookFrames;
	}

	public Map<Account, ChatFrame> getChatFrames() {
		return chatFrames;
	}

	public void setChatFrames(Map<Account, ChatFrame> chatFrames) {
		this.chatFrames = chatFrames;
	}

	public MainFrame(Account account,Client client) {
		
		this.account = account;
		this.client = client;
		client.setMainframe(this);
		chatFrames = new HashMap<Account,ChatFrame>();
		lookFrames = new HashMap<Account,LookFrame>();
		account.setState(Constants.STATUS_ONLINE);
		Message m = new Message(Message.STATES_CHANGE,DateUtil.getDate(),account,account,"状态改变为"+account.getState());
		client.sendMessage(m);
		
		setSize(280, 600);
		setLocation(970, 80);
		getContentPane().setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq_icon.png"));
		backgound();
		top();
		center();
		sourth();
		FrameDragListener frame = new FrameDragListener(this);
		addMouseListener(frame);
		addMouseMotionListener(frame);
		setUndecorated(true);
		setVisible(true);
		addWindowListener(new MainWindowListener(account, client));
	}


	private void backgound() {
		ImageIcon backgound = new ImageIcon("images/skin_background0.png");
		bjLbl = new JLabel(backgound);
		bjLbl.setBounds(0, 0, 280, 600);
		add(bjLbl);
	}

	private void top() {
		ImageIcon tubiaoIcon = new ImageIcon("images/tubiao.png");
		JLabel tubiaoLbl = new JLabel(tubiaoIcon);
		tubiaoLbl.setBounds(10, 5, 35, 23);
		bjLbl.add(tubiaoLbl);

		ImageIcon minIcon = new ImageIcon("images/min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setName("min_main");
		minLbl.addMouseListener(new MinListener(this,account,client));
		minLbl.setBounds(225, 0, 30, 30);
		bjLbl.add(minLbl);

		ImageIcon exitIcon = new ImageIcon("images/exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setName("LoginMain_exit");
		exitLbl.setBounds(250, 0, 30, 30);
		exitLbl.addMouseListener(new MainExitListener(account,client));
		bjLbl.add(exitLbl);
		
		
		ImageIcon headIcon = new ImageIcon("images/"+ account.getHeadIcon() + ".png");
		headLbl = new JLabel(headIcon);
		headLbl.setBounds(10, 50, 70, 70);
		headLbl.addMouseListener(new HeadUpdateDataListener(headLbl,account,this,client));
		bjLbl.add(headLbl);

		nickName = new JLabel(account.getNickname());
		nickName.setBounds(90, 45, 90, 30);
		bjLbl.add(nickName);

		ImageIcon stateIcon = new ImageIcon("images/online.png");
		JLabel stateLbl = new JLabel(stateIcon);
		stateLbl.addMouseListener(new BorderListener(stateLbl));
		stateLbl.setBounds(145, 55, 13, 13);
		bjLbl.add(stateLbl);

		ImageIcon downIcon = new ImageIcon("images/down.png");
		JLabel downLbl = new JLabel(downIcon);
		downLbl.addMouseListener(new MainStatusListener(account,client,downLbl,stateLbl));
		downLbl.setBounds(160, 51, 20, 20);
		bjLbl.add(downLbl);
 
		ImageIcon gradeIcon = new ImageIcon("images/grade.png");
		JLabel gradeLbl = new JLabel(gradeIcon);
		gradeLbl.addMouseListener(new BorderListener(gradeLbl));
		gradeLbl.setBounds(177, 53, 66, 16);
		bjLbl.add(gradeLbl);
		
		
		JTextField oneTxt = new JTextField(); // 一开始让焦点在这里。。
		oneTxt.setBounds(160, 0, 0, 0);
		bjLbl.add(oneTxt);
		
		autographTxt = new JTextField(account.getAutograph());
		autographTxt.setBounds(90, 77, 120, 20);
		autographTxt.setOpaque(false);
		autographTxt.setBorder(null);
		autographTxt.setName("autograph_Txt");
		AutographKeyListener aghKey = new AutographKeyListener(autographTxt,account);
		autographTxt.addKeyListener(aghKey);
		autographTxt.addFocusListener(new ClearFocusListener(autographTxt,account));
		bjLbl.add(autographTxt);

		ImageIcon spaceIcon = new ImageIcon("images/space.png");
		JLabel spaceLbl = new JLabel(spaceIcon);
		spaceLbl.addMouseListener(new BorderListener(spaceLbl));
		spaceLbl.setBounds(90, 100, 20, 20);
		bjLbl.add(spaceLbl);

		ImageIcon newsIcon = new ImageIcon("images/news.png");
		JLabel newsLbl = new JLabel(newsIcon);
		newsLbl.addMouseListener(new BorderListener(newsLbl));
		newsLbl.setBounds(110, 100, 20, 20);
		bjLbl.add(newsLbl);

		ImageIcon boxIcon = new ImageIcon("images/box.png");
		JLabel boxLbl = new JLabel(boxIcon);
		boxLbl.addMouseListener(new BorderListener(boxLbl));
		boxLbl.setBounds(133, 100, 20, 20);
		bjLbl.add(boxLbl);

		ImageIcon shoppingIcon = new ImageIcon("images/shopping.png");
		JLabel shoppingLbl = new JLabel(shoppingIcon);
		shoppingLbl.addMouseListener(new BorderListener(shoppingLbl));
		shoppingLbl.setBounds(154, 100, 20, 20);
		bjLbl.add(shoppingLbl);

		ImageIcon skinIcon = new ImageIcon("images/skin.png");
		JLabel skinLbl = new JLabel(skinIcon);
		skinLbl.addMouseListener(new AlterSkinListener(skinLbl));
		skinLbl.setBounds(235, 100, 20, 20);
		bjLbl.add(skinLbl);

		ImageIcon meaIcon = new ImageIcon("images/message.png");
		JLabel meaLbl = new JLabel(meaIcon);
		meaLbl.addMouseListener(new BorderListener(meaLbl));
		meaLbl.setBounds(255, 100, 20, 20);
		bjLbl.add(meaLbl);

		JTextField searchTxt = new JTextField("搜索：联系人、多人聊天、群");
		searchTxt.setBounds(0, 130, 280, 25);
		searchTxt.setName("search_Txt");
		searchTxt.addFocusListener(new ClearFocusListener(searchTxt));
		searchTxt.setOpaque(false);
		searchTxt.setFont(QQFont.myFont(13));
		searchTxt.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0.5f), 1,true));
		bjLbl.add(searchTxt);

		ImageIcon searchIcon = new ImageIcon("images/search.png");
		JLabel searchLbl = new JLabel(searchIcon);
		searchLbl.setBounds(250, 133, 20, 20);
		bjLbl.add(searchLbl);

	}
	
	private BlackListPanel blackListPanel;
	private void center(){
		UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
		JTabbedPane tabed = new JTabbedPane();
		tabed.setBounds(5,154,272,385);
		tabed.setOpaque(false);
		tabed.setBorder(null);
		AccountDAO accountDAO = new AccountDAOImpl();
		finedListPanel = new FinedListPanel(this,accountDAO.queryFroemds(account.getNumber()),client,account);
		blackListPanel = new BlackListPanel(this,accountDAO.BlackList(account.getNumber()),client ,account);
		tabed.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); // 设置在一次运行中不能放入所有的选项卡时，选项卡窗格使用的对选项卡进行布局安排的策略。
		tabed.addTab("",new ImageIcon("images/fiend.png"),finedListPanel,"");
		tabed.addTab("",new ImageIcon("images/group.png"),blackListPanel,"");
		tabed.addTab("",new ImageIcon("images/last.png"),new JLabel("C放的内容"),"");
		tabed.addTab("",new ImageIcon("images/mobile.png"),new JLabel("D放的内容"),"");
		bjLbl.add(tabed);
	}
	
	private JLabel voiceLbl; // 查找好友消息闪动
	public JLabel getVoiceLbl() {
		return voiceLbl;
	}

	private void sourth(){
		JLabel menuLbl = new JLabel(new ImageIcon("images/menu.png"));
		menuLbl.setBounds(5,575,20,20);
		menuLbl.addMouseListener(new BorderListener(menuLbl));
		bjLbl.add(menuLbl);
		
		JLabel setLbl = new JLabel(new ImageIcon("images/set.png"));
		setLbl.setBounds(35, 575, 20, 20);
		setLbl.addMouseListener(new BorderListener(setLbl));
		bjLbl.add(setLbl);
		
		voiceLbl = new JLabel(new ImageIcon("images/voice_def.png"));
		voiceLbl.setBounds(65, 575, 20, 20);
		voiceLbl.setName("repLbl");
		voiceLbl.addMouseListener(new MainLblListener(client,this));
		voiceLbl.addMouseListener(new BorderListener(voiceLbl));
		bjLbl.add(voiceLbl);
		
		JLabel fileLbl = new JLabel(new ImageIcon("images/file.png"));
		fileLbl.setBounds(95, 575, 20, 20);
		fileLbl.addMouseListener(new BorderListener(fileLbl));
		bjLbl.add(fileLbl);
		
		JLabel collectLbl = new JLabel(new ImageIcon("images/collect.png"));
		collectLbl.setBounds(120, 575, 20, 20);
		collectLbl.addMouseListener(new BorderListener(collectLbl));
		bjLbl.add(collectLbl);
		
		JLabel findLbl = new JLabel(new ImageIcon("images/find_def.png"));
		findLbl.setBounds(150, 577, 50, 20);
		findLbl.addMouseListener(new FindMouseListener(findLbl));
		
		bjLbl.add(findLbl);
		
		JLabel use = new JLabel("应用宝");
		use.setFont(QQFont.myFont(13));
		use.setBounds(220,570,40,30);
		bjLbl.add(use);
		JLabel useLbl = new JLabel(new ImageIcon("images/use.png"));
		useLbl.setBounds(260,575,20,20);
		useLbl.addMouseListener(new BorderListener(useLbl));
		bjLbl.add(useLbl);
		
		JLabel petLbl = new JLabel(new ImageIcon("images/pet.png"));
		petLbl.setBounds(5,550,20,20);
		petLbl.addMouseListener(new BorderListener(petLbl));
		bjLbl.add(petLbl);
		
		JLabel tvLbl = new JLabel(new ImageIcon("images/tv.png"));
		tvLbl.setBounds(35,550,20,20);
		tvLbl.addMouseListener(new BorderListener(tvLbl));
		bjLbl.add(tvLbl);
		
		JLabel bodyLbl = new JLabel(new ImageIcon("images/body.png"));
		bodyLbl.setBounds(65,550,20,20);
		bodyLbl.addMouseListener(new BorderListener(bodyLbl));
		bjLbl.add(bodyLbl);
	}
	
	
	public boolean isUpdateData() {
		return isUpdateData;
	}

	public void setUpdateData(boolean isUpdateData) {
		this.isUpdateData = isUpdateData;
	}

	public void setSearchOpen(boolean isSearchOpen) {
		this.isSearchOpen = isSearchOpen;
	}
	class FindMouseListener extends MouseAdapter {
		private JLabel lbl;
		public FindMouseListener(JLabel lbl){
			this.lbl = lbl;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!isSearchOpen) {
				new FriendSearchFrame(MainFrame.this,account,client);
				isSearchOpen = true;
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			lbl.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0.5f), 1,true));
			lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			lbl.setBorder(null);
			lbl.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	private Message message;
	public Message getMessage(){
		return message;
	}
	
	public void receiveRepThread(Message message){
		this.message = message;
		repThread = new RepThread();
		new Thread(repThread).start();
	}
	
	public RepThread getRepThread(){
		return repThread;
	}
	public class RepThread implements Runnable {
		boolean needRunning = true;
		boolean isRemove = false;
		public void setNeedRunning(boolean needRunning){
			this.needRunning = needRunning;
		}
		
		@Override
		public void run() {
			while(needRunning){
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(!isRemove){
					voiceLbl.setIcon(new ImageIcon("images/voice_over.png"));
					isRemove = true;
				}else{
					voiceLbl.setIcon(new ImageIcon("images/voice_def.png"));
					isRemove = false;
				}
			}
		}
		
		
	}
	public void updateFriendList(Account account) {
		finedListPanel.updateFriendList(account);
	}
	
	public void updateDataMain(Account account){
		nickName.setText(account.getNickname());
		autographTxt.setText(account.getAutograph());
		headLbl.setIcon(new ImageIcon("images/"+ account.getHeadIcon()+ ".png"));
		
	}
	
	public void updateFriendStates(Account account){
		finedListPanel.updateFriendState(account);
	}
	
	
	public boolean isAlterSkin() {
		return isAlterSkin;
	}

	public void setAlterSkin(boolean isAlterSkin) {
		this.isAlterSkin = isAlterSkin;
	}

	class AlterSkinListener extends  MouseAdapter {

		private JLabel lbl;
		
		public AlterSkinListener(JLabel lbl){
			this.lbl = lbl;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!isAlterSkin){
				new SkinFrame(MainFrame.this);
				isAlterSkin = true;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			lbl.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0.3f)));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			lbl.setBorder(null);
		}
	}
	
	public void deleteFriend(Account account){
		finedListPanel.delteFriedndList(account);
	}
	
	public void updateBlackList(Account account) {
		blackListPanel.updateFriendList(account);
	}
	
	public void deleteBlckList(Account account){
		blackListPanel.delteFriedndList(account);
	}
}
