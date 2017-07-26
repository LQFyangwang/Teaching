package com.gs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendRedirectToPage extends HttpServlet {

	private static final long serialVersionUID = 8145993980857952343L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendRedirect("send_redirect.jsp"); //　不需要以/开头
	}
	
}
