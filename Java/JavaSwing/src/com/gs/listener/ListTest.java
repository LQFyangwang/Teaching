package com.gs.listener;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListTest extends Frame {
	
	// List与Choice很像，用法基本一致
	public ListTest() {
		setSize(400, 400);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		
		List list = new List(5, true); // 默认单选，如果要多选，List(int rows, boolean multipleSelected) 
		list.add("item1");
		list.add("item2");
		list.add("item3");
		list.addItemListener(new ListListener());
		add(list);
		
		Button btn = new Button("弹出所选项");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String items = "";
				for (String s : list.getSelectedItems()) {
					items += s + " ";
				}
				Dialog dialog = new Dialog(ListTest.this, "所选项");
				dialog.setSize(200, 100);
				dialog.setLocationRelativeTo(null); // 相对于另外一个组件来设置位置，null，屏幕居中显示
				Label label = new Label(items);
				dialog.add(label);
				dialog.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						dialog.dispose(); // 把当前dialog占用的屏幕资源释放
					}
				});
				dialog.setVisible(true); // 使用Dialog类的setVisible(true)显示对话框
			}
		});
		add(btn);
		
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] a) {
		new ListTest();
	}

}
