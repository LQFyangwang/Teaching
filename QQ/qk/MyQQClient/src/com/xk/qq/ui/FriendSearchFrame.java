package com.xk.qq.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.xk.bean.Account;
import com.xk.common.QQFont;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;
import com.xk.qq.client.Client;
import com.xk.qq.ui.common.ListTableVector;
import com.xk.qq.ui.listener.ClearFocusListener;
import com.xk.qq.ui.listener.ExitListener;
import com.xk.qq.ui.listener.FiendSearchTableListener;
import com.xk.qq.ui.listener.FrameDragListener;
import com.xk.qq.ui.listener.MinListener;

public class FriendSearchFrame extends JFrame {

	private static final long serialVersionUID = 1237061145309178481L;
	
	private MainFrame mainFrame;
	private JTextField ctnTxt;
	private Vector<String> titleName;
	private JLabel likelyLbl;
	private Account account;
	private Client client;
	
	private ListTableVector toVector;
	private AccountDAO accountDAO;
	private DefaultTableModel dataModel;
	private Map <Account,LookFrame> lookFrames; 
	

	public Map<Account, LookFrame> getLookFrames() {
		return lookFrames;
	}

	public void setLookFrames(Map<Account, LookFrame> lookFrames) {
		this.lookFrames = lookFrames;
	}

	public FriendSearchFrame(MainFrame mainFrame,Account account,Client client){
		this.account = account;
		this.mainFrame = mainFrame;
		this.client = client;
		lookFrames = new HashMap<Account,LookFrame>();
		setSize(500,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		top();
		center();
		setUndecorated(true);
		FrameDragListener darg = new FrameDragListener(this);
		addMouseListener(darg);
		addMouseMotionListener(darg);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq_icon.png"));
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}
	
	private void top(){
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30,144,255));
		panel.setLayout(null);
		panel.setBounds(0, 0, 500, 70);
		
		JLabel qqIcon = new JLabel(new ImageIcon("images/tubiao.png"));
		qqIcon.setBounds(10, 5, 35, 23);
		panel.add(qqIcon);
		
		JLabel minLbl = new JLabel(new ImageIcon("images/min_def.png"));
		minLbl.setName("Login_min");
		minLbl.addMouseListener(new MinListener(this));
		minLbl.setBounds(445, 0, 30, 30);
		panel.add(minLbl);

		JLabel exitLbl = new JLabel(new ImageIcon("images/exit_def.png"));
		exitLbl.setBounds(470, 0, 30, 30);
		exitLbl.setName("Friend_exit");
		exitLbl.addMouseListener(new ExitListener(this,mainFrame));
		panel.add(exitLbl);
		
		JLabel searchLbl = new JLabel("查找好友");
		searchLbl.setBounds(225,30,80,30);
		searchLbl.setFont(QQFont.myFont(18));
		searchLbl.setForeground(new Color(248,248,255));
		panel.add(searchLbl);
		add(panel);
		
		JTextField oneTxt = new JTextField();	// 一开始让焦点在这里。。。。。
		oneTxt.setBounds(20, 85, 0, 0);
		add(oneTxt);
		
		ctnTxt = new JTextField("请输入QQ号码/手机号码/昵称");
		ctnTxt.setForeground(new Color(192,192,192));
		ctnTxt.setName("ctn_Txt");
		ctnTxt.addKeyListener(new SearchKeyListener());
		ctnTxt.addFocusListener(new ClearFocusListener(ctnTxt));
		ctnTxt.setBorder(BorderFactory.createLineBorder(new Color(30,144,255)));
		ctnTxt.setBounds(20, 90, 240, 30);
		add(ctnTxt);
		
		JCheckBox onlineBox = new JCheckBox("在线",false);
		onlineBox.setFont(QQFont.myFont(13));
		onlineBox.setBounds(265,85,80,30);
		add(onlineBox);
		
		JCheckBox overBox = new JCheckBox("离线",false);
		overBox.setFont(QQFont.myFont(13));
		overBox.setBounds(265,110,80,30);
		add(overBox);
		
		JLabel chazaoLbl = new JLabel(new ImageIcon("images/chazao.png"));
		chazaoLbl.setBounds(330,95,113,28);
		chazaoLbl.addMouseListener(new SearchMouseListener(chazaoLbl));
		add(chazaoLbl);
		
		JComboBox<String> addresBox = new JComboBox<String>();
		addresBox.setBounds(20,130,165,20);
		addresBox.setFont(QQFont.myFont(12));
		addresBox.addItem("所在地:中国、江西、赣州");
		addresBox.addItem("中国、江西、南昌");
		addresBox.addItem("中国、广东、深圳");
		add(addresBox);
		
		JComboBox<String> genderBox = new JComboBox<String>();
		genderBox.setForeground(new Color(192,192,192));
		genderBox.setBounds(200,130,50,20);
		genderBox.setFont(QQFont.myFont(12));
		genderBox.addItem("性别");
		genderBox.addItem("男");
		genderBox.addItem("女");
		add(genderBox);
		
		likelyLbl = new JLabel("好友推荐 :");
		likelyLbl.setBounds(20,160,80,30);
		likelyLbl.setFont(QQFont.myFont(16));
		likelyLbl.setForeground(new Color(130,150,130));
		add(likelyLbl);
		
	}
	
	private void center() {
		JTable table = new JTable();
		titleName = new Vector<String>();
		titleName.add("账号");
		titleName.add("昵称");
		titleName.add("性别");
		titleName.add("年龄");
		titleName.add("地址");
		titleName.add("状态");
		accountDAO = new AccountDAOImpl();
		toVector = new ListTableVector();
		dataModel = new DefaultTableModel();
		dataModel.setDataVector(toVector.toVector(accountDAO.queryAll(account.getNumber())), titleName);
		table.setModel(dataModel);
		table.addMouseListener(new FiendSearchTableListener(account,client,this));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20,200,460,280);
		add(scrollPane);
		
	} 
	
	public void seekFriend(){
		String searchStr = ctnTxt.getText();
		List<Account> accounts = accountDAO.queryAll(searchStr, searchStr);
		dataModel.setDataVector(toVector.toVector(accounts), titleName);
		likelyLbl.setText("查找结果 :");
	} 
	
	class SearchMouseListener extends MouseAdapter {
		private JLabel lbl;
		public SearchMouseListener(JLabel lbl) {
			this.lbl = lbl;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			seekFriend();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			lbl.setCursor(Cursor.getDefaultCursor());
		} 
		
	}
	class SearchKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_ENTER){
				seekFriend();
			}
		}
		
		
	}

}
