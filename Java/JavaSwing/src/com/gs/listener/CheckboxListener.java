package com.gs.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckboxListener implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println(e.getItem()); // 获取选项框中的文本值
		System.out.println(e.getStateChange()); // e.getStateChange()选中为1，取消选中为2
	}

}
