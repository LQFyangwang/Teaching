package com.gs.listener;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListenerTest1 extends Frame implements ActionListener, TextListener {
	
	public ListenerTest1() {
		setSize(400, 400);
		setLocation(200, 200);
		setLayout(new FlowLayout());
		
		Button button = new Button("请点我！");
		button.addActionListener(this);
		add(button);
		
		TextField field = new TextField("默认文本");
		field.addTextListener(this);
		add(field);
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("action....");
	}
	
	public static void main(String[] args) {
		new ListenerTest1();
	}

	@Override
	public void textValueChanged(TextEvent e) {
		System.out.println("text changed...");
	}

}
