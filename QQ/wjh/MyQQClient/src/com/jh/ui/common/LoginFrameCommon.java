package com.jh.ui.common;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.UIManager.LookAndFeelInfo;

import com.jh.common.Constants;
import com.jh.thread.BackgroundChangeThread;
import com.jh.ui.listener.ExitListener;
import com.jh.ui.listener.FrameMoverListener;
import com.jh.ui.listener.LoginListener;
import com.jh.ui.listener.MinListener;

public class LoginFrameCommon {
	
	public static JLabel addLbl;

	public static void setStyle(JFrame frame) {
		try {
			LookAndFeelInfo[] feel = UIManager.getInstalledLookAndFeels(); // 获取所有风格
			UIManager.setLookAndFeel(feel[1].getClassName()); // 设置Windows风格
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true); // 去边框
		frame.setBounds(Constants.frameX, Constants.frameY, 450, 355);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource("/images/qq_icon.png"))); // 设置任务栏图标
		FrameMoverListener fml = new FrameMoverListener(frame);
		frame.addMouseListener(fml);
		frame.addMouseMotionListener(fml);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void setTop(JFrame frame) {
		Icon bgIcon = CommonMethod.getImg(frame, "background1.png");
		JLabel bgLbl = new JLabel(bgIcon);
		bgLbl.setBounds(0, 0, 450, 200);
		BackgroundChangeThread bct = new BackgroundChangeThread(bgLbl, frame);
		new Thread(bct).start();
		frame.add(bgLbl);
		Icon setIcon = CommonMethod.getImg(frame, "triangle_def.png");
		JLabel setLbl = new JLabel(setIcon);
		setLbl.setBounds(360, 0, 30, 30);
		setLbl.setToolTipText("设置");
		setLbl.setName("set");
		setLbl.addMouseListener(new LoginListener(frame, setLbl));
		bgLbl.add(setLbl);
		Icon minIcon = CommonMethod.getImg(frame, "min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setBounds(390, 0, 30, 30);
		minLbl.setToolTipText("最小化");
		minLbl.setName("task_min");
		minLbl.addMouseListener(new MinListener(frame));
		bgLbl.add(minLbl);
		Icon exitIcon = CommonMethod.getImg(frame, "exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setBounds(420, 0, 30, 30);
		exitLbl.setToolTipText("关闭");
		exitLbl.addMouseListener(new ExitListener(null, null));
		bgLbl.add(exitLbl);
	}
	
	public static void setBottom(JFrame frame) {
		Icon loginIcon = CommonMethod.getImg(frame, "login_btn_def.png");
		JLabel loginLbl = new JLabel(loginIcon);
		loginLbl.setBounds(135, 315, 194, 30);
		loginLbl.setName("login");
		loginLbl.addMouseListener(new LoginListener(frame, loginLbl));
		frame.add(loginLbl);
		Icon addIcon = CommonMethod.getImg(frame, "person_def.png");
		addLbl = new JLabel(addIcon);
		addLbl.setBounds(10, 320, 40, 40);
		addLbl.setToolTipText("多账号登入");
		addLbl.setName("add");
		addLbl.addMouseListener(new LoginListener(frame, addLbl));
		frame.add(addLbl);
		Icon codeIcon = CommonMethod.getImg(frame, "code_def.png");
		JLabel codeLbl = new JLabel(codeIcon);
		codeLbl.setBounds(410, 315, 40, 40);
		codeLbl.setToolTipText("二维码登入");
		codeLbl.setName("code");
		codeLbl.addMouseListener(new LoginListener(frame, codeLbl));
		frame.add(codeLbl);
	}
	
	public static void setTopButton(JFrame frame) {
		JPanel upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBounds(0, 0, 450, 30);
		upPanel.setBackground(Color.BLUE);
		Icon minIcon = CommonMethod.getImg(frame, "min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setBounds(390, 0, 30, 30);
		minLbl.setToolTipText("最小化");
		minLbl.setName("task_min");
		minLbl.addMouseListener(new MinListener(frame));
		upPanel.add(minLbl);
		Icon exitIcon = CommonMethod.getImg(frame, "exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setBounds(420, 0, 30, 30);
		exitLbl.setToolTipText("关闭");
		exitLbl.addMouseListener(new ExitListener(null , null));
		upPanel.add(exitLbl);
		frame.add(upPanel);
	}
}
