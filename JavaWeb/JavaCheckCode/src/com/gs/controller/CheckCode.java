package com.gs.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gs.util.CheckCodeUtil;

public class CheckCode extends HttpServlet {

	private static final long serialVersionUID = -5373133483032243552L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = CheckCodeUtil.randomCode();
		BufferedImage img = CheckCodeUtil.getCodeImage(code);
		HttpSession session = req.getSession();
		session.setAttribute("checkCode", code);
		resp.setContentType("image/jpeg");
		ServletOutputStream out = resp.getOutputStream();
		ImageIO.write(img, "jpg", out);
		out.flush();
		out.close();
	}

}
