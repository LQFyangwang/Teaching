package com.xk.qq.ui.common;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CheckCodeGenerator {
	
	public static final int LENGTH = 5;
	public static final String CHECK_CODE_STR = "3456789abcdefghjkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY";
	
	public static CheckCode getCheckCode(){
		BufferedImage bufImage = new BufferedImage(60,30,BufferedImage.TYPE_INT_BGR); // 生成一张内存中在图片
		Graphics g = bufImage.getGraphics(); // 获取该图片的画笔对象
		String checkCode = "";
		for(int x = 5; x < 60; x+=8){
			for(int y = 60; y > 0; y -=8){
				g.drawLine(x, y, x, y);
			}
		}
		for(int i = 0; i < LENGTH; i++){
			int index = new Random().nextInt(CHECK_CODE_STR.length());
			checkCode += CHECK_CODE_STR.charAt(index);
		}
		g.drawString(checkCode, 10, 20);
		CheckCode cc = new CheckCode();
		cc.setCheckImage(bufImage);
		cc.setCheck(checkCode);
		return cc;
	}
}
