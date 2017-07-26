package com.gs.common;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	
	public static final String DEFAULT_UPLOADS = "uploads";
	
	public static String getUriMethod(HttpServletRequest req) {
		String uri = req.getRequestURI(); // 不包含查询字符串部分
		return uri.substring(uri.lastIndexOf("/") + 1);
	}
	
	/**
	 * 此方法去获取网站在服务器端的真实路径的根路径
	 * 
	 * getRealPath(String); 表示去获取网站某个资源的真实路径
	 * getRealPath("/");表示获取网站根路径的真实路径
	 * @param req
	 */
	public static String mkUpload(HttpServletRequest req) {
		String path = req.getServletContext().getRealPath("/");
		File file = new File(path, DEFAULT_UPLOADS);
		if (!file.exists()) {
			file.mkdir();
		}
		return file.getAbsolutePath();
	}

}
