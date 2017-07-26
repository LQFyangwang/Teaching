package com.gs.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ServletAction extends ActionSupport {

	private static final long serialVersionUID = 6106254646911857857L;
	
	@SuppressWarnings("unused")
	public String execute() {
		
		ActionContext actionContext = ActionContext.getContext();
		actionContext.getParameters(); // 获取所有的请求参数，返回Map
		actionContext.getApplication(); // 获取Application， ServletContext   返回Map
		actionContext.getSession(); // 获取session内的键值对信息   返回Map
		actionContext.put("aaa", "aaa"); // 把数据存储到action上下文
		actionContext.get("aaa"); // 从action上下文中去获取数据
		
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		HttpServletResponse resp = ServletActionContext.getResponse();
		ServletContext app = ServletActionContext.getServletContext();
		PageContext pc = ServletActionContext.getPageContext();
		ActionContext ac = ServletActionContext.getActionContext(req);
		
		return SUCCESS;
	}

}
