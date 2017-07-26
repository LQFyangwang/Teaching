package com.jh.ui.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.jh.bean.Account;
import com.jh.client.Client;
import com.jh.common.Constants;
import com.jh.ui.common.CommonMethod;

public class StatusListener extends MouseAdapter {
	
	private JFrame frame;
	private JLabel statusLbl;
	private Account account;
	private Client client;
	
	public StatusListener(JFrame frame, JLabel statusLbl, Account account, Client client) {
		this.frame = frame;
		this.statusLbl = statusLbl;
		this.account = account;
		this.client = client;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JPopupMenu menu = new JPopupMenu();
		StatusActionListener sal = new StatusActionListener(frame, statusLbl, account, client);
		JMenuItem item1 = new JMenuItem("我在线上", CommonMethod.getImg(frame, "online.png"));
		item1.setActionCommand("online");
		item1.addActionListener(sal);
		item1.setToolTipText("表示希望好友看到您在线");
		menu.add(item1);
		JMenuItem item2 = new JMenuItem("Q我吧", CommonMethod.getImg(frame, "qme.png"));
		item2.setActionCommand("qme");
		item2.addActionListener(sal);
		item2.setToolTipText("表示希望好友主动联系您");
		menu.add(item2);
		JMenuItem item3 = new JMenuItem("离开", CommonMethod.getImg(frame, "leave.png"));
		item3.setActionCommand("leave");
		item3.addActionListener(sal);
		item3.setToolTipText("表示离开，暂无法处理消息");
		menu.add(item3);
		JMenuItem item4 = new JMenuItem("忙碌", CommonMethod.getImg(frame, "busy.png"));
		item4.setActionCommand("busy");
		item4.addActionListener(sal);
		item4.setToolTipText("表示忙碌，不会及时处理消息");
		menu.add(item4);
		JMenuItem item5 = new JMenuItem("请勿打扰", CommonMethod.getImg(frame, "dont.png"));
		item5.setActionCommand("dont");
		item5.addActionListener(sal);
		item5.setToolTipText("表示不想被打扰");
		menu.add(item5);
		JMenuItem item6 = new JMenuItem("隐身", CommonMethod.getImg(frame, "hidden.png"));
		item6.setActionCommand("hidden");
		item6.addActionListener(sal);
		item6.setToolTipText("表示好友看到您是离线的");
		menu.add(item6);
		statusLbl.add(menu);
		menu.show(statusLbl, 20, 15);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (Constants.mainOk) {
			statusLbl.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190)));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		statusLbl.setBorder(null);
	}
}
