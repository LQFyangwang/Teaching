package com.gs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gs.bean.User;

public class UserController extends HttpServlet {

	private static final long serialVersionUID = 4133479893933382431L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		User user = new User();
		user.setName("test");
		user.setAge(20);
		user.setPwd("123456");
		user.setHobby("Εά²½");
		req.setAttribute("user", user);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}	
	
}
