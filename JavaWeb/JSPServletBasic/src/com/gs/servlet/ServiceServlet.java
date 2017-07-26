package com.gs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceServlet extends HttpServlet {

	private static final long serialVersionUID = -2093995948416680583L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // 设置HttpServletRequest对象的编码方式为utf-8，与jsp页面中的编码保持一致
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8"); // response返回的内容的类型是什么
		
		System.out.println(req.getMethod()); // 获取请求方法
		String name = req.getParameter("name");
		System.out.println(name);
		
		PrintWriter out = res.getWriter(); // 由HttpServletResponse对象获取一个输出流，该输出流输出的内容可以返回到客户端
		out.print("<!doctype html>"); // 通过out对象把数据输出到客户端 
		out.print("<html>");
		out.print("<head>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div style='color:white;background-color:black;'>");
		out.print(name);
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		out.close();
	}

}
