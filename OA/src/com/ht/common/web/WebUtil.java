package com.ht.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ht.bean.Emp;
import com.ht.bean.Stu;

/**
 * 用于获取Web参数的工具类
 * @author Administrator
 *
 */
public class WebUtil {
	
	public static final int PAGE_NO = 1;
	public static final int PAGE_SIZE = 20;

	/**
	 * 获取页面页数
	 * @param req
	 * @return
	 */
	public static int getPageNo(HttpServletRequest req) {
		String pageNoStr = req.getParameter("page");
		if (pageNoStr != null && !pageNoStr.equals("")) {
			return Integer.valueOf(pageNoStr);
		}
		return PAGE_NO;
	}
	
	/**
	 * 获取每页显示的大小
	 * @param req
	 * @return
	 */
	public static int getPageSize(HttpServletRequest req) {
		String pageSizeStr = req.getParameter("rows");
		if (pageSizeStr != null && !pageSizeStr.equals("")) {
			return Integer.valueOf(pageSizeStr);
		}
		return PAGE_SIZE;
	}
	
	/**
	 * 获取当前登入的角色
	 * @return
	 */
	public static String getRole(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		if (emp != null) {
			return emp.getRole().getName();
		} else {
			return null;
		}
	}
	
	/**
	 * 获取当前登入账号的角色id
	 * @param req
	 * @return
	 */
	public static String getRoleId(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("emp");
		if (obj == null) {
			obj = session.getAttribute("stu");
		}
		if (obj != null) {
			if (obj instanceof Emp) {
				Emp emp = (Emp) obj;
				return emp.getRole().getRoleId();
			} else if (obj instanceof Stu) {
				Stu stu = (Stu) obj;
				return stu.getRoleId();
			}
		}
		return null;
	}
	
	/**
	 * 获取当前的emp
	 * @param req
	 * @return
	 */
	public static Emp getEmp(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return (Emp) session.getAttribute("emp");
	}
	
	/**
	 * 获取当前登入的学生id
	 * @param req
	 * @return
	 */
	public static String getStuId(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Stu stu = (Stu) session.getAttribute("stu");
		if (stu != null) {
			return stu.getStuId();
		}
		return null;
	}
	
}
