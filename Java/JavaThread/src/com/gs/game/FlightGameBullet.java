package com.gs.game;

public class FlightGameBullet implements Runnable {
	
	private int x;
	private int y = 460;
	
	
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
			if (y < 0) {
				y = 460;
			}
			y -= 5;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
