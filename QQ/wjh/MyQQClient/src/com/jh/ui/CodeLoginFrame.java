package com.jh.ui;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jh.common.Constants;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;
import com.jh.ui.listener.LoginListener;

/**
 * 二维码登入的窗体
 * @author Administrator
 *
 */
public class CodeLoginFrame extends JFrame {

	private static final long serialVersionUID = 6477755005683328159L;
	
	public CodeLoginFrame() {
		LoginFrameCommon.setStyle(this);
		LoginFrameCommon.setTopButton(this);
		initWidgets();
		CommonMethod.setTray(this);
	}
	
	private void initWidgets() {
		JLabel txtLbl = new JLabel("请用手机扫描二维码登入");
		txtLbl.setBounds(155, 70, 150, 20);
		txtLbl.setFont(new Font("微软雅黑", Font.BOLD, 13));
		add(txtLbl);
		Icon codeIcon = CommonMethod.getImg(this, "code1.png");
		JLabel codeLbl = new JLabel();
		codeLbl.setIcon(codeIcon);
		codeLbl.setBounds(Constants.codeX, Constants.codeY, 300, 190);
		codeLbl.setName("codeLogin");
		codeLbl.addMouseListener(new LoginListener(this, codeLbl));
		add(codeLbl);
		
		Icon retrunIcon = CommonMethod.getImg(this, "return_btn_def.png");
		JLabel returnLbl = new JLabel();
		returnLbl.setIcon(retrunIcon);
		returnLbl.setBounds(135, 315, 194, 30);
		returnLbl.setName("return");
		returnLbl.addMouseListener(new LoginListener(this, returnLbl));
		add(returnLbl);
	}
}
