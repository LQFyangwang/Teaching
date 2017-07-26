package com.jh.ui.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.jh.ui.MainFrame;

/**
 * 验证码获取器
 * @author Administrator
 *
 */
public class CheckCodeGenerator {

	public static final int LENGTH = 4; // 验证码长度
	public static final String CHECK_CODE_STR = "3456789abcdefghjkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY"; // 随机获取的验证码
	
	public static CheckCode getCheckCode() {
		BufferedImage bi = new BufferedImage(50,30,BufferedImage.TYPE_INT_RGB); // 获取到一张图片到内存中
		Graphics g = bi.getGraphics();
		Graphics gg = null;
		BufferedImage bi1 = null;
		String checkCode = "";
		for (int i = 0; i < LENGTH; i++) {
			int index = new Random().nextInt(CHECK_CODE_STR.length()); // 在数组中随机出一个字的索引
			checkCode += CHECK_CODE_STR.charAt(index); // 将随机出来的数据追加到字符串中
		}
		try {
			bi1 = ImageIO.read(MainFrame.class.getResource("/images/verify.png")); // 获取到一张图片
			gg = bi1.getGraphics(); 
			gg.setColor(Color.RED);
			gg.setFont(new Font("微软雅黑", Font.BOLD, 12));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gg.drawString(checkCode, 8, 20); // 把随机出来的字符串画到这张图片上
		g.drawImage(bi1, 0, 0, null); // 然后把这张图片画到原先画在内存的图片中
		CheckCode cc = new CheckCode();
		cc.setBuffImg(bi); // 把这张图片保存起来
		cc.setCheckCode(checkCode); // 把验证码也保存起来
		return cc;
	}
}
