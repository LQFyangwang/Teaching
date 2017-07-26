package com.xk.qq.ui.listener;

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

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.common.DateUtil;
import com.xk.qq.client.Client;
import com.xk.qq.ui.common.MouseEnterExitIconUtil;

public class MinListener extends MouseAdapter{
	private JFrame frame;
	private Account account;
	private Client client;
	
	public MinListener(JFrame frame){
		this.frame = frame;
	}
	public MinListener(JFrame frame,Account account, Client client){
		this.frame = frame;
		this.account = account;
		this.client = client;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JLabel){
			JLabel lbl = (JLabel)obj;
			String name = lbl.getName();
			if(name.equals("Login_min")){
				frame.setExtendedState(JFrame.ICONIFIED); // 设置JFrame窗口的状态
			}else if(name.equals("min_main")){
				if(SystemTray.isSupported()){ // 判断当前系统是否支持系统托盘
					frame.setVisible(false);
					SystemTray tray = SystemTray.getSystemTray(); // 获取系统托盘
					TrayIcon trayIcon=new TrayIcon(new ImageIcon("images/qq_notification.png").getImage(), "QQ");
					trayIcon.addActionListener(new ActionListener() { // 给系统托盘图标添加点击事件
						@Override
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(true);
						}
					});
					trayIcon.setPopupMenu(new QQPopupMenu().men());
					try {
						TrayIcon[] icons = tray.getTrayIcons();
						for (TrayIcon i : icons) {
							tray.remove(i);
						}
						tray.add(trayIcon);// 把图标添加到系统托盘
						
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		MouseEnterExitIconUtil.change(e, "images/min_over.png");
	}
	@Override
	public void mouseExited(MouseEvent e) {
		MouseEnterExitIconUtil.change(e, "images/min_def.png");
	}
	
	class QQPopupMenu implements ActionListener{
		
		public PopupMenu men(){
			PopupMenu menu = new PopupMenu();
			MenuItem showItem = new MenuItem("打开主面板");
			showItem.setActionCommand("show");
			showItem.addActionListener(this);
			
			MenuItem exitItem = new MenuItem("退出");
			exitItem.setActionCommand("exit");
			exitItem.addActionListener(this);
			menu.add(showItem);
			menu.add(exitItem);
			return menu;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("show")){
				frame.setVisible(true);
			}else if(e.getActionCommand().equals("exit")){
				if(account != null){
					Message m = new Message(Message.LOGOUT_MSG,DateUtil.getDate(),account,account,"退出");
					client.sendMessage(m);
				}
				System.exit(0);
			}
		}
		
	}
}
