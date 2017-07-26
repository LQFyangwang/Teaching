package com.gs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gs.common.WebUtil;

public class ProductFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("商品过滤器销毁。。。。。");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("商品过滤器开始过滤");
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		String method = WebUtil.getUriMethod(req);
		if (method.equals("add")) {
			String name = req.getParameter("name");
			String priceStr = req.getParameter("price");
			if (name == null || name.equals("") || priceStr == null || priceStr.equals("")) {
				resp.sendRedirect("add_page");
			} else {
				chain.doFilter(arg0, arg1);
			}
		} else {
			chain.doFilter(arg0, arg1);
		}
		System.out.println("商品过滤器过滤完毕");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("商品过滤器初始化....");
	}

}
