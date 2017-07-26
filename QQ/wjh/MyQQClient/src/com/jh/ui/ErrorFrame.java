package com.jh.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;

/**
 * 账号密码错误的窗体
 * @author Administrator
 *
 */
public class ErrorFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 6477755005683328159L;

	public ErrorFrame() {
		LoginFrameCommon.setStyle(this);
		LoginFrameCommon.setTopButton(this);
		initWidgets();
		CommonMethod.setTray(this);
	}
	
	private void initWidgets() {
		JLabel label1 = new JLabel("您输入的密码不正确，您要找回密码吗？");
		label1.setBounds(80, 90, 300, 20);
		add(label1);
		JLabel label2 = new JLabel("如果您的密码丢失或遗忘，可访问QQ安全中心找回密码。");
		label2.setBounds(80, 130, 320, 20);
		add(label2);
		JLabel label3 = new JLabel("如果您无法中国密保找回密码，请进行账号申诉。");
		label3.setBounds(80, 150, 300, 20);
		add(label3);
		JLabel label4 = new JLabel("如果您的账号为非大陆手机号，请点击这里进行登入。");
		label4.setBounds(80, 220, 300, 20);
		add(label4);
		
		JPanel downPanel = new JPanel();
		downPanel.setLayout(null);
		downPanel.setBackground(Color.GRAY);
		downPanel.setBounds(0, 315, 450, 40);
		JButton errorBtn = new JButton("找回密码");
		errorBtn.setActionCommand("error");
		errorBtn.addActionListener(this);
		errorBtn.setBounds(220, 5, 100, 30);
		downPanel.add(errorBtn);
		JButton cancelBtn = new JButton("取消");
		cancelBtn.setActionCommand("cancel");
		cancelBtn.addActionListener(this);
		cancelBtn.setBounds(325, 5, 100, 30);
		downPanel.add(cancelBtn);
		add(downPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		if (name.equals("error")) {
			CommonMethod.shortcutOpen("https://aq.qq.com/cn2/findpsw/pc/pc_find_pwd_input_account?source_id=1003&pt_clientver=5473&pt_src=1&ptlang=2052&aquin=null");
		} else if (name.equals("cancel")) {
			this.dispose();
			new LoginFrame();
		}
	}
}
