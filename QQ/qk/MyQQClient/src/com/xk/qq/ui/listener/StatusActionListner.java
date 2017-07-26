package com.xk.qq.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StatusActionListner implements ActionListener{
	private JLabel statusLbl;
	

	public StatusActionListner(JLabel statusLbl){
		this.statusLbl = statusLbl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("online")){
			statusLbl.setIcon(new ImageIcon("images/online.png"));
			statusLbl.repaint();
		}else if(action.equals("qme")){
			statusLbl.setIcon(new ImageIcon("images/qme.png"));
			statusLbl.repaint();
		}else if(action.equals("leave")){
			statusLbl.setIcon(new ImageIcon("images/leave.png"));
			statusLbl.repaint();
		}else if(action.equals("busy")){
			statusLbl.setIcon(new ImageIcon("images/busy.png"));
			statusLbl.repaint();
		}else if(action.equals("dont")){
			statusLbl.setIcon(new ImageIcon("images/dont.png"));
			statusLbl.repaint();
		}else if(action.equals("hidden")){
			statusLbl.setIcon(new ImageIcon("images/hidden.png"));
			statusLbl.repaint();
		}
		
	}
}
