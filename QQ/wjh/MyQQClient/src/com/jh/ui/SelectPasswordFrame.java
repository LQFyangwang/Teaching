package com.jh.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.Constants;
import com.jh.common.DateUtil;
import com.jh.common.EncryptUtil;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.ui.common.CheckCode;
import com.jh.ui.common.CheckCodeGenerator;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;
import com.jh.ui.listener.MinListener;
import com.jh.ui.listener.SelectMouseListener;

public class SelectPasswordFrame extends JFrame implements FocusListener,ActionListener {

	private static final long serialVersionUID = -2709000587484577189L;
	
	private Account account;
	private Client client;
	
	private boolean isOk = false;
	private boolean one = false;
	private boolean two = false;
	private boolean three = false;
	private boolean four = false;
	
	private JPasswordField presentTxt;
	private JLabel passPrePwdLbl;
	private JPasswordField newTxt;
	private JLabel passNewLbl;
	private JPasswordField affrimTxt;
	private JLabel passAffrimLbl;
	private JTextField codeTxt;
	private JLabel passCodeLbl;
	
	public JLabel verLbl;
	public String checkCode;
	private String newValue;
	
	public SelectPasswordFrame(MainFrame mainFrame) {
		this.account = mainFrame.getAccount();
		this.client = mainFrame.getClient();
		Constants.isSelectPasswordFrameOpen = false;
		LoginFrameCommon.setStyle(this);
		Constants.isAlterHeadFrameOpen = false;
		initWidgets();
		setBounds(300, 100, 500, 420);
	}

