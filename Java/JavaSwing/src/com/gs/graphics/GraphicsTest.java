package com.gs.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import com.gs.listener.MyWindowListener;

public class GraphicsTest extends Frame implements MouseMotionListener {
	
	private int x;
	private int y;
	
	public GraphicsTest() {
		setSize(800, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		addMouseMotionListener(this);
		addWindowListener(new MyWindowListener());
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED); // 给当前画笔设置颜色
		g.drawLine(100, 100, 200, 100); // 画直线 起点的x,y。 终点的x,y
		g.drawLine(100, 100, 100, 200);
		g.drawRect(100, 200, 100, 50); // 画方形 左上用的x,y   方形的宽和高
		g.draw3DRect(100, 300, 100, 50, false); // 画方形
		g.drawRect(100, 400, 100, 50);
		g.drawLine(150, 425, 150, 425);
		g.drawArc(100, 400, 100, 50, 0, 270); // 画弧形
		g.setFont(new Font("楷体", Font.PLAIN, 20));
		g.drawString("我是字符串", 100, 500); // 绘制字符串， String想要绘制的字符串，   字符串开始的左上角的x,y坐标
		g.drawChars(new char[]{'a', 'b', 'c', 'd'},  1, 2, 100, 550); // 输出指定开始位置的指定长度字符
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResource("/images/1.jpg"));
//			Toolkit kit = Toolkit.getDefaultToolkit();
//			Image img1 = kit.getImage("map.png");
//			g.drawImage(img1, 100, 600, null);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g.drawPolygon(new int[]{310, 340, 420, 450, 500, 510}, 
						new int[]{200, 220, 310, 330, 400, 420}, 6); // 多边开
		g.drawOval(300, 300, 100, 200); // 圆形
		g.drawRoundRect(400, 500, 100, 200, 5, 30); // 画圆角矩形
		super.paint(g);
	}
	
	@Override
	public void update(Graphics g) {
		g.drawLine(x, y, x, y);
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResource("/images/1.jpg"));
			g.drawImage(img, x, y, null); // 跟随鼠标画图，但是有个问题，没有把原先的图片擦除
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GraphicsTest();
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
