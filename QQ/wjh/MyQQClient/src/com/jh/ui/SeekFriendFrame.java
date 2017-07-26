package com.jh.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jh.bean.Account;
import com.jh.client.Client;
import com.jh.common.Constants;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;
import com.jh.ui.common.TableVector;
import com.jh.ui.listener.MinListener;
import com.jh.ui.listener.SeekFriendTableMouseListener;
import com.jh.ui.listener.TxtFocusListener;

/**
 * 查找好友的窗体
 * @author Administrator
 *
 */
public class SeekFriendFrame extends JFrame implements ActionListener,KeyListener {

	private static final long serialVersionUID = -5126310786420996711L;
	
	private Account account; // 已经登入的用户账号信息
	private Client client;
	private MainFrame mainFrame;
	
	private JLabel resultLbl; // 好友推荐
	private JTextField seekTxt; // 查找好友的输入框
	private AccountDAO accountDAO; // 用来执行查找数据库语句
	private TableVector toVector; // 表格
	private Vector<String> columnNames; // 表格的列名
	private DefaultTableModel dataModel; // 表格的值
	
	public SeekFriendFrame(MainFrame mainFrame, Account account, Client client) {
		this.account = account;
		this.client = client;
		this.mainFrame = mainFrame;
		Constants.isSeekFirendFrameOpen = false;
		LoginFrameCommon.setStyle(this);
		accountDAO = new AccountDAOImpl();
		initWidgets();
		initTable();
		setBounds(300, 100, 620, 460);
	}

	private void initWidgets() {
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBounds(0, 0, 620, 40);
		topPanel.setBackground(new Color(30, 144, 255));
		Icon qqIconIcon = CommonMethod.getImg(this, "seek_icon_bottom.png");
		JLabel qqIconLbl = new JLabel("查找", qqIconIcon, JLabel.LEFT);
		qqIconLbl.setBounds(5, 5, 200, 30);
		topPanel.add(qqIconLbl);
		Icon minIcon = CommonMethod.getImg(this, "min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setBounds(560, 0, 30, 30);
		minLbl.setToolTipText("最小化");
		minLbl.setName("min");
		minLbl.addMouseListener(new MinListener(this));
		topPanel.add(minLbl);
		Icon exitIcon = CommonMethod.getImg(this, "exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setBounds(590, 0, 30, 30);
		exitLbl.setToolTipText("关闭");
		exitLbl.setName("dispose");
		exitLbl.addMouseListener(new MinListener(this));
		topPanel.add(exitLbl);
		add(topPanel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setBounds(0, 40, 620, 80);
		centerPanel.setBackground(new Color(175, 238, 238));
		// 查找好友的输入框
		seekTxt = new JTextField("输入QQ号码/昵称查找好友");
		seekTxt.setBounds(20, 20, 450, 40);
		seekTxt.setName("seek");
		seekTxt.addKeyListener(this);
		seekTxt.addFocusListener(new TxtFocusListener(mainFrame, seekTxt, null));
		centerPanel.add(seekTxt);
		// 查找按钮
		JButton seekBtn = new JButton("查找");
		seekBtn.setBounds(500, 20, 100, 40);
		seekBtn.setActionCommand("seek");
		seekBtn.addActionListener(this);
		centerPanel.add(seekBtn);
		add(centerPanel);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(null);
		resultPanel.setBounds(0, 120, 620, 30);
		resultPanel.setBackground(new Color(176, 196, 222));
		resultLbl = new JLabel("好友推荐:");
		resultLbl.setBounds(10, 5, 80, 20);
		resultLbl.setFont(new Font("华文行楷", Font.BOLD, 14));
		resultLbl.setForeground(Color.white);
		resultPanel.add(resultLbl);
		add(resultPanel);
		
	}

	private void initTable() {
		JTable table = new JTable();
		columnNames = new Vector<String>();
		columnNames.add("账号");
		columnNames.add("昵称");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("手机号码");
		toVector = new TableVector();
		dataModel = new DefaultTableModel();
		dataModel.setDataVector(toVector.toVector(accountDAO.queryAll(account)), columnNames);
		table.setModel(dataModel);
		table.addMouseListener(new SeekFriendTableMouseListener(this, account, client));
		JScrollPane scroll = new JScrollPane(table); // 将好友数据添加到滚动面板中
		scroll.setBounds(10, 150, 600, 300);
		add(scroll);
	}

	// 用户按查找按钮时调用
	@Override
	public void actionPerformed(ActionEvent e) {
		seekMethod();
	}
	
	public void seekMethod() {
		String value = seekTxt.getText();
		List<Account> accounts = accountDAO.querySingle(value, value); // 根据用户输入的数据去查询数据库
		dataModel.setDataVector(toVector.toVector(accounts), columnNames); // 替换table中的数据
		resultLbl.setText("查找结果:");
	}

	// 用户按回车键时调用
	@Override
	public void keyTyped(KeyEvent e) {
		seekMethod();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
