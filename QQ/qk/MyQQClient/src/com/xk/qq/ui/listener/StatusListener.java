package com.xk.qq.ui.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class StatusListener extends MouseAdapter{
	
	private JLabel headLbl;
	private JLabel statusLbl;
	private String[] actionName = new String[]{"online","qme","leave","busy","dont","hidden"};
	private String[] strName = new String[]{"我在线上","Q我吧","离开","忙碌","请勿打扰","隐身"};
	public StatusListener(JLabel headLbl, JLabel statusLbl){
		this.headLbl = headLbl;
		this.statusLbl = statusLbl;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JPopupMenu menu = new JPopupMenu();
		StatusActionListner sta = new StatusActionListner(statusLbl);
		for(int i = 0,len = actionName.length; i < len; i++){
			JMenuItem item = new JMenuItem(strName[i],new ImageIcon("images/" + actionName[i] + ".png"));
			item.setActionCommand(actionName[i]);
			item.addActionListener(sta);
			menu.add(item);
		}
		
		headLbl.add(menu);
		menu.show(headLbl, 77, 77);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		statusLbl.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0.5f), 1,true));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		statusLbl.setBorder(null);
	}
	
	
	
}
