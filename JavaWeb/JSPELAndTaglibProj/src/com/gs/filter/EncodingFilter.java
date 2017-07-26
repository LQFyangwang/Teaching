package com.gs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.gs.common.Constants;

public class EncodingFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("编码过滤器销毁....");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		arg0.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		arg1.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("编码过滤器初始化....");
	}

}
