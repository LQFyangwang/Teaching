package com.gs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gs.bean.Product;
import com.gs.service.ProductService;
import com.gs.service.ProductServiceImpl;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = -5130755934001288922L;
	
	private ProductService productService;
	
	public ProductServlet() {
		productService = new ProductServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		List<Product> products = productService.queryAll();
		for (Product p : products) {
			System.out.println(p);
		}
	}

}
