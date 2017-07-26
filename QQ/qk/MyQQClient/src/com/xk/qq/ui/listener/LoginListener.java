package com.xk.qq.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.xk.bean.Account;
import com.xk.common.Constants;
import com.xk.common.ReflectUtil;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;
import com.xk.qq.client.Client;
import com.xk.qq.ui.LoginFrame;
import com.xk.qq.ui.MainFrame;

public class LoginListener extends MouseAdapter{
	
	private LoginFrame loginFrame;
	private JComboBox<String>  accBox;
	private JPasswordField pwd;
	private JLabel lbl;
	
	public LoginListener(LoginFrame loginFrame,JComboBox<String> accBox, JPasswordField pwd, JLabel lbl){
		this.loginFrame = loginFrame;
		this.accBox = accBox;
		this.pwd = pwd;
		this.lbl = lbl;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String number = accBox.getItemAt(0);
		if(number == null){
			number = (String) ReflectUtil.getFieldValue(JComboBox.class, accBox, "selectedItemReminder");
		}
		String strPwd = new String(pwd.getPassword());
		if(number != null && !number .equals("") && strPwd != null && !strPwd.equals("")){
			AccountDAO account = new AccountDAOImpl();
			Account a = account.queryFriend(number);
			if (a.getState().equals("offline")){
				Account a1 = account.query(number, strPwd);
				if(a1 != null){
					Client c = new Client();
					c.login(a1);
					loginFrame.dispose();
					new MainFrame(a1,c);
				}else{
					JOptionPane.showMessageDialog(loginFrame, "账号或密码错误","提示",JOptionPane.INFORMATION_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(loginFrame, "账号已登录请下线之后在登","提示",JOptionPane.INFORMATION_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(loginFrame, "账号密码不能为空","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		lbl.setIcon(new ImageIcon("images/login_btn_over.png"));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		lbl.setIcon(new ImageIcon("images/login_btn_def.png"));
	}
	
	
}
