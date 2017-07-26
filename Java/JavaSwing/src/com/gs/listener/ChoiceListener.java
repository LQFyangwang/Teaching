package com.gs.listener;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChoiceListener implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println(e.getItem()); // 返回文本值
		Choice choice = (Choice) e.getSource();
		System.out.println(choice.getSelectedItem()); // e.getItem() choice getSelectedItem都可以获取到choice选中的项
	}

}
