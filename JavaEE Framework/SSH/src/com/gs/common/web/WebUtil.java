package com.gs.common.web;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	
	public static final int PAGE_SIZE = 20;
	
	public static int getPageNo(HttpServletRequest req) {
		String pageStr = req.getParameter("page");
		if (pageStr != null && !pageStr.equals("")) {
			return Integer.valueOf(pageStr);
		}
		return 1;
	}
	
	public static int getPageSize(HttpServletRequest req) {
		String rowsPage = req.getParameter("rows");
		if (rowsPage != null && !rowsPage.equals("")) {
			return Integer.valueOf(rowsPage);
		}
		return PAGE_SIZE;
	}

}
