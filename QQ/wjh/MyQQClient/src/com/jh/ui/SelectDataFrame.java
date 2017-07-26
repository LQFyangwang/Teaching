package com.jh.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jh.bean.Account;
import com.jh.common.Constants;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;
import com.jh.ui.listener.DataFrameListener;
import com.jh.ui.listener.MinListener;

/**
 * 修改个人资料窗体
 * @author Administrator
 *
 */
public class SelectDataFrame extends JFrame implements ItemListener,KeyListener {

	private static final long serialVersionUID = -3112493938229583385L;

	private Account account;
	private MainFrame mainFrame;
	
	private static JButton saveBtn;
	public static JButton headBtn;
	public static String headName;
	private JLabel autographLbl;

	public SelectDataFrame(MainFrame mainFrame, Account account) {
		this.mainFrame = mainFrame;
		Constants.select = false;
		this.account = account;
		headName = account.getHead();
		Constants.saveData = false; // 每次打开个人资料窗体就表示默认不能修改资料
		Constants.saveWin = false;
		LoginFrameCommon.setStyle(this);
		initTop();
		initCenter();
		setBounds(300, 100, 450, 600);
	}
	
	private void initTop() {
		JLabel bgLbl = new JLabel(CommonMethod.getImg(this, "data_background" + CommonMethod.myRandom() + ".png"));
		bgLbl.setLayout(null);
		bgLbl.setBounds(0, 0, 450, 220);
		Icon minIcon = CommonMethod.getImg(this, "min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setBounds(390, 0, 30, 30);
		minLbl.setToolTipText("最小化");
		minLbl.setName("min");
		minLbl.addMouseListener(new MinListener(this));
		bgLbl.add(minLbl);
		Icon exitIcon = CommonMethod.getImg(this, "exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setBounds(420, 0, 30, 30);
		exitLbl.setToolTipText("关闭");
		exitLbl.setName("dispose");
		exitLbl.addMouseListener(new MinListener(this));
		bgLbl.add(exitLbl);
		
		JPanel lucencyPanel = new JPanel();
		lucencyPanel.setBounds(0, 150, 450, 100);
		lucencyPanel.setBackground(new Color(0, 0, 0, 0.2f));
		bgLbl.add(lucencyPanel);
		// 头像
		headBtn = new JButton(CommonMethod.getImg(this, account.getHead() + ".png"));
		headBtn.setBounds(10, 120, 80, 80);
		headBtn.setActionCommand("head");
		headBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		headBtn.addActionListener(new DataFrameListener(this, mainFrame));
		bgLbl.add(headBtn);
		// 昵称
		JLabel nicknameLbl = new JLabel(account.getNickname());
		nicknameLbl.setBounds(100, 130, 70, 20);
		bgLbl.add(nicknameLbl);
		// 账号
		JLabel numberLbl = new JLabel(account.getNumber());
		numberLbl.setBounds(180, 130, 70, 20);
		bgLbl.add(numberLbl);
		// 等級
		JLabel gradeLbl = new JLabel(CommonMethod.getImg(this, "grade.png"));
		gradeLbl.setBounds(100, 145, 82, 30);
		bgLbl.add(gradeLbl);
		// 个性签名
		autographLbl = new JLabel(account.getAutograph());
		autographLbl.setBounds(100, 170, 200, 20);
		bgLbl.add(autographLbl);
		add(bgLbl);
		
		JPanel dataPanel = new JPanel();
		dataPanel.setBounds(0, 220, 450, 30);
		dataPanel.setBackground(new Color(176, 196, 222));
		dataPanel.setLayout(null);
		JLabel dataLbl = new JLabel("个人资料:");
		dataLbl.setBounds(20, 5, 100, 20);
		dataLbl.setFont(new Font("华文行楷", Font.BOLD, 15));
		dataLbl.setForeground(Color.WHITE);
		dataPanel.add(dataLbl);
		add(dataPanel);
		
		JLabel btnLbl = new JLabel();
		btnLbl.setBounds(-1, 249, 452, 50);
		btnLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// 保存按钮
		saveBtn = new JButton("保存");
		saveBtn.setBounds(300, 10, 60, 30);
		saveBtn.setEnabled(false);
		saveBtn.setActionCommand("save");
		saveBtn.addActionListener(new DataFrameListener(this, mainFrame));
		btnLbl.add(saveBtn);
		// 关闭按钮
		JButton exitBtn = new JButton("关闭");
		exitBtn.setBounds(370, 10, 60, 30);
		exitBtn.setActionCommand("exit");
		exitBtn.addActionListener(new DataFrameListener(this, mainFrame));
		btnLbl.add(exitBtn);
		add(btnLbl);
	}
	
	private JTextField nicknameTxt; // 昵称
	private JTextField ageTxt; // 年龄
	private JTextField mobileTxt; // 手机
	private JTextField proviceTxt; // 省份
	private JTextField cityTxt; // 城市
	private JTextField areaTxt; // 市区
	private JTextField autographTxt; // 个性签名
	private JComboBox<String> genderBox;
	private String gender = "男"; // 性别
	
	private void initCenter() {
		// 昵称
		JLabel nicknameLbl = new JLabel("昵   称:");
		nicknameLbl.setBounds(20, 320, 60, 20);
		add(nicknameLbl);
		nicknameTxt = new JTextField();
		nicknameTxt.setBounds(80, 315, 130, 30);
		nicknameTxt.setText(account.getNickname());
		nicknameTxt.addKeyListener(this);
		add(nicknameTxt);
		// 性别
		JLabel genderLbl = new JLabel("性   别:");
		genderLbl.setBounds(230, 320, 60, 20);
		add(genderLbl);
		genderBox = new JComboBox<String>();
		String value = account.getGender();
		if (value.equals("男")) {
			genderBox.addItem("男");
			genderBox.addItem("女");
		} else {
			genderBox.addItem("女");
			genderBox.addItem("男");
		}
		genderBox.setBounds(290, 315, 130, 30);
		genderBox.setName("gender");
		genderBox.addItemListener(this);
		add(genderBox);
		// 年龄
		JLabel ageLbl = new JLabel("年   龄:");
		ageLbl.setBounds(230, 360, 60, 20);
		add(ageLbl);
		ageTxt = new JTextField();
		ageTxt.setBounds(290, 355, 130, 30);
		ageTxt.setText(String.valueOf(account.getAge()));
		ageTxt.addKeyListener(this);
		add(ageTxt);
		// 手机
		JLabel mobileLbl = new JLabel("手   机:");
		mobileLbl.setBounds(20, 360, 60, 20);
		add(mobileLbl);
		mobileTxt = new JTextField();
		mobileTxt.setBounds(80, 355, 130, 30);
		mobileTxt.setText(account.getMobile());
		mobileTxt.addKeyListener(this);
		add(mobileTxt);
		// 省份
		JLabel proviceLbl = new JLabel("省   份:");
		proviceLbl.setBounds(20, 400, 60, 20);
		add(proviceLbl);
		proviceTxt = new JTextField();
		proviceTxt.setBounds(80, 395, 130, 30);
		proviceTxt.setText(account.getProvice());
		proviceTxt.addKeyListener(this);
		add(proviceTxt);
		// 城市
		JLabel cityLbl = new JLabel("城   市:");
		cityLbl.setBounds(230, 400, 60, 20);
		add(cityLbl);
		cityTxt = new JTextField();
		cityTxt.setBounds(290, 395, 130, 30);
		cityTxt.setText(account.getCity());
		cityTxt.addKeyListener(this);
		add(cityTxt);
		// 市区
		JLabel areaLbl = new JLabel("市   区:");
		areaLbl.setBounds(20, 440, 60, 20);
		add(areaLbl);
		areaTxt = new JTextField();
		areaTxt.setBounds(80, 435, 130, 30);
		areaTxt.setText(account.getArea());
		add(areaTxt);
		// 个性签名
		JLabel autographLbl = new JLabel("个性签名：");
		autographLbl.setBounds(20, 480, 80, 20);
		add(autographLbl);
		autographTxt = new JTextField();
		autographTxt.setBounds(20, 510, 400, 60);
		autographTxt.setText(account.getAutograph());
		autographTxt.addKeyListener(this);
		add(autographTxt);
	}
	
	public Account saveAccount() {
		Account a = new Account();
		a.setNumber(account.getNumber());
		a.setNickname(nicknameTxt.getText());
		a.setMobile(mobileTxt.getText());
		a.setProvice(proviceTxt.getText());
		a.setCity(cityTxt.getText());
		a.setArea(areaTxt.getText());
		a.setAutograph(autographTxt.getText());
		a.setAge(Integer.valueOf(ageTxt.getText()));
		a.setHead(headName);
		a.setGender(gender);
		a.setSkin(account.getSkin());
		a.setStatus(CommonMethod.IMG_URL);
		return a;
	}
	
	public static void setSaveBtn() {
		Constants.saveData = true;
		saveBtn.setEnabled(true);
	}
	
	public void updateAutograph(Account account) {
		autographLbl.setText(account.getAutograph());
		autographTxt.setText(account.getAutograph());
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (genderBox.getSelectedItem() != null) {
			setSaveBtn();
			gender = (String) genderBox.getSelectedItem(); // 获取到选中的项
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		setSaveBtn();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
