package com.jh.ui.common;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jh.bean.Account;
import com.jh.client.Client;
import com.jh.ui.listener.TrayActionListener;

/**
 * 窗体中公共的一些方法
 * @author Administrator
 *
 */
public class CommonMethod {
	
	public static String IMG_URL = "online"; // 在线状态
	public static String head = "head"; // 更新头像
	public static Client client;
	public static Account account;

	/**
	 * 获取图片
	 * @param frame
	 * @param url
	 * @return
	 */
	public static Icon getImg(JFrame frame, String url) {
		return new ImageIcon(frame.getClass().getResource("/images/" + url));
	}
	
	/**
	 * 获取到JLabel名称
	 * @param e
	 * @return
	 */
	public static String getLabelName(MouseEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JLabel) {
			JLabel label = (JLabel) obj;
			return label.getName();
		}
		return null;
	}
	
	/**
	 * 设置托盘
	 * @param frame
	 */
	public static void setTray(JFrame frame) {
		if (SystemTray.isSupported()) { // 判断系统是否支持托盘
			ImageIcon icon = new ImageIcon(frame.getClass().getResource("/images/tray_" + IMG_URL + ".png")); // 获取图片路径
			PopupMenu popupMenu = new PopupMenu(); // 右键弹窗
			MenuItem item1 = new MenuItem("打开主界面");
			item1.setActionCommand("boundary");
			item1.addActionListener(new TrayActionListener(frame));
			MenuItem item2 = new MenuItem("退出");
			item2.setActionCommand("exit");
			item2.addActionListener(new TrayActionListener(frame));
			popupMenu.add(item1);
			popupMenu.add(item2);
			TrayIcon trayIcon = new TrayIcon(icon.getImage(), "QQ", popupMenu); // 创建托盘图片对象
			trayIcon.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(true);
				}
			});
			SystemTray systemTray = SystemTray.getSystemTray(); // 获得系统托盘对象
			try {
				TrayIcon[] icons = systemTray.getTrayIcons(); // 获取到系统托盘图标的数组
				for (TrayIcon i : icons) {
					systemTray.remove(i); // 每次添加托盘图标前，都先把原来的图标remove掉
				}
				systemTray.add(trayIcon); // 将托盘图片添加到系统托盘中
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
	}

	// 打开浏览器
	public static void shortcutOpen(String url) {
		Desktop desktop = Desktop.getDesktop();
		if (desktop.isSupported(Desktop.Action.BROWSE)) { // 判断当前系统是否支持打开浏览器
			try {
				desktop.browse(new URI(url)); // 打开指定的URI
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// 设置手型
	public static void setLblCursor(JLabel label) {
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 设置鼠标为手型
	}
	
	// 把鼠标设置为默认的
	public static void setLblCursorDef(JLabel label) {
		label.setCursor(Cursor.getDefaultCursor()); // 鼠标设置为默认的
	}

	public static void setPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setBounds(10, 175, 260, 400);
	}
	
	/**
	 * 随机生成1-6
	 */
	public static int myRandom() {
		Random random = new Random();
		int a = random.nextInt(6);
		if (a != 0) {
			return a;
		}
		return 2;
	}
}
