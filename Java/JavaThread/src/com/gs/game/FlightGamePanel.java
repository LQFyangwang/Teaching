package com.gs.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FlightGamePanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 9153002995681289637L;

	private List<FlightGameEnemy> ges;
	private FlightGameBullet bullet;
	private PaintThread paint;
	
	private List<Integer> toRemove;
	
	private FlightGameCollision collision;
	
	private int meX = 175;
	
	public FlightGamePanel() {
		setLayout(null);
		setBackground(Color.BLACK);
		focus();
		addKeyListener(this);
		ges = new ArrayList<FlightGameEnemy>();
		FlightGameEnemy ge = new FlightGameEnemy();
		ge.setX(100);
		FlightGameEnemy ge1 = new FlightGameEnemy();
		ge1.setX(200);
		FlightGameEnemy ge2 = new FlightGameEnemy();
		ge2.setX(300);
		ges.add(ge);
		ges.add(ge1);
		ges.add(ge2);
		new Thread(ge).start();
		new Thread(ge1).start();
		new Thread(ge2).start();
		bullet = new FlightGameBullet();
		bullet.setX(meX + 17);
		collision = new FlightGameCollision(bullet, this);
		new Thread(collision).start();
		new Thread(bullet).start();
		paint = new PaintThread(this);
		new Thread(paint).start();
	}
	
	public List<FlightGameEnemy> getGes() {
		return ges;
	}

	public void setGes(List<FlightGameEnemy> ges) {
		this.ges = ges;
	}

	public List<Integer> getToRemove() {
		return toRemove;
	}

	public void setToRemove(List<Integer> toRemove) {
		this.toRemove = toRemove;
	}

	@Override
	public void paint(Graphics g) {
		try {
			// 先创建一张存在于内存中的缓冲图片
			BufferedImage canvas = new BufferedImage(400, 600, BufferedImage.TYPE_INT_RGB);
			// 去获取内存中缓冲图片所对应的画笔对象，由此获取的画笔所绘制的图形是画在缓冲图上
			Graphics gg = canvas.getGraphics();
			BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("/images/dj.gif"));
			BufferedImage meImg = ImageIO.read(this.getClass().getResourceAsStream("/images/me.gif"));
			BufferedImage bulletImg = ImageIO.read(this.getClass().getResourceAsStream("/images/bullet.PNG"));
			if (toRemove != null) {
				BufferedImage enemyOver = ImageIO.read(this.getClass().getResourceAsStream("/images/dj_over.gif"));
				for (Integer i : toRemove) {
					System.out.println("********" + i);
					FlightGameEnemy e = ges.remove(i.intValue());
					gg.drawImage(enemyOver, e.getX(), e.getY(), null);
				}
				toRemove = null;
			}
			for (int i = 0, size = ges.size(); i < size; i++) {
				gg.drawImage(img, ges.get(i).getX(), ges.get(i).getY(), null);
			}
			gg.drawImage(bulletImg, bullet.getX(), bullet.getY(), null);
			gg.drawImage(meImg, meX, 480, null);
			// 使用面板对象的画笔把内存中的缓冲图片画到面板上
			g.drawImage(canvas, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			meX -= 8;
			break;

		case KeyEvent.VK_RIGHT:
			meX += 8;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	public void focus() {
		setFocusable(true);
		requestFocus();
	}
}

class PaintThread implements Runnable {

	private FlightGamePanel gp;
	
	public PaintThread(FlightGamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void run() {
		while (true) {
			gp.repaint();
			gp.focus();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
