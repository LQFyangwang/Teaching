package com.ht.common;

import org.apache.struts2.ServletActionContext;

/**
 * 用来存储常量的类
 * @author Administrator
 *
 */
public class Constants {
	
	public static final String PATH_UPLOAD = ServletActionContext.getServletContext().getRealPath("/") + "upload"; // 获取服务器上的upload目录
	
	public static final String PATH_EXCEL = ServletActionContext.getServletContext().getRealPath("/") + "excel\\"; // 获取服务器上的excel目录
	
	public static final String SALARY_NAME = "salary.xls";
	
	public static final String CHECKING_NAME = "checking.xls";
}
