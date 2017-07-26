package com.jh.ui.listener;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.jh.bean.Account;
import com.jh.common.Constants;
import com.jh.common.ReflectUtil;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.thread.BeingLoginThread;
import com.jh.ui.BeingLoginFrame;
import com.jh.ui.CodeLoginFrame;
import com.jh.ui.LoginFrame;
import com.jh.ui.ManyAccFrame;
import com.jh.ui.RegisterFrame;
import com.jh.ui.SetFrame;
import com.jh.ui.common.CheckCode;
import com.jh.ui.common.CheckCodeGenerator;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.UIUtil;

public class LoginListener extends MouseAdapter implements KeyListener {
	
	private String name;
	private JFrame frame = null;
	private JLabel label;
	private JLabel mobileLbl;
	
	public LoginListener(JFrame frame, JLabel label) {
		this.frame = frame;
		this.label = label;
	}

	// 监听鼠标按下事件
	@Override
	public void mouseClicked(MouseEvent e) {
		name = CommonMethod.getLabelName(e);
		if (name != null) {
			if (name.equals("register")) { // 注册账号
				if (Constants.isRegisterFrameOpen) {
					new RegisterFrame();
				}
			} else if (name.equals("forget")) { // 忘记密码
				CommonMethod.shortcutOpen("https://aq.qq.com/cn2/findpsw/pc/pc_find_pwd_input_account?source_id=1003&pt_clientver=5473&pt_src=1&ptlang=2052&aquin=null");
			} else if (name.equals("login")) { // 登入
				login();
			} else if (name.equals("set")) { // 设置
				frame.dispose();
				new SetFrame();
			} else if (name.equals("add")) { // 添加
				frame.dispose();
				new ManyAccFrame();
			} else if (name.equals("code")) { // 二维码
				frame.dispose();
				new CodeLoginFrame();
			} else if (name.equals("key")) { // 显示键盘
				JPopupMenu menu = new JPopupMenu();
				JMenuItem item = new JMenuItem(CommonMethod.getImg(frame, "keyboard.png"));
				menu.add(item);
				label.add(menu);
				menu.show(label, -210, 22);
			} else if (name.equals("qq")) { // 右下角的qq图标
				frame.dispose();
				new LoginFrame();
			} else if (name.equals("codeLogin")) { // 二维码登入
				
			} else if (name.equals("return")) { // 返回按钮
				frame.dispose();
				new LoginFrame();
			} else if (name.equals("cancel")) { // 取消
				BeingLoginThread.isOk = false;
				frame.dispose();
				new LoginFrame();
			} else if (name.equals("exchange")) { // 换验证码
				CheckCode cc = CheckCodeGenerator.getCheckCode();
				RegisterFrame.verLbl.setIcon(new ImageIcon(cc.getBuffImg()));
				RegisterFrame.checkCode = cc.getCheckCode(); // 及时更新验证码
			} else if (name.equals("submit")) { // 提交按钮
				if (RegisterFrame.submit) {
					String nickname = RegisterFrame.nicknameTxt.getText();
					String pwd = new String(RegisterFrame.pwdTxt.getPassword());
					String conPwd = new String(RegisterFrame.conpwdTxt.getPassword());
					String mobile = RegisterFrame.mobileTxt.getText();
					String verification = RegisterFrame.verificationTxt.getText();
					String status = "offline";
					String skin = "main_background4";
					int size = mobile.length();
					if (nickname != null && !nickname.equals("") 
							&& pwd.equals(conPwd) 
							&& size == 11
							&& verification.equalsIgnoreCase(RegisterFrame.checkCode)) {
						String number = String.valueOf(new Random().nextInt(999999));
						Account account = new Account();
						account.setAge(RegisterFrame.ageAcc);
						account.setGender(RegisterFrame.genderAcc);
						account.setProvice(RegisterFrame.proviceAcc);
						account.setCity(RegisterFrame.cityAcc);
						account.setArea(RegisterFrame.areaAcc);
						account.setAutograph(null);
						account.setMobile(mobile);
						account.setNumber(number);
						account.setNickname(nickname);
						account.setPwd(pwd);
						account.setStatus(status);
						account.setSkin(skin);
						AccountDAO accountDAO = new AccountDAOImpl();
						Account a = accountDAO.add(account);
						if (a != null) {
							JOptionPane.showMessageDialog(frame, "注册成功，账号为：" + number);
							frame.dispose();
							LoginFrame.accBox.insertItemAt(number, 0);
						}
					} else {
						if (nickname == null || nickname.equals("")) {
							JOptionPane.showMessageDialog(frame, "昵称不能为空");
						} else if (pwd == null || pwd.equals("")) {
							JOptionPane.showMessageDialog(frame, "密码不能为空");
						} else if (!pwd.equals(conPwd)) {
							JOptionPane.showMessageDialog(frame, "两次密码不一致");
						} else if (size != 11) {
							JOptionPane.showMessageDialog(frame, "手机号码必须11位数");
						} else if (!verification.equalsIgnoreCase(RegisterFrame.checkCode)) {
							JOptionPane.showMessageDialog(frame, "验证码错误");
						}
					}
				}
			}
		}
	}

