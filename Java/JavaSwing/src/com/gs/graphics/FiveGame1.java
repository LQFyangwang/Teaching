package com.gs.graphics;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gs.listener.MyWindowListener;

public class FiveGame1 extends Frame implements MouseListener {

	public static int BLACK = 1; // 黑棋
	public static int WHITE = 2; // 白棋
	private boolean isBlack = true; // 表示是否应该下黑棋， false则表示下白棋
	private int xDes; // 棋子的目标交叉点x坐标
	private int yDes; // 棋子的目标交叉点y坐标
	private int[][] hold = new int[17][17]; // 用于统计每个交叉点是黑棋还是白棋

	public FiveGame1() {
		setSize(435,465);
		setLocationRelativeTo(null);
		addMouseListener(this);
		setResizable(false);
		setVisible(true);
		addWindowListener(new MyWindowListener());
	}

	@Override
	public void paint(Graphics g) {
		try {
			BufferedImage bufImage = ImageIO.read(this.getClass().getResource("/images/Board.gif"));
			g.drawImage(bufImage, 0, 25, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Graphics g) {
		try {
			BufferedImage img;
			if (isBlack) { // 如果下一步是黑棋，则绘制黑棋
				img = ImageIO.read(this.getClass().getResource("/images/Black.gif"));
			} else { // 否则绘制白棋
				img = ImageIO.read(this.getClass().getResource("/images/white.gif"));
			}
			g.drawImage(img, xDes - 12, yDes - 12, this); // 交叉点的位置上需要减去棋子的一半大小
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX(); // 获取鼠标所点击的位置
		int y = e.getY();
		System.out.println(y);
		int xIndex = (int) ((x - 18) / 25.0 + 0.5); // 转化为鼠标点击位置附近的交叉点的坐标所对应的索引
		int yIndex = (int) ((y - 18) / 25.0 + 0.5);

		if (hold[xIndex][yIndex] == 0) { // 判断鼠标点击附近的交叉点是否已经摆放柜子，为0表示未摆放，则可以下子
			if (isBlack) { // 如果下黑子，则记录该交叉点为黑子
				hold[xIndex][yIndex] = BLACK;
			} else { // 如果下白子，则记录该交叉点为白子
				hold[xIndex][yIndex] = WHITE;
			}
			xDes = (xIndex * 25) + 18; // 获取交叉点的坐标
			yDes = (yIndex * 25) + 18;
			repaint();
			if (isBlack) {
				isBlack = false; // 如果前一步是黑棋，则下一步不是黑棋
			} else {
				isBlack = true; // 否则下一步是黑棋
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	public static void main(String[] args) {
		new FiveGame1();
	}

}
