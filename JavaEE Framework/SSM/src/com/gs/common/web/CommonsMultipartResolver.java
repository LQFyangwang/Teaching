package com.gs.common.web;

import javax.servlet.http.HttpServletRequest;

public class CommonsMultipartResolver extends org.springframework.web.multipart.commons.CommonsMultipartResolver{

	@Override
	public boolean isMultipart(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if (uri.indexOf("/ueditor/core") > 0) {
			return false; // 请使用UEditor的判断方式 
		} else {
			return super.isMultipart(request); // springmvc的
		}
	}

}
