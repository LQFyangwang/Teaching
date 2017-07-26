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
import com.gs.bean.ProductType;
import com.gs.common.WebUtil;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.service.ProductTypeService;
import com.gs.service.ProductTypeServiceImpl;

public class ProductTypeController extends HttpServlet {

	private static final long serialVersionUID = 7325402790354592572L;
	
	private ProductTypeService productTypeService;
	
	public ProductTypeController() {
		productTypeService = new ProductTypeServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = WebUtil.getUriMethod(req);
		if (method.equals("all")) {
			queryAll(req, resp);
		}
	}
	
	private void queryAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<ProductType> productTypes = productTypeService.queryAll();
		PrintWriter out = resp.getWriter();
		List<ComboBox4EasyUI> result = new ArrayList<ComboBox4EasyUI>();
		for (ProductType pt : productTypes) {
			ComboBox4EasyUI combo = new ComboBox4EasyUI();
			combo.setId(String.valueOf(pt.getId()));
			combo.setText(pt.getName());
			result.add(combo);
		}
		out.write(JSON.toJSONString(result));
	}
	
}
