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
import com.jh.ui.listener.SkinFrameListener;

/**
 * 更换皮肤的窗体
 * @author Administrator
 *
 */
public class AlterSkinFrame extends JFrame {

	private static final long serialVersionUID = -7891866949592111531L;
	
	private MainFrame mainFrame;
	
	public AlterSkinFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		LoginFrameCommon.setStyle(this);
		Constants.isAlterSkinFrameOpen = false;
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
		
		JLabel titleLbl = new JLabel("点击以下皮肤图片更换主界面的皮肤");
		titleLbl.setBounds(300, 60, 300, 20);
		titleLbl.setForeground(Color.RED);
		titleLbl.setFont(new Font("微软雅黑", Font.BOLD, 16));
		add(titleLbl);
		
		// 皮肤示例
		Icon bgIcon = CommonMethod.getImg(this, "skin_background.png");
		JLabel background = new JLabel(bgIcon);
		background.setBounds(20, 100, 140, 300);
		background.setName("background");
		background.addMouseListener(new SkinFrameListener(mainFrame, background));
		add(background);
		JLabel intro = new JLabel("动漫男生");
		intro.setBounds(60, 420, 80, 20);
		add(intro);
		Icon bgIcon1 = CommonMethod.getImg(this, "skin_background1.png");
		JLabel background1 = new JLabel(bgIcon1);
		background1.setBounds(180, 100, 140, 300);
		background1.setName("background1");
		background1.addMouseListener(new SkinFrameListener(mainFrame, background1));
		add(background1);
		JLabel intro1 = new JLabel("望天");
		intro1.setBounds(230, 420, 80, 20);
		add(intro1);
		Icon bgIcon2 = CommonMethod.getImg(this, "skin_background2.png");
		JLabel background2 = new JLabel(bgIcon2);
		background2.setBounds(340, 100, 140, 300);
		background2.setName("background2");
		background2.addMouseListener(new SkinFrameListener(mainFrame, background2));
		add(background2);
		JLabel intro2 = new JLabel("非主流女生");
		intro2.setBounds(380, 420, 80, 20);
		add(intro2);
		Icon bgIcon3 = CommonMethod.getImg(this, "skin_background3.png");
		JLabel background3 = new JLabel(bgIcon3);
		background3.setBounds(500, 100, 140, 300);
		background3.setName("background3");
		background3.addMouseListener(new SkinFrameListener(mainFrame, background3));
		add(background3);
		JLabel intro3 = new JLabel("文字控");
		intro3.setBounds(550, 420, 80, 20);
		add(intro3);
		Icon bgIcon4 = CommonMethod.getImg(this, "skin_background4.png");
		JLabel background4 = new JLabel(bgIcon4);
		background4.setBounds(660, 100, 140, 300);
		background4.setName("background4");
		background4.addMouseListener(new SkinFrameListener(mainFrame, background4));
		add(background4);
		JLabel intro4 = new JLabel("史迪奇");
		intro4.setBounds(700, 420, 80, 20);
		add(intro4);
	}
}
