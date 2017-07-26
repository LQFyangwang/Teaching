package com.gs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gs.bean.Product;
import com.gs.bean.User;
import com.gs.common.WebUtil;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8664490495294262812L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getUriMethod(req);
		if (method.equals("login_page")) {
			showLoginPage(req, resp);
		} else if (method.equals("login")) {
			login(req, resp);
		} else if (method.equals("home")) {
			showHomePage(req, resp);
		}
	}

	private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		if (name != null && !name.equals("") && pwd != null && !pwd.equals("")) {
			User user = new User();
			user.setName(name);
			user.setPwd(pwd);
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			
			List<Product> products = new ArrayList<Product>();
			Product p1 = new Product();
			p1.setName("p1");
			Product p2 = new Product();
			p2.setName("p2");
			products.add(p1);
			products.add(p2);
			
			session.setAttribute("products", products);
			
			resp.sendRedirect("home");
		} else {
			req.setAttribute("errorMsg", "用户名或密码错误，请重新输入");
			HttpSession session = req.getSession();
			session.setAttribute("errorMsg", "session error msg");
			showLoginPage(req, resp); // 如果出现登录错误，则转发到登录页面
		}
	}
	
	private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}

}
