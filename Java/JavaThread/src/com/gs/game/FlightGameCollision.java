package com.gs.game;

import java.util.ArrayList;
import java.util.List;

public class FlightGameCollision implements Runnable {
	
	private FlightGameBullet bullet;
	private FlightGamePanel panel;
	
	private List<Integer> toRemove;
	
	public FlightGameCollision(FlightGameBullet bullet, FlightGamePanel panel) {
		this.bullet = bullet;
		this.panel = panel;
		toRemove = new ArrayList<Integer>();
	}
	
	public int collision() {
		int bulletX = bullet.getX();
		int bulletY = bullet.getY();
		for (int i = 0, size = panel.getGes().size(); i < size; i++) {
			int x = panel.getGes().get(i).getX();
			int y = panel.getGes().get(i).getY();
			if ((bulletX >= x && bulletX <= x + 35 && bulletY <= y && bulletY >= y - 35)
					|| (bulletX + 18 >= x && bulletX + 18 <= x + 35 && bulletY <= y && bulletY >= y - 35)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void run() {
		while (true) {
			int index = collision();
			if (index >= 0){
				toRemove.add(index);
				panel.setToRemove(toRemove);
				panel.repaint();
				panel.focus();
			}
		}
	}

}
