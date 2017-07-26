package com.gs.qq.ui.listener;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class FrameDragListener extends MouseAdapter implements MouseMotionListener {

	private JFrame frame;
	private Point lastPoint; // 鼠标位置
	
	public FrameDragListener(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		lastPoint = e.getLocationOnScreen();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point currPoint = e.getLocationOnScreen();
		if (!currPoint.equals(lastPoint)) {
			int x = currPoint.x - lastPoint.x;
			int y = currPoint.y - lastPoint.y;
			Rectangle bounds = frame.getBounds(); // getBounds()获取的是左上角x,y组成的Point点类
			bounds.x += x;
			bounds.y += y;
			frame.setBounds(bounds);
			lastPoint = currPoint;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

}
