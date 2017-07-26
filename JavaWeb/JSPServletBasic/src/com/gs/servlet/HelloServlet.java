package com.gs.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的Servlet类继承自javax.serlvet.http.HttpServlet类
 */
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = -470818723804064268L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("HelloServlet初始化.....");
	}
	
	@Override
	public void destroy() {
		System.out.println("HelloServlet被销毁.....");
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("HelloServlet正在处理...hello!");
	}
	
	
}
