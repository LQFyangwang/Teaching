package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gs.bean.Product;
import com.gs.common.WebUtil;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.ProductService;
import com.gs.service.ProductServiceImpl;

public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 7325402790354592572L;
	
	private ProductService productService;
	
	public ProductController() {
		productService = new ProductServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getUriMethod(req);
		if (method.equals("all")) {
			queryAll(req, resp);
		} else if (method.equals("add")) {
			add(req, resp);
		} else if (method.equals("edit")) {
			edit(req, resp);
		} else if (method.equals("del")) {
			delete(req, resp);
		} else if (method.equals("pager")) {
			queryByPager(req, resp);
		}
	}
	
	private void queryAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Product> products = productService.queryAll();
		int total = productService.countAll();
		Pager4EasyUI<Product> pager = new Pager4EasyUI<Product>();
		pager.setTotal(total);
		pager.setRows(products);
		PrintWriter out = resp.getWriter();
		String json = JSON.toJSONString(pager);
		System.out.println(json);
		out.write(json);
	}
	
	private void queryByPager(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int pageNo = 1;
		int pageSize = 20;
		try {
			pageNo = Integer.valueOf(req.getParameter("page"));
			pageSize = Integer.valueOf(req.getParameter("rows"));
		} catch(NumberFormatException e) {
			
		}
		Pager4EasyUI<Product> pager = new Pager4EasyUI<>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		pager = productService.queryByPager(pager);
		PrintWriter out = resp.getWriter();
		String json = JSON.toJSONString(pager);
		System.out.println(json);
		out.write(json);
	}
	
	private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String title = req.getParameter("title");
		String priceStr = req.getParameter("price");
		String des = req.getParameter("des");
		String type = req.getParameter("type");
		System.out.println(type + "=======================================");
		Product p = new Product();
		if (priceStr != null) {
			double price = 0;
			try {
				price = Double.valueOf(priceStr);
			} catch(NumberFormatException e) {
			}
			p.setPrice(price);
		}
		p.setTitle(title);
		p.setDes(des);
		p.setType(Integer.valueOf(type));
		Product product = productService.add(p);
		PrintWriter out = resp.getWriter();
		if (product != null) {
			ControllerResult result = ControllerResult.getSuccessResult("成功添加商品");
			out.write(JSON.toJSONString(result));
		} else {
			ControllerResult result = ControllerResult.getFailResult("添加商品失败");
			out.write(JSON.toJSONString(result));
		}
	}
	
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		long id = Long.valueOf(req.getParameter("id"));
		String title = req.getParameter("title");
		String priceStr = req.getParameter("price");
		String des = req.getParameter("des");
		String type = req.getParameter("type");
		Product p = new Product();
		if (priceStr != null) {
			double price = 0;
			try {
				price = Double.valueOf(priceStr);
			} catch(NumberFormatException e) {
			}
			p.setPrice(price);
		}
		p.setId(id);
		p.setTitle(title);
		p.setDes(des);
		p.setType(Integer.valueOf(type));
		Product product = productService.edit(p);
		PrintWriter out = resp.getWriter();
		if (product != null) {
			ControllerResult result = ControllerResult.getSuccessResult("成功修改商品");
			out.write(JSON.toJSONString(result));
		} else {
			ControllerResult result = ControllerResult.getFailResult("修改商品失败");
			out.write(JSON.toJSONString(result));
		}
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		long id = Long.valueOf(req.getParameter("id"));
		productService.delete(id);
		PrintWriter out = resp.getWriter();
		out.write(JSON.toJSONString(ControllerResult.getSuccessResult("成功删除商品")));
	}

}
