package com.jh.ui;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jh.thread.BeingLoginThread;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;
import com.jh.ui.listener.LoginListener;

/**
 * 正在登入的窗体
 * @author Administrator
 *
 */
public class BeingLoginFrame extends JFrame {
	
	public static final long serialVersionUID = 2L;

	public BeingLoginFrame() {
		BeingLoginThread.isOk = true;
		LoginFrameCommon.setStyle(this);
		LoginFrameCommon.setTop(this);
		initCenter();
		initBottom();
		CommonMethod.setTray(this);
	}

	private void initCenter() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setBounds(0, 200, 450, 110);
		Icon accIcon = CommonMethod.getImg(this, CommonMethod.head + ".png");
		JLabel accLbl = new JLabel(accIcon);
		accLbl.setBounds(180, 20, 80,80);
		centerPanel.add(accLbl);
		add(centerPanel);
	}
	
	public void initBottom() {
		Icon returnIcon = CommonMethod.getImg(this, "cancel_btn_def.png");
		JLabel returnLbl = new JLabel(returnIcon);
		returnLbl.setBounds(135, 315, 194, 30);
		returnLbl.setName("cancel");
		returnLbl.addMouseListener(new LoginListener(this, returnLbl));
		add(returnLbl);
		Icon addIcon = CommonMethod.getImg(this, "person_def.png");
		JLabel addLbl = new JLabel(addIcon);
		addLbl.setBounds(10, 320, 40, 40);
		addLbl.setToolTipText("多账号登入");
		addLbl.setName("add");
		addLbl.addMouseListener(new LoginListener(this, addLbl));
		add(addLbl);
		Icon codeIcon = CommonMethod.getImg(this, "code_def.png");
		JLabel codeLbl = new JLabel(codeIcon);
		codeLbl.setBounds(410, 315, 40, 40);
		codeLbl.setToolTipText("二维码登入");
		codeLbl.setName("code");
		codeLbl.addMouseListener(new LoginListener(this, codeLbl));
		add(codeLbl);
	}
}
