package com.gs.bank1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BankSavePanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -2625735273187833967L;
	
	private MyBank myBank;
	private JTextField moneyTxt;
	
	public BankSavePanel(MyBank myBank) {
		this.myBank = myBank;
		setLayout(new GridLayout(6, 2));
		JLabel moneyLbl = new JLabel("ÊäÈë½ð¶î");
		moneyTxt = new JTextField(10);
		JButton btn = new JButton("´æ¿î");
		btn.setActionCommand("save");
		btn.addActionListener(this);
		add(moneyLbl);
		add(moneyTxt);
		add(new JLabel());
		add(btn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("save")) {
			double money = Double.valueOf(moneyTxt.getText());
			myBank.save(money);
		}
	}
}
