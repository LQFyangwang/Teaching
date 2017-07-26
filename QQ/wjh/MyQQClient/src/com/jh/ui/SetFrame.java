package com.jh.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;

/**
 * 设置窗体
 * @author Administrator
 *
 */
public class SetFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 6477755005683328159L;

	public SetFrame() {
		LoginFrameCommon.setStyle(this);
		LoginFrameCommon.setTopButton(this);
		initWidgets();
		CommonMethod.setTray(this);
	}
	
	private void initWidgets() {
		JLabel label1 = new JLabel("网络设置");
		label1.setBounds(20, 70, 80, 20);
		add(label1);
		JLabel label2 = new JLabel("类型：");
		label2.setBounds(30, 100, 50, 20);
		add(label2);
		JComboBox<String> box = new JComboBox<String>();
		box.addItem("不使用代理");
		box.addItem("HTTP代理");
		box.addItem("SOCKET5代理");
		box.addItem("使用浏览器代理设置");
		box.setBounds(70, 100, 110, 20);
		add(box);
		JLabel label3 = new JLabel("地址：");
		label3.setBounds(190, 100, 50, 20);
		add(label3);
		JTextField field1 = new JTextField();
		field1.setBounds(225, 100, 80, 20);
		add(field1);
		JLabel label4 = new JLabel("端口：");
		label4.setBounds(315, 100, 50, 20);
		add(label4);
		JTextField field2 = new JTextField();
		field2.setBounds(350, 100, 80, 20);
		add(field2);
		JLabel label5 = new JLabel("用户名：");
		label5.setBounds(20, 130, 50, 20);
		add(label5);
		JTextField field3 = new JTextField();
		field3.setBounds(70, 130, 110, 20);
		add(field3);
		JLabel label6 = new JLabel("密码：");
		label6.setBounds(190, 130, 50, 20);
		add(label6);
		JTextField field4 = new JTextField();
		field4.setBounds(225, 130, 80, 20);
		add(field4);
		JLabel label7 = new JLabel("域：");
		label7.setBounds(325, 130, 50, 20);
		add(label7);
		JTextField field8 = new JTextField();
		field8.setBounds(350, 130, 80, 20);
		add(field8);
		JButton btn = new JButton("测试");
		btn.setEnabled(false);
		btn.setBounds(350, 160, 80, 20);
		add(btn);
		
		JLabel label8 = new JLabel("登入服务器");
		label8.setBounds(20, 210, 80, 20);
		add(label8);
		JLabel label9 = new JLabel("类型：");
		label9.setBounds(30, 250, 50, 20);
		add(label9);
		JComboBox<String> box1 = new JComboBox<String>();
		box1.addItem("不使用高级选项");
		box1.addItem("UDP类型");
		box1.addItem("TCP类型");
		box1.setBounds(70, 250, 110, 20);
		add(box1);
		JLabel label10 = new JLabel("地址：");
		label10.setBounds(190, 250, 50, 20);
		add(label10);
		JTextField field9 = new JTextField();
		field9.setBounds(225, 250, 80, 20);
		add(field9);
		JLabel label11 = new JLabel("端口：");
		label11.setBounds(315, 250, 50, 20);
		add(label11);
		JTextField field10 = new JTextField();
		field10.setBounds(350, 250, 80, 20);
		add(field10);
		
		JPanel downPanel = new JPanel();
		downPanel.setLayout(null);
		downPanel.setBackground(Color.GRAY);
		downPanel.setBounds(0, 315, 450, 40);
		JButton confrimBtn = new JButton("确定");
		confrimBtn.addActionListener(this);
		confrimBtn.setBounds(220, 5, 100, 30);
		downPanel.add(confrimBtn);
		JButton cancelBtn = new JButton("取消");
		cancelBtn.addActionListener(this);
		cancelBtn.setBounds(325, 5, 100, 30);
		downPanel.add(cancelBtn);
		add(downPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		new LoginFrame();
	}
}
