package com.gs.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CheckCodeUtil {
	
	public static final String CHECK_CODE = "abcdefghjkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY23456789";
	public static final int CODE_LENGTH = 5;
	
	public static final int LINE_COUNT = 5;
	
	public static final int WIDTH = 100;
	public static final int HEIGHT = 50;
	
	public static String randomCode() {
		int length = CHECK_CODE.length();
		Random ran = new Random();
		String code = "";
		for (int i = 0; i < CODE_LENGTH; i++) {
			int random = ran.nextInt(length - 1);
			if (code.equals("")) {
				code = String.valueOf(CHECK_CODE.charAt(random));
			} else {
				code += String.valueOf(CHECK_CODE.charAt(random));
			}
		}
		return code;
	}
	
	public static BufferedImage getCodeImage(String code) {
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
		g.drawString(code, 20, 30);
		drawLine(g);
		return img;
	}
	
	public static void drawLine(Graphics g) {
		g.setColor(randomColor());
		Random ran = new Random();
		for (int i = 0; i < LINE_COUNT; i++) {
			int beginX = ran.nextInt(WIDTH);
			int beginY = ran.nextInt(HEIGHT);
			int endX = ran.nextInt(WIDTH);
			int endY = ran.nextInt(HEIGHT);
			g.drawLine(beginX, beginY, endX, endY);
		}
	}
	
	public static Color randomColor() {
		Random ran = new Random();
		return new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255));
	}

}
