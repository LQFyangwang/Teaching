package com.gs.listener;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuBarTest extends Frame {
	
	public MenuBarTest() {
		setSize(600, 400);
		setLocation(200, 200);
		setLayout(new FlowLayout());
		
		MenuBar bar = new MenuBar(); // 实例化菜单栏
		Menu menu = new Menu("File"); // 实例化一个菜单
		MenuItemListener l = new MenuItemListener();
		MenuItem item1 = new MenuItem("New"); // 实例化菜单项
		item1.addActionListener(l);
		MenuItem item2 = new MenuItem("Open");
		item2.addActionListener(l);
		menu.add(item1); // 把菜单项添加到某个菜单
		menu.addSeparator(); // 添加菜单项间的分隔条
		menu.add(item2);
		bar.add(menu); // 把菜单添加到菜单栏
		Menu menu1 = new Menu("Edit");
		MenuItem item3 = new MenuItem("Undo");
		item3.addActionListener(l);
		MenuItem item4 = new MenuItem("Redo");
		item4.addActionListener(l);
		menu1.add(item3);
		menu1.addSeparator();
		menu1.add(item4);
		bar.add(menu1);

		setMenuBar(bar); // 给当前窗体设置菜单栏
		
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	private class MenuItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			if (e.getActionCommand().equals("New")) {
				System.out.println("新建文件");
			}
		}
	}
	
	public static void main(String[] args) {
		new MenuBarTest();
	}
}
