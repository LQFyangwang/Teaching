package com.gs.game;

import java.util.Random;

public class FlightGameEnemy implements Runnable {
	
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void run() {
		while (true) {
			if (y > 600) {
				y = 0;
			}
			y += new Random().nextInt(10);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
