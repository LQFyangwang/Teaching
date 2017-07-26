package com.gs.action;

import com.opensymphony.xwork2.ActionSupport;

public class MyInterceptorAction1 extends ActionSupport {

	private static final long serialVersionUID = -6200442463518872375L;
	
	public String addUser() {
		return SUCCESS;
	}
	
	public String queryUser() {
		return SUCCESS;
	}

}
