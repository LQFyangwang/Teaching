package com.gs.action;

import com.opensymphony.xwork2.ActionSupport;

public class MyInterceptorAction extends ActionSupport {

	private static final long serialVersionUID = -6200442463518872375L;
	
	public String execute() {
		return SUCCESS;
	}

}