	// 监听鼠标经过事件
	@Override
	public void mouseEntered(MouseEvent e) {
		name = CommonMethod.getLabelName(e);
		if (name != null) {
			if (name.equals("register")) { 
				CommonMethod.setLblCursor(label);
				label.setForeground(UIUtil.myColor(Color.RED));
			} else if (name.equals("forget")) { 
				CommonMethod.setLblCursor(label);
				label.setForeground(UIUtil.myColor(Color.RED));
			} else if (name.equals("login")) { 
				setLblIcon("login_btn_over.png");
			} else if (name.equals("set")) {
				setLblIcon("triangle_over.png");
			} else if (name.equals("key")) {
				CommonMethod.setLblCursor(label);
				setLblIcon("key_over.png");
			} else if (name.equals("qq")) {
				setLblIcon("qq_over.png");
			} else if (name.equals("codeLogin")) { // 二维码登入
				Constants.codeX = 80;
				label.setBounds(Constants.codeX, Constants.codeY, 300, 190);
				Icon mobileIcon = CommonMethod.getImg(frame, "mobile.png");
				mobileLbl = new JLabel();
				mobileLbl.setIcon(mobileIcon);
				mobileLbl.setBounds(230, 100, 180, 190);
				frame.add(mobileLbl);
			} else if (name.equals("return")) { // 返回按钮
				setLblIcon("return_btn_over.png");
			} else if (name.equals("cancel")) { // 取消
				setLblIcon("cancel_btn_over.png");
			} else if (name.equals("exchange")) { // 换验证码
				CommonMethod.setLblCursor(label);
			} else if (name.equals("submit")) {
				CommonMethod.setLblCursor(label);
			} else if (name.equals("add")) {
				setLblIcon("person_over.png");
			} else if (name.equals("code")) {
				setLblIcon("code_over.png");
			}
		}
	}

	// 监听鼠标离开事件
	@Override
	public void mouseExited(MouseEvent e) {
		name = CommonMethod.getLabelName(e);
		if (name != null) {
			if (name.equals("register")) { 
				CommonMethod.setLblCursorDef(label);
				label.setForeground(UIUtil.myColor(Color.BLUE));
			} else if (name.equals("forget")) { 
				CommonMethod.setLblCursorDef(label);
				label.setForeground(UIUtil.myColor(Color.BLUE));
			} else if (name.equals("login")) { 
				setLblIcon("login_btn_def.png");
			} else if (name.equals("set")) {
				setLblIcon("triangle_def.png");
			} else if (name.equals("key")) {
				CommonMethod.setLblCursorDef(label);
				setLblIcon("key_def.png");
			} else if (name.equals("qq")) {
				setLblIcon("qq_def.png");
			} else if (name.equals("codeLogin")) { // 二维码登入
				Constants.codeX = 170;
				label.setBounds(Constants.codeX, Constants.codeY, 300, 190);
				frame.remove(mobileLbl);
			} else if (name.equals("return")) { // 返回按钮
				setLblIcon("return_btn_def.png");
			} else if (name.equals("cancel")) { // 取消
				setLblIcon("cancel_btn_def.png");
			} else if (name.equals("exchange")) { // 换验证码
				CommonMethod.setLblCursorDef(label);
			} else if (name.equals("submit")) {
				CommonMethod.setLblCursorDef(label);
			} else if (name.equals("add")) {
				setLblIcon("person_def.png");
			} else if (name.equals("code")) {
				setLblIcon("code_def.png");
			}
		}
	}
	
	private void setLblIcon(String url) {
		label.setIcon(CommonMethod.getImg(frame, url));
	}
	
	private void login() {
		String number = (String) LoginFrame.accBox.getSelectedItem(); // 获取到登入选中的项
		String pwd = new String(LoginFrame.pwdTxt.getPassword());
		if (number == null) { // 如果检测到下拉菜单中选中的为空，则用反射获取下拉框中输入的值
			number = (String) ReflectUtil.getFieldValue(JComboBox.class, LoginFrame.accBox, "selectedItemReminder");
		}
		if (number != null && !number.equals("") && pwd != null && !pwd.equals("")) { // 如果账号密码框都不为空，则表示可能可以登入
			AccountDAO accountDAO = new AccountDAOImpl();
			Account a = accountDAO.query(number); // 检查账号是否存在
			if (a.getStatus().equals("offline")) {
				Account a1 = accountDAO.query(number, pwd); // 检查账号密码是否存在
				frame.dispose(); // 把当前窗体释放掉
				BeingLoginFrame blf = new BeingLoginFrame();
				new Thread(new BeingLoginThread(blf, a1)).start();
			} else {
				JOptionPane.showMessageDialog(frame, "你已经登入账号" + a.getNumber());
			}
		} else {
			JOptionPane.showMessageDialog(frame, "账号和密码都不能为空");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			login();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
