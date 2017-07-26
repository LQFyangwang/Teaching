package com.gs.qq.ui.common;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CheckCodeGenerator {
	
	public static final int LENGTH = 5;
	public static final String CHECK_CODE_STR = "3456789abcdefghjkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY";
	
	public static CheckCode getCheckCode() {
		BufferedImage bufImg = new BufferedImage(60, 30, BufferedImage.TYPE_INT_RGB); // 生成一张内存中在图片
		Graphics g = bufImg.getGraphics(); // 获取该图片的画笔对象
		String checkCode = "";
		for (int i = 0; i < LENGTH; i++) {
			int index = new Random().nextInt(CHECK_CODE_STR.length());
			checkCode += CHECK_CODE_STR.charAt(index);
		}
		g.drawString(checkCode, 10, 10); // 由画笔对象把验证码画出
		CheckCode cc = new CheckCode();
		cc.setCheckCode(checkCode);
		cc.setBufImg(bufImg);
		return cc;
	}

}

