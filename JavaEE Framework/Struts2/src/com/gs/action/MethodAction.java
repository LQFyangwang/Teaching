package com.gs.action;

import com.opensymphony.xwork2.ActionSupport;

public class MethodAction extends ActionSupport {
	
	private static final long serialVersionUID = 8780814659181570359L;
	
	public String method() {
		System.out.println("MethodAction mehtod()");
		return SUCCESS;
	}

}
