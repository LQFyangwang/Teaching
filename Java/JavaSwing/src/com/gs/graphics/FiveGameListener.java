package com.gs.graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FiveGameListener extends MouseAdapter {
	
	public static int BLACK = 1;
	public static int WHITE = 2;
	
	private int[][] hold = new int[17][17];
	
	private boolean isBlack = true;
	
	private FiveGamePanel panel;
	
	public void setPanel(FiveGamePanel panel) {
		this.panel = panel;
	}
	
	public boolean isBlack() {
		return isBlack;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int xIndex = (int) ((x - 18) / 25.0 + 0.5);
		int yIndex = (int) ((y - 18) / 25.0 + 0.5);
		
		if (hold[xIndex][yIndex] == 0){
			if (isBlack) {
				hold[xIndex][yIndex] = BLACK;
			} else {
				hold[xIndex][yIndex] = WHITE;
			}
			int xDes = (xIndex * 25) + 18;
			int yDes = (yIndex * 25) + 18;
			System.out.println("mouse clicked..." + xDes + ", " + yDes);
			panel.setxDes(xDes - 12);
			panel.setyDes(yDes - 12);
			panel.repaint();
			if (isBlack) {
				isBlack = false;
			} else {
				isBlack = true;
			}
		}
		
	}
	
}
