package com.gs.graphics;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import com.gs.listener.MyWindowListener;

public class GraphicsTest2 extends Frame implements MouseMotionListener {
	
	private BufferedImage bufImage;
	private int x;
	private int y;
	
	public GraphicsTest2() {
		setSize(800, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		addMouseMotionListener(this);
		addWindowListener(new MyWindowListener());
	}
	
	private void createBufferedImage() {
		bufImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_BGR);
		Graphics gg = bufImage.getGraphics();
		gg.setColor(Color.RED); // 给当前画笔设置颜色
		gg.drawLine(100, 100, 200, 100); // 画直线 起点的x,y。 终点的x,y
		gg.drawLine(100, 100, 100, 200);
		gg.drawRect(100, 200, 100, 50); // 画方形 左上用的x,y   方形的宽和高
		gg.draw3DRect(100, 300, 100, 50, false); // 画方形
		gg.drawRect(100, 400, 100, 50);
		gg.drawLine(150, 425, 150, 425);
		gg.drawArc(100, 400, 100, 50, 0, 270); // 画弧形
		gg.drawString("我是字符串", 100, 500); // 绘制字符串， String想要绘制的字符串，   字符串开始的左上角的x,y坐标
		gg.drawChars(new char[]{'a', 'b', 'c', 'd'},  1, 3, 100, 550); // 输出指定开始位置的指定长度字符
		gg.drawPolygon(new int[]{310, 340, 420, 450, 500, 510}, 
						new int[]{200, 220, 310, 330, 400, 420}, 6); // 多边开
		gg.drawOval(300, 300, 100, 200); // 圆形
		gg.drawRoundRect(400, 500, 100, 200, 5, 30); // 画圆角矩形
	}
	
	@Override
	public void paint(Graphics g) {
		createBufferedImage();
		g.drawImage(bufImage, 0, 0, this);
	}
	
	@Override
	public void update(Graphics g) {
		createBufferedImage();
		Graphics gg = bufImage.getGraphics();
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResource("/images/1.jpg"));
			gg.drawImage(img, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(bufImage, 0, 0, this);
		
	}
	
	public static void main(String[] args) {
		new GraphicsTest2();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		repaint(); // 每次拖动鼠标时，调用组件的重绘方法，重绘方法会自动调用组件的update方法来更新所看到图形
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		repaint(); // 每次拖动鼠标时，调用组件的重绘方法，重绘方法会自动调用组件的update方法来更新所看到图形
		
	}

}
