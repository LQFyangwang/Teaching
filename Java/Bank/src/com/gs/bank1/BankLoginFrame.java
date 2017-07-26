package com.gs.bank1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class BankLoginFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 7389301369456276658L;
	private JTextField numberTxt;
	private JPasswordField pwdTxt;
	
	private MyBank myBank;
	
	public BankLoginFrame() {
		setTitle("登录");
		myBank = new MyBank();
		setSize(400 ,400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new GridLayout(6,2));
		JLabel numberLbl = new JLabel("账号");
		numberTxt = new JTextField(10);
		JLabel pwdLbl = new JLabel("密码");
		pwdTxt = new JPasswordField(10);
		JButton btn = new JButton("登录");
		btn.setActionCommand("login");
		btn.addActionListener(this);
		add(numberLbl);
		add(numberTxt);
		add(pwdLbl);
		add(pwdTxt);
		add(new JLabel());
		add(btn);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * 当点击登录按钮的时候，需要从文本文件中读取所有的行，每一行对应一个Account对象，所有Account都添加到List列表中，
	 * 并且保留当前已经登录的账号到MyBank中，接下来的每一个操作都 是基于该保存的账号
	 * 再从该列表中查找是否有要登录的账号，如果有，则把当前窗口隐藏，显示出主窗口
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("login")) {
			String number = numberTxt.getText();
			String pwd = String.valueOf(pwdTxt.getPassword());
			Account account = myBank.query(number, pwd);
			if (account != null) {
				dispose();
				new BankMenuFrame(account);
			} else {
				JOptionPane.showMessageDialog(this.getParent(), "请输入正确的账号和密码，如果没有账号，请开户", "账号信息", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
