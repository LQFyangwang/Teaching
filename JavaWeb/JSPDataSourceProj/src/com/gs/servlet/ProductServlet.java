package com.gs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gs.bean.Pager;
import com.gs.bean.Product;
import com.gs.common.Constants;
import com.gs.common.WebUtil;
import com.gs.service.ProductService;
import com.gs.service.ProductServiceImpl;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = -5130755934001288922L;
	
	private ProductService productService;
	
	public ProductServlet() {
		productService = new ProductServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		resp.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		String method = WebUtil.getUriMethod(req);
		if (method.equals("all")) {
			// 查询出所有商品
			queryAll(req, resp);
		} else if (method.equals("pager")) {
			queryByPager(req, resp);
		}
	}

	private void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = productService.queryAll();
		req.setAttribute("products", products);
		req.getRequestDispatcher("/products.jsp").forward(req, resp);
	}
	
	private void queryByPager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo");
		final int pageSize = 5;
		int pageNo = 1; // 默认为第1页
		int total = productService.count();
		int totalPage = (total % pageSize) == 0 ? total / pageSize : (total / pageSize + 1);
		if (pageNoStr != null) {
			try {
				pageNo = Integer.valueOf(pageNoStr);
				if (pageNo <= 0) {
					pageNo = 1;
				} else if (pageNo > totalPage){
					pageNo = totalPage;
				}
			} catch (NumberFormatException e) {
				
			}
		}
		Pager<Product> pager = productService.queryByPager(pageNo, pageSize);
		req.setAttribute("products", pager.getResult());
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("totalPage", totalPage);
		req.getRequestDispatcher("/products_pager.jsp").forward(req, resp);
	}

}
