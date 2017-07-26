package com.gs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gs.bean.User;
import com.gs.common.Constants;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1471288583205694248L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		res.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		res.setContentType("text/html;charset=" + Constants.DEFAULT_ENCODING);
		
		User user = new User();
		user.setName(req.getParameter("name"));
		user.setPwd(req.getParameter("pwd"));
		user.setGender(req.getParameter("gender"));
		user.setHobby(req.getParameterValues("hobby"));
		user.setAddress(req.getParameter("address"));
		user.setCars(req.getParameterValues("cars"));
		user.setInfo(req.getParameter("info"));
		System.out.println(user);
		
		PrintWriter out = res.getWriter();
		out.print("<!doctype html>"); // 通过out对象把数据输出到客户端 
		out.print("<html>");
		out.print("<head>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div style='margin:auto;width:400px;'>");
		out.print("您已经注册成功，用户信息如下：<br />" + user.toString());
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		out.close();
		
	}

}
