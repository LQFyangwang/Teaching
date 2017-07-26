package com.gs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gs.bean.Product;

public class TaglibServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8647643541786977538L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("user", "user");
		List<Product> products = new ArrayList<Product>();
		Product p1 = new Product();
		p1.setName("product 1");
		products.add(p1);
		Product p2 = new Product();
		p2.setName("product 2");
		products.add(p2);
		req.setAttribute("products", products);
		req.getRequestDispatcher("/taglib.jsp").forward(req, resp);
	}

}
