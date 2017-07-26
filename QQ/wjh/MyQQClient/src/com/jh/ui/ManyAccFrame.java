package com.jh.ui;


import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;
import com.jh.ui.listener.LoginListener;

/**
 * 多账号登入窗体
 * @author Administrator
 *
 */
public class ManyAccFrame extends JFrame {
	
	public static final long serialVersionUID = 2L;

	public ManyAccFrame() {
		
		LoginFrameCommon.setStyle(this);
		LoginFrameCommon.setTop(this);
		initCenter();
		LoginFrameCommon.setBottom(this);
		remove(LoginFrameCommon.addLbl);
		CommonMethod.setTray(this);
	}

	private void initCenter() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setBounds(0, 200, 450, 110);
		Icon accIcon = CommonMethod.getImg(this, "add_acc.png");
		// 中间部分的图标
		JLabel accLbl = new JLabel(accIcon);
		accLbl.setBounds(170, 30, 117,33);
		accLbl.setToolTipText("添加多个账号"); // 设置文本提示
		centerPanel.add(accLbl);
		add(centerPanel);
		
		// 左下角的图标
		Icon qqIcon = CommonMethod.getImg(this, "qq_def.png");
		JLabel qqLbl = new JLabel(qqIcon);
		qqLbl.setBounds(10, 320, 40, 40);
		qqLbl.setToolTipText("单账号登入");
		qqLbl.setName("qq");
		qqLbl.addMouseListener(new LoginListener(this, qqLbl));
		add(qqLbl);
	}
}
