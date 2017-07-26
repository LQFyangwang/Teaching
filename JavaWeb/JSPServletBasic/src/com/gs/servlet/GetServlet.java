package com.gs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetServlet extends HttpServlet {

	private static final long serialVersionUID = -7158864282162908342L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("get");
		String name = req.getParameter("name"); // 获取指定名称为name的请求参数的值
		if (name != null && !name.equals("")) {
			System.out.println(name);
		} else {
			System.out.println("没有name");
		}
	}

}
