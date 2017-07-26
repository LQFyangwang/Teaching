package com.gs.graphics;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FiveGamePanel extends Panel {

	private FiveGameListener gameListener;
	
	private int xDes;
	private int yDes;
	
	public void setxDes(int xDes) {
		this.xDes = xDes;
	}
	
	public void setyDes(int yDes) {
		this.yDes = yDes;
	}

	public FiveGamePanel() {
		gameListener = new FiveGameListener();
		gameListener.setPanel(this);
		addMouseListener(gameListener);
	}

	@Override
	public void paint(Graphics g) {
		try {
			BufferedImage bufImage = ImageIO.read(this.getClass().getResource("/images/Board.gif"));
			g.drawImage(bufImage, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Graphics g) {
		System.out.println("draw image...");
		try {
			BufferedImage img;
			if (gameListener.isBlack()) {
				img = ImageIO.read(this.getClass().getResource("/images/Black.gif"));
			} else {
				img = ImageIO.read(this.getClass().getResource("/images/white.gif"));
			}
			g.drawImage(img, xDes, yDes, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
