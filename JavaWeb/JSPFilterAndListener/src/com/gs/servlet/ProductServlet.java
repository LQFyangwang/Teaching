package com.gs.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gs.bean.Product;
import com.gs.common.WebUtil;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = -5130755934001288922L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getUriMethod(req);
		if (method.equals("add_page")) {
			showAddPage(req, resp);
		} else if (method.equals("add")) {
			addProduct(req, resp);
		}
	}

	private void showAddPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/product_add.jsp").forward(req, resp);
	}

	private void addProduct(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("name");
		String priceStr = req.getParameter("price");
		float price = 0.f;
		if (priceStr != null) {
			try {
				price = Float.valueOf(priceStr); 
			} catch (NumberFormatException e) {
			}
		}
		String des = req.getParameter("des");
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setDes(des);
		System.out.println(product);
		ServletContext sc = req.getServletContext();
		System.out.println(sc.getAttribute("max_file_size"));
		sc.setAttribute("max_file_size", "100");
		sc.removeAttribute("max_file_size");
	}

}
