package com.jh.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jh.common.Constants;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;
import com.jh.ui.listener.MinListener;

public class ApplyManageFrame extends JFrame {

	private static final long serialVersionUID = 3059799925802613304L;
	
	public ApplyManageFrame() {
		Constants.isApplyManageFrameOpen = false;
		LoginFrameCommon.setStyle(this);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBounds(0, 0, 900, 30);
		topPanel.setBackground(Color.BLUE);
		Icon qqIconIcon = CommonMethod.getImg(this, "applyManage_icon.png");
		JLabel qqIconLbl = new JLabel("应用管理器", qqIconIcon, JLabel.RIGHT);
		qqIconLbl.setBounds(0, 0, 120, 30);
		qqIconLbl.setFont(new Font("微软雅黑", Font.BOLD, 15));
		qqIconLbl.setForeground(Color.white);
		topPanel.add(qqIconLbl);
		Icon minIcon = CommonMethod.getImg(this, "min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setBounds(840, 0, 30, 30);
		minLbl.setToolTipText("最小化");
		minLbl.setName("min");
		minLbl.addMouseListener(new MinListener(this));
		topPanel.add(minLbl);
		Icon exitIcon = CommonMethod.getImg(this, "exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setBounds(870, 0, 30, 30);
		exitLbl.setToolTipText("关闭");
		exitLbl.setName("dispose");
		exitLbl.addMouseListener(new MinListener(this));
		topPanel.add(exitLbl);
		add(topPanel);
		JLabel mainLbl = new JLabel(CommonMethod.getImg(this, "applyManage.png"));
		mainLbl.setBounds(0, 30, 900, 570);
		add(mainLbl);
		setBounds(300, 100, 900, 600);
	}
}