	private void initWidgets() {
		// 顶部的图标面板
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBounds(0, 0, 500, 30);
		topPanel.setBackground(Color.BLUE);
		Icon qqIconIcon = CommonMethod.getImg(this, "main_qq_icon.png");
		JLabel qqIconLbl = new JLabel(qqIconIcon);
		qqIconLbl.setBounds(0, 0, 60, 30);
		topPanel.add(qqIconLbl);
		Icon minIcon = CommonMethod.getImg(this, "min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setBounds(440, 0, 30, 30);
		minLbl.setToolTipText("最小化");
		minLbl.setName("min");
		minLbl.addMouseListener(new MinListener(this));
		topPanel.add(minLbl);
		Icon exitIcon = CommonMethod.getImg(this, "exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setBounds(470, 0, 30, 30);
		exitLbl.setToolTipText("关闭");
		exitLbl.setName("dispose");
		exitLbl.addMouseListener(new MinListener(this));
		topPanel.add(exitLbl);
		add(topPanel);

		// 当前密码
		JLabel presentPwd = new JLabel("*当前密码:");
		presentPwd.setBounds(60, 70, 80, 20);
		add(presentPwd);
		presentTxt = new JPasswordField();
		presentTxt.setBounds(140, 65, 200, 30);
		presentTxt.setName("present");
		presentTxt.addFocusListener(this);
		add(presentTxt);
		passPrePwdLbl = new JLabel("输入旧密码");
		passPrePwdLbl.setBounds(360, 70, 150, 20);
		passPrePwdLbl.setForeground(Color.RED);
		add(passPrePwdLbl);

		// 新密码
		JLabel newPwd = new JLabel("*新密码:");
		newPwd.setBounds(60, 120, 80, 20);
		add(newPwd);
		newTxt = new JPasswordField();
		newTxt.setBounds(140, 115, 200, 30);
		newTxt.setName("new");
		newTxt.addFocusListener(this);
		add(newTxt);
		passNewLbl = new JLabel("输入新密码");
		passNewLbl.setBounds(360, 120, 150, 20);
		passNewLbl.setForeground(Color.RED);
		add(passNewLbl);

		// 确认密码
		JLabel affrimPwd = new JLabel("*确认密码:");
		affrimPwd.setBounds(60, 170, 80, 20);
		add(affrimPwd);
		affrimTxt = new JPasswordField();
		affrimTxt.setBounds(140, 165, 200, 30);
		affrimTxt.setName("affrim");
		affrimTxt.addFocusListener(this);
		add(affrimTxt);
		passAffrimLbl = new JLabel("确认新密码");
		passAffrimLbl.setBounds(360, 170, 150, 20);
		passAffrimLbl.setForeground(Color.RED);
		add(passAffrimLbl);

		// 验证码
		JLabel codePwd = new JLabel("验证码:");
		codePwd.setBounds(60, 220, 80, 20);
		add(codePwd);
		codeTxt = new JTextField();
		codeTxt.setBounds(140, 215, 200, 30);
		codeTxt.setName("code");
		codeTxt.addFocusListener(this);
		add(codeTxt);
		passCodeLbl = new JLabel("请输入验证码");
		passCodeLbl.setBounds(360, 220, 150, 20);
		passCodeLbl.setForeground(Color.RED);
		add(passCodeLbl);
		JLabel hintLbl = new JLabel("输入图片中的字符，不区分大小写");
		hintLbl.setBounds(150, 240, 300, 20);
		hintLbl.setForeground(Color.LIGHT_GRAY);
		add(hintLbl);
		JLabel verificationLbl = new JLabel("验证码：");
		verificationLbl.setBounds(150, 270, 80, 20);
		add(verificationLbl);
		CheckCode cc = CheckCodeGenerator.getCheckCode();
		verLbl = new JLabel(new ImageIcon(cc.getBuffImg()));
		checkCode = cc.getCheckCode();
		verLbl.setBounds(150, 290, 50, 30);
		add(verLbl);
		JLabel exchangeLbl = new JLabel("看不清，换一张");
		exchangeLbl.setBounds(200, 300, 200, 20);
		exchangeLbl.setName("exchange");
		exchangeLbl.addMouseListener(new SelectMouseListener(this, exchangeLbl));
		add(exchangeLbl);

		// 确定按钮
		JButton ensureBtn = new JButton("确定");
		ensureBtn.setBounds(180, 350, 100, 40);
		ensureBtn.setActionCommand("ensure");
		ensureBtn.addActionListener(this);
		add(ensureBtn);
	}
	
	private void isOk(JLabel lbl) {
		lbl.setForeground(Color.GREEN);
		lbl.setText("正确");
		if (one && two && three && four) {
			isOk = true;
		}
	}
	
	private void setColor(JLabel label) {
		label.setForeground(Color.RED);
	}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField txt = (JTextField) e.getSource();
		String name = txt.getName();
		if (name.equals("present")) {
			String pwd = EncryptUtil.encrypt(String.valueOf(presentTxt.getPassword())); // 先把用户输入的加密后再去比较
			if (pwd.equals(account.getPwd())) {
				one = true;
				isOk(passPrePwdLbl);
			} else {
				setColor(passPrePwdLbl);
				passPrePwdLbl.setText("旧密码不正确");
			}
		} else if (name.equals("new")) {
			newValue = String.valueOf(newTxt.getPassword());
			String encryptPwd = EncryptUtil.encrypt(newValue);
			if (newValue.equals("") || newValue == null) {
				setColor(passNewLbl);
				passNewLbl.setText("输入新密码:");
			} else if (encryptPwd.equals(account.getPwd())) {
				setColor(passNewLbl);
				passNewLbl.setText("不能和旧密码相同");
			} else {
				two = true;
				isOk(passNewLbl);
			}
		} else if (name.equals("affrim")) {
			String pwdValue = String.valueOf(affrimTxt.getPassword());
			if (pwdValue.equals("") || pwdValue == null || !pwdValue.equals(newValue)) {
				setColor(passAffrimLbl);
				passAffrimLbl.setText("两次密码不一致");
			} else {
				three = true;
				isOk(passAffrimLbl);
			}
		} else if (name.equals("code")) {
			String codeValue = codeTxt.getText();
			if (!codeValue.equalsIgnoreCase(checkCode)) {
				setColor(passCodeLbl);
				passCodeLbl.setText("验证码错误");
			} else {
				four = true;
				isOk(passCodeLbl);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isOk) {
			AccountDAO accountDAO = new AccountDAOImpl();
			account.setPwd(newValue);
			accountDAO.updatePwd(account);
			JOptionPane.showMessageDialog(this, "修改密码成功");
			Message m = new Message(Message.SELECT_PWD_MSG, account, account, DateUtil.getDate(), account.getNumber() + "已经修改密码");
			client.sendMessage(m);
			this.dispose();
		}
	}

}
