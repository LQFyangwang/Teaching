package com.jh.ui.common;

import java.awt.image.BufferedImage;

/**
 * 用来保存验证码图片和验证码的类
 * @author Administrator
 *
 */
public class CheckCode {

	private String checkCode; // 验证码
	private BufferedImage buffImg; // 验证码图片

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public BufferedImage getBuffImg() {
		return buffImg;
	}

	public void setBuffImg(BufferedImage buffImg) {
		this.buffImg = buffImg;
	}
}
