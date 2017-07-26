package com.jh.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jh.common.Constants;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;
import com.jh.ui.listener.MinListener;

/**
 * 更换头像的窗体
 * @author Administrator
 *
 */
public class AlterHeadFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7891866949592111531L;
	
	public AlterHeadFrame() {
		LoginFrameCommon.setStyle(this);
		Constants.isAlterHeadFrameOpen = false;
		initWidgets();
		setBounds(300, 100, 820, 460);
	}
	
	public void initWidgets() {
		// 顶部的图标面板
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBounds(0, 0, 820, 30);
		topPanel.setBackground(Color.BLUE);
		Icon qqIconIcon = CommonMethod.getImg(this, "main_qq_icon.png");
		JLabel qqIconLbl = new JLabel(qqIconIcon);
		qqIconLbl.setBounds(0, 0, 60, 30);
		topPanel.add(qqIconLbl);
		Icon minIcon = CommonMethod.getImg(this, "min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setBounds(760, 0, 30, 30);
		minLbl.setToolTipText("最小化");
		minLbl.setName("min");
		minLbl.addMouseListener(new MinListener(this));
		topPanel.add(minLbl);
		Icon exitIcon = CommonMethod.getImg(this, "exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setBounds(790, 0, 30, 30);
		exitLbl.setToolTipText("关闭");
		exitLbl.setName("dispose");
		exitLbl.addMouseListener(new MinListener(this));
		topPanel.add(exitLbl);
		add(topPanel);
		
		int x = 20;
		int y = 50;
		int count = 1;
		for (int i = 0; i < 4; i++) {
			x = 20;
			for (int j = 1; j <= 8; j++) {
				JButton headBtn = new JButton(CommonMethod.getImg(this, "head" + count + ".png"));
				headBtn.setBounds(x, y, 80, 80);
				headBtn.setActionCommand("head" + count);
				headBtn.addActionListener(this);
				headBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				add(headBtn);
				count++;
				x += 100;
			}
			y += 100;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		for (int i = 1; i <= 32; i++) {
			if (name.equals("head" + i)) {
				SelectDataFrame.setSaveBtn();
				SelectDataFrame.headBtn.setIcon(CommonMethod.getImg(this, "head" + i + ".png"));
				SelectDataFrame.headName = "head" + i;
			}
		}
	}
}
