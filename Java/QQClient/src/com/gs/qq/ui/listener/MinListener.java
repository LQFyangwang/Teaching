package com.gs.qq.ui.listener;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.gs.qq.ui.common.MouseEnterExitIconUtil;

public class MinListener extends MouseAdapter {
	
	private JFrame frame;
	
	public MinListener(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JLabel) {
			JLabel lbl = (JLabel) obj;
			String name = lbl.getName();
			if (name.equals("login_min")) {
				frame.setExtendedState(JFrame.ICONIFIED); // 设置JFrame窗口的状态
			} else if (name.equals("main_min")) {
				frame.setVisible(false); // 让当前窗口不可见
				if (SystemTray.isSupported()) { // 判断当前系统是否支持系统托盘
					SystemTray tray = SystemTray.getSystemTray(); // 获取系统托盘
					TrayIcon trayIcon=new TrayIcon(new ImageIcon("img/qq_notification.png").getImage(), "QQ");
					trayIcon.addActionListener(new ActionListener() { // 给系统托盘图标添加点击事件
						@Override
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(true);
						}
					});
					trayIcon.setPopupMenu(new QQNoficationPopupMenu().menu());
					try {
						TrayIcon[] icons = tray.getTrayIcons();
						for (TrayIcon icon : icons) {
							tray.remove(icon);
						}
						tray.add(trayIcon); // 把图标添加到系统托盘
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		MouseEnterExitIconUtil.change(e, "img/min_over.png");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		MouseEnterExitIconUtil.change(e, "img/min_def.png");
	}
	
	class QQNoficationPopupMenu implements ActionListener {
		
		public PopupMenu menu() {
			PopupMenu menu = new PopupMenu();
			MenuItem showMainFrame = new MenuItem("显示QQ窗口");
			showMainFrame.setActionCommand("show");
			showMainFrame.addActionListener(this);
			MenuItem exit = new MenuItem("退出");
			exit.setActionCommand("exit");
			exit.addActionListener(this);
			menu.add(showMainFrame);
			menu.addSeparator();
			menu.add(exit);
			return menu;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if (action.equals("show")) {
				frame.setVisible(true);
			} else if (action.equals("exit")) {
				System.exit(0);
			}
		}
		
	}
}

