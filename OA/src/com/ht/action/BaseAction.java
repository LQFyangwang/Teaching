package com.ht.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 5305423996146955451L;
	
	public HttpServletRequest req;
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

}
