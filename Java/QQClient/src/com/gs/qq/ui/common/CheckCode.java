package com.gs.qq.ui.common;

import java.awt.image.BufferedImage;

public class CheckCode {
	private String checkCode;
	private BufferedImage bufImg;
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public BufferedImage getBufImg() {
		return bufImg;
	}
	public void setBufImg(BufferedImage bufImg) {
		this.bufImg = bufImg;
	}
}