package com.gs.game;

public class GameStep {
	
	private int[][] map;
	private boolean currentSpriteIsOnDestination;
	public int[][] getMap() {
		return map;
	}
	public void setMap(int[][] map) {
		this.map = map;
	}
	public boolean isCurrentSpriteIsOnDestination() {
		return currentSpriteIsOnDestination;
	}
	public void setCurrentSpriteIsOnDestination(boolean currentSpriteIsOnDestination) {
		this.currentSpriteIsOnDestination = currentSpriteIsOnDestination;
	}
	
}
