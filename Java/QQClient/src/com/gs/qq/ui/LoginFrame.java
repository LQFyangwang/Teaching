package com.gs.qq.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import com.gs.qq.ui.common.UIUtil;
import com.gs.qq.ui.listener.ExitListener;
import com.gs.qq.ui.listener.FrameDragListener;
import com.gs.qq.ui.listener.LabelHrefListener;
import com.gs.qq.ui.listener.LoginListener;
import com.gs.qq.ui.listener.MinListener;
import com.gs.qq.ui.listener.StatusListener;

public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = -5293038203858812142L;

	public LoginFrame() {
		setSize(450, 370);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true); // 设置为true取消边框，默认为false，保留了边框
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/qq_icon.png"));
		top();
		bottom();
		FrameDragListener dragListener = new FrameDragListener(this);
		addMouseListener(dragListener);
		addMouseMotionListener(dragListener);
		UIUtil.setLookAndFeel();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void top() {
		Icon icon = new ImageIcon("img/background1.png");
		JLabel lbl = new JLabel(icon);
		lbl.setBounds(0, 0, 450, 200);
		add(lbl);
		BackgroundChangeThread bgct = new BackgroundChangeThread(lbl);
		new Thread(bgct).start();
		Icon exitIcon = new ImageIcon("img/exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setBounds(420, 0, 30, 30);
		exitLbl.addMouseListener(new ExitListener(null, null));
		lbl.add(exitLbl);
		Icon minIcon = new ImageIcon("img/min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setBounds(390, 0, 30, 30);
		minLbl.setName("login_min");
		minLbl.addMouseListener(new MinListener(this));
		lbl.add(minLbl);
	}
	
	private void bottom() {
		Icon head = new ImageIcon("img/head.png");
		JLabel lbl = new JLabel(head);
		lbl.setBounds(35, 225, 80, 80);
		add(lbl);
		JLabel statusLbl = new JLabel(new ImageIcon("img/online.png"));
		statusLbl.setBounds(60, 60, 20, 20);
		statusLbl.addMouseListener(new StatusListener(lbl, statusLbl));
		lbl.add(statusLbl);
		JLabel accLbl = new JLabel("账号");
		accLbl.setBounds(140, 225, 40, 20);
		add(accLbl);
		JComboBox<String> accBox = new JComboBox<String>();
		accBox.setEditable(true); // 设置下拉菜单是否可编辑，true为可编辑，默认为false，不可编辑
		accBox.setBounds(190, 225, 140, 20);
		add(accBox);
		LabelHrefListener labelHrefListener = new LabelHrefListener();
		JLabel regLbl = new JLabel("注册账号");
		regLbl.setName("reg");
		regLbl.setFont(UIUtil.myFont(12));
		regLbl.setForeground(Color.BLUE);
		regLbl.setBounds(335, 225, 80, 20);
		regLbl.addMouseListener(labelHrefListener);
		add(regLbl);
		JLabel pwdLbl = new JLabel("密码");
		pwdLbl.setBounds(140, 255, 40, 20);
		add(pwdLbl);
		JPasswordField pwdTxt = new JPasswordField();
		pwdTxt.setBounds(190, 255, 140, 20);
		add(pwdTxt);
		JLabel foLbl = new JLabel("忘记密码");
		foLbl.setFont(UIUtil.myFont(12));
		foLbl.setForeground(Color.BLUE);
		foLbl.setBounds(335, 255, 80, 20);
		foLbl.addMouseListener(labelHrefListener);
		add(foLbl);
		JCheckBox remBox = new JCheckBox("记住密码");
		remBox.setBounds(140, 285, 80, 20);
		add(remBox);
		JCheckBox autoBox = new JCheckBox("自动登录");
		autoBox.setBounds(260, 285, 80, 20);
		add(autoBox);
		Icon loginBtnIcon = new ImageIcon("img/login_btn_def.png");
		JLabel loginLbl = new JLabel(loginBtnIcon);
		loginLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginLbl.setBounds(140, 315, 194, 30);
		loginLbl.addMouseListener(new LoginListener(this, accBox, pwdTxt));
		add(loginLbl);
	}
	
	class BackgroundChangeThread implements Runnable {

		private JLabel lbl;
		
		public BackgroundChangeThread(JLabel lbl) {
			this.lbl = lbl;
		}
		
		@Override
		public void run() {
			int count = 2;
			while (true) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lbl.setIcon(new ImageIcon("img/background" + count + ".png"));
				count++;
				if (count > 3) {
					count = 1;
				}
				
			}
		}
		
	}
	
}
