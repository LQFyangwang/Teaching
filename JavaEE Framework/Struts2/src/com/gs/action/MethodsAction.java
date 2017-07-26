package com.gs.action;

import com.opensymphony.xwork2.ActionSupport;

public class MethodsAction extends ActionSupport {

	private static final long serialVersionUID = 4259048974821793906L;
	
	public String addUser() {
		return "add";
	}
	
	public String queryUser() {
		return "query";
	}
	
	public String deleteUser() {
		return "delete";
	}

}
