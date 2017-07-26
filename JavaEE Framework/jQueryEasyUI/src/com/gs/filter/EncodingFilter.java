package com.gs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.gs.common.Constants;

/**
 * 编码过滤器，在执行任何servlet前，把该serlvet所用到的request和response对象的编码设置成
 * utf-8
 *
 */
public class EncodingFilter implements Filter {
	
	private String encoding;

	@Override
	public void destroy() {
		System.out.println("编码过滤器销毁....");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) // FilterChain表示过滤器链
			throws IOException, ServletException {
		System.out.println("编码过滤器开始过滤");
		if (encoding == null) {
			encoding = Constants.DEFAULT_ENCODING;
		}
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		// chain.doFilter的作用是开始做过滤，并且传递到下一个过滤器，如果没有下一个过滤器，则传递给Servlet
		// chain.doFilter前的代码叫做前置方法
		chain.doFilter(req, resp);
		// chain.doFilter后的代码叫做后置方法
		System.out.println("编码过滤器过滤完毕");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		encoding = arg0.getInitParameter("encoding");
		System.out.println("编码过滤器初始化...");
	}

}
