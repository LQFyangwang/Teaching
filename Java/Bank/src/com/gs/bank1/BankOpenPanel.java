package com.gs.bank1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class BankOpenPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -2625735273187833967L;
	
	private MyBank myBank;
	
	private JTextField nameTxt;
	private JPasswordField pwdTxt;
	private JPasswordField pwdTxt1;
	
	public BankOpenPanel(MyBank myBank) {
		this.myBank = myBank;
		setLayout(new GridLayout(6, 2));
		JLabel nameLbl = new JLabel("账户名");
		nameTxt = new JTextField(10);
		JLabel pwdLbl = new JLabel("密码");
		pwdTxt = new JPasswordField(10);
		JLabel pwdLbl1 = new JLabel("确认密码");
		pwdTxt1 = new JPasswordField(10);
		JButton btn = new JButton("确认");
		btn.addActionListener(this);
		btn.setActionCommand("ok");
		add(nameLbl);
		add(nameTxt);
		add(pwdLbl);
		add(pwdTxt);
		add(pwdLbl1);
		add(pwdTxt1);
		add(new JLabel());
		add(btn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ok")) {
			String name = nameTxt.getText();
			String pwd = String.valueOf(pwdTxt.getPassword());
			String pwd1 = String.valueOf(pwdTxt1.getPassword());
			if (name != null && !name.equals("") 
					&& pwd != null && !pwd.equals("") 
					&& pwd1 != null && !pwd1.equals("")
					&& pwd.equals(pwd1)) {
				Account account = myBank.openAccout(name, pwd); // 开户操作
				if (account != null) {
					JOptionPane.showMessageDialog(this.getParent(), account.toString(), "开户成功", JOptionPane.INFORMATION_MESSAGE);
					BankFile.save(account); // 追加数据到文件末尾
				}
			} else {
				JOptionPane.showMessageDialog(this.getParent(), "请输入用户名和正确的密码", "开户提示", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
