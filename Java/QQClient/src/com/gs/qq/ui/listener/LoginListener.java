package com.gs.qq.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.gs.bean.Account;
import com.gs.common.ReflectUtil;
import com.gs.dao.AccountDAO;
import com.gs.dao.AccountDAOImpl;
import com.gs.qq.client.Client;
import com.gs.qq.ui.LoginFrame;
import com.gs.qq.ui.MainFrame;

public class LoginListener extends MouseAdapter {

	private LoginFrame loginFrame;
	private JComboBox<String> accBox;
	private JPasswordField pwd;
	
	/**
	 * 实例化一个监听登录按钮的监听器
	 * 此监听器需要知道是来源于哪个窗口
	 * 还需要获取账号和密码
	 * @param loginFrame
	 * @param accBox
	 * @param pwd
	 */
	public LoginListener(LoginFrame loginFrame, JComboBox<String> accBox, JPasswordField pwd) {
		this.loginFrame = loginFrame;
		this.accBox = accBox;
		this.pwd = pwd;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String number = accBox.getItemAt(0); // 获取账号， 是指从checkbox中选择的
		if (number == null) { // 如果没有账号信息，则意味着很有可能是用户手动输入的
			number = (String) ReflectUtil.getFieldValue(JComboBox.class, accBox, "selectedItemReminder"); // 获取用户手动输入的账号
			
		}
		if (number != null && !number.equals("") ) {
			System.out.println("aaaa");
			AccountDAO accountDAO = new AccountDAOImpl();
			Account a = accountDAO.query(number, new String(pwd.getPassword()));
			if (a != null) { // 如果数据库中有该 账号，完成登录操作
				System.out.println("bbb");
				Client client = new Client(); // 实例化Client类
				client.login(a);
				loginFrame.dispose();
				new MainFrame(client, a); // 把已经登录的用户信息传递给主界面，把此用户对应的Client对象也传递给主界面
				
			}else {
				JOptionPane.showMessageDialog(loginFrame, "账号或密码错误");
			}
		}
	}

}
