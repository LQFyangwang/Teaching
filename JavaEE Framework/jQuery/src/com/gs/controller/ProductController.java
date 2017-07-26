package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gs.bean.Product;

public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 8953787861682146123L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("手机", 100));
		products.add(new Product("电脑", 100));
		products.add(new Product("电视", 100));
		products.add(new Product("iphone", 100));
		products.add(new Product("华为", 100));
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json");
		String title = req.getParameter("title");
		List<Product> desProducts = new ArrayList<Product>();
		for (Product p : products) {
			String t = p.getTitle();
			if (t.contains(title)) { // contains判断某个对象是否含有这个元素
				desProducts.add(p);
			}
		}
		PrintWriter out = resp.getWriter();
		out.write(JSON.toJSONString(desProducts));
	}

}
