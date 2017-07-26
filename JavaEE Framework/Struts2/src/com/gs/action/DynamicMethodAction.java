package com.gs.action;

import com.opensymphony.xwork2.ActionSupport;

public class DynamicMethodAction extends ActionSupport {

	private static final long serialVersionUID = -7832130328612675277L;
	
	public String test1() {
		return "test1";
	}
	
	public String test2() {
		return "test2";
	}
	
	public String test3() {
		return "test3";
	}

}
