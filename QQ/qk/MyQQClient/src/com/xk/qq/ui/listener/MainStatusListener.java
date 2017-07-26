package com.xk.qq.ui.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.xk.bean.Account;
import com.xk.qq.client.Client;

public class MainStatusListener extends MouseAdapter {

	private JLabel Lbl;
	private JLabel statusLbl;
	private Account account;
	private Client client;
	
	private String[] actionName = new String[]{"online","qme","leave","busy","dont","hidden"};
	public MainStatusListener(Account account,Client client,JLabel Lbl, JLabel statusLbl){
		this.account = account;
		this.client = client;
		this.Lbl = Lbl;
		this.statusLbl = statusLbl;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JPopupMenu menu = new JPopupMenu();
		MainStatusActionListner sta = new MainStatusActionListner(account,client,statusLbl);
		for(int i = 0,len = actionName.length; i < len; i++){
			JMenuItem item = new JMenuItem(new ImageIcon("images/" + actionName[i] + ".png"));
			item.setActionCommand(actionName[i]);
			item.addActionListener(sta);
			menu.add(item);
		}
		
		Lbl.add(menu);
		menu.show(Lbl, -20, 20);
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
