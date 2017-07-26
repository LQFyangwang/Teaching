package com.xk.qq.ui.listener;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class FrameDragListener extends MouseAdapter{
	private JFrame frame;
	private Point lastPoint; //存鼠标上一次鼠标的位置     Point
	
	public FrameDragListener(JFrame frame){
		this.frame = frame;
	}

	//按下
	@Override
	public void mousePressed(MouseEvent e) {
		lastPoint = e.getLocationOnScreen(); // 当点上去的时候的位置，还没松开
	}
	//拖动
	@Override
	public void mouseDragged(MouseEvent e) {
		
		// 获取拖动后的鼠标位置
		Point point = e.getLocationOnScreen();
		// 当前鼠标位置，减去上一次鼠标位置
		int x = point.x - lastPoint.x;
		int y = point.y - lastPoint.y;
		// 获取当前界面位置
		Rectangle bound = frame.getBounds();
		// 将界面的位置根据鼠标的移动位置进行加减
		bound.x += x;
		bound.y += y;
		// 将新的位置设置在界面中
		frame.setBounds(bound);
		//将移动完的鼠标位置付给上一次的鼠标位置
		lastPoint = point;
	}
	
}
