package com.gs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gs.bean.Product;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.WebUtil;
import com.gs.service.ProductService;
import com.gs.service.ProductServiceImpl;
import com.gs.service.UserService;
import com.gs.service.UserServiceImpl;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = -8664490495294262812L;
	
	private UserService userService;
	private ProductService productService;
	
	public UserServlet() {
		userService = new UserServiceImpl();
		productService = new ProductServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		resp.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		String method = WebUtil.getUriMethod(req);
		if (method.equals("addcart")) {
			// TODO 添加到购物车
			addCart(req, resp);
		} else if (method.equals("login")) {
			// TODO 登录
			login(req, resp);
		} else if (method.equals("register")) {
			// TODO 注册
		} else if (method.equals("login_page")) { // 显示登录页面
			showLoginPage(req, resp);
		} else if (method.equals("home")) { // 显示主页
			showHomePage(req, resp);
		} else if (method.equals("showcart")) {
			showCartPage(req, resp);
		} else if (method.equals("remove")) {
			removeCart(req, resp);
		}
		
	}
	
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		if (name != null && !name.equals("") && pwd != null && !pwd.equals("")) {
			User user = userService.queryByNamePwd(name, pwd);
			System.out.println(user);
			if (user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				resp.sendRedirect("home");
			} else {
				resp.sendRedirect("login_page");
			}
		}
	}
	
	private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object userObj = session.getAttribute("user");
		if (userObj == null) {
			resp.sendRedirect("login_page");
		} else {
			// 开始查询所有商品信息，并把商品信息放到request中，转发到home.jsp后，在home.jsp中通过request获取商品信息
			
			List<Product> products = productService.queryAll();
			req.setAttribute("products", products);
			
			req.getRequestDispatcher("/home.jsp").forward(req, resp);
		}
	}
	
	private void addCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Object user = session.getAttribute("user");
		if (user != null) {
			String idStr = req.getParameter("id");
			int id = 0;
			try {
				id = Integer.valueOf(idStr);
			} catch (NumberFormatException e) {
				
			}
			Product product = productService.queryById(id);
			Object obj = session.getAttribute("productsInCart");
			if (obj != null) { // 此时购物车中已经有商品
				List<Product> products = (List) obj;
				products.add(product);
				session.setAttribute("productsInCart", products);
			} else {
				List<Product> products = new ArrayList<Product>();
				products.add(product);
				session.setAttribute("productsInCart", products);
			}
			resp.sendRedirect("home");
		} else {
			resp.sendRedirect("login_page");
		}
	}
	
	private void showCartPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("user");
		if (obj != null) {
			req.getRequestDispatcher("/cart.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("login_page");
		}
	}
	
	private void removeCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Object user = session.getAttribute("user");
		if (user != null) {
			String idStr = req.getParameter("id");
			int id = 0;
			try {
				id = Integer.valueOf(idStr);
			} catch (NumberFormatException e) {
				
			}
			Object obj = session.getAttribute("productsInCart");
			if (obj != null) {
				List<Product> products = (List) obj;
				Iterator<Product> ite = products.iterator();
				while (ite.hasNext()) {
					Product p = ite.next();
					if (p.getId() == id) {
						ite.remove();
						break;
					}
				}
			}
			resp.sendRedirect("showcart");
		} else {
			resp.sendRedirect("login_page");
		}
	}

}
