package com.jh.ui.listener;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import com.jh.common.Constants;

/**
 * 窗体移动监听
 * @author Administrator
 *
 */
public class FrameMoverListener extends MouseAdapter implements MouseMotionListener {

	private JFrame frame;
	// 存放鼠标上一次的位置
	private Point lastPoint;
	
	public FrameMoverListener(JFrame frame) {
		this.frame = frame;
	}
	
	//鼠标按下时调用
	@Override
	public void mousePressed(MouseEvent e) {
		// 获取鼠标按下时在窗体中的位置
		lastPoint = e.getLocationOnScreen();
	}

	// 鼠标拖动时调用
	@Override
	public void mouseDragged(MouseEvent e) {
		Point point = e.getLocationOnScreen();
		if (!lastPoint.equals(point)) {
			int x = point.x - lastPoint.x;
			int y = point.y - lastPoint.y;
			Rectangle rectangle = frame.getBounds();
			rectangle.x = Constants.frameX = rectangle.x + x;
			rectangle.y = Constants.frameY = rectangle.y + y;
			frame.setBounds(rectangle);
			frame.repaint();
			lastPoint = point;
		}
			
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

}
