package com.gs.game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameUtil {
	
	public static final int HOUSE = 1;
	public static final int GRASS = 2;
	public static final int BOX = 3;
	public static final int DES_BOX = 9;
	public static final int DESTINATION = 4;
	public static final int SPRITE_DOWN = 5;
	public static final int SPRITE_UP = 8;
	public static final int SPRITE_LEFT = 6;
	public static final int SPRITE_RIGHT = 7;
	
	public static int[][] getLevel(int level) {
		return new GameMap().getLevel(level);
	}
	
	public static BufferedImage[] getImages() {
		BufferedImage[] images = new BufferedImage[10];
		for (int i = 0; i <= 9; i++) {
			try {
				BufferedImage img = ImageIO.read(GameMap.class.getResource("/images/" + i + ".gif"));
				images[i] = img;
			} catch (IOException e) {
				System.out.println("指定的图片无法读入");
				e.printStackTrace();
			}
		}
		return images;
	}
	
	/**
	 * 获取人所在的格子的行与列
	 * 
	 * @param map 地图数据
	 * @return
	 */
	public static int[] getSprite(int[][] map) {
		for (int i = 0; i < Constants.TOTAL; i++) {
			for (int j = 0; j < Constants.TOTAL; j++) {
				if (map[i][j] == SPRITE_DOWN || map[i][j] == SPRITE_LEFT || map[i][j] == SPRITE_RIGHT || map[i][j] == SPRITE_UP) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}

}
