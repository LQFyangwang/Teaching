package com.gs.listener;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnListener implements  ActionListener {

	/**
	 * 使用setName或者setAcionComman来区分按钮
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Button btn = (Button) e.getSource();
		String btnName = btn.getName();
		if (btnName.equals("btn1")) {
			System.out.println("执行按钮1对应的操作");
		} else if (btnName.equals("btn2")) {
			System.out.println("执行按钮2对应的操作");
		}
		System.out.println(e.getActionCommand()); // 获取Button上的Label
		System.out.println("action...");
	}

}
