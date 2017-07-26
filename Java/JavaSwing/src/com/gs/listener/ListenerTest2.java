package com.gs.listener;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListenerTest2 extends Frame {
	
	public ListenerTest2() {
		setSize(400, 400);
		setLocation(200, 200);
		setLayout(new FlowLayout());
		
		Button button = new Button("点我！");
		button.setName("btn1");
		button.setActionCommand("1");
		button.addActionListener(new BtnListener());
		add(button);
		
		Button button1 = new Button("点我！");
		button1.setName("btn2");
		button1.setActionCommand("2");
		button1.addActionListener(new BtnListener());
		add(button1);
		
		TextField field = new TextField();
		field.addTextListener(new FieldListener());
		add(field);
		
		TextField field1 = new TextField("AAAA", 10);
		field1.setFont(new Font("微软雅黑", Font.BOLD, 10));
		field1.setForeground(Color.RED); // 设置前景色
		field1.setEditable(false); // 设置文本框是否只读
		field1.addTextListener(new FieldListener());
		add(field1);
		
		TextArea txtArea = new TextArea("初始文本", 4, 20);
		txtArea.setFont(new Font("微软雅黑", Font.BOLD, 20));
		txtArea.setForeground(Color.RED); // 设置前景色
		add(txtArea);
		
		CheckboxGroup cbGroup = new CheckboxGroup();
		
		Checkbox cb1 = new Checkbox("Running", false);
		Checkbox cb2 = new Checkbox("Reading", true);
		Checkbox cb3 = new Checkbox("Sleeping", true);
		cb1.addItemListener(new CheckboxListener());
		cb2.addItemListener(new CheckboxListener());
		cb1.setCheckboxGroup(cbGroup); // 把选项框加入于是选项框组，多个选项框在组里面，就变成了单选按钮，只能选其一
		cb2.setCheckboxGroup(cbGroup);
		cb3.setCheckboxGroup(cbGroup);
		
		add(cb1);
		add(cb2);
		add(cb3);
		// add(cbGroup); // 不是把CheckboxGroup添加到容器中，因为它不是组件
		System.out.println("cb2是否选中: " + cb2.getState());
		
		Choice choice = new Choice();
		choice.add("item1");
		choice.add("item2");
		choice.add("item3");
		choice.addItem("item4"); // add(String) addItem(String)都可以添加选项值
		choice.addItemListener(new ChoiceListener());
		choice.select(2); // 选中指定索引的item
		System.out.println(choice.getItemCount()); // 获取共多少项
		System.out.println(choice.getItem(3)); // 获取指定索引的item，索引从0开始
		add(choice);
		
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new ListenerTest2();
	}

}
