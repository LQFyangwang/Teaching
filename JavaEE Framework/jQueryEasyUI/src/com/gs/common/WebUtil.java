package com.gs.common;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	
	public static String getUriMethod(HttpServletRequest req) {
		String uri = req.getRequestURI(); // 不包含查询字符串部分
		return uri.substring(uri.lastIndexOf("/") + 1);
	}

}
