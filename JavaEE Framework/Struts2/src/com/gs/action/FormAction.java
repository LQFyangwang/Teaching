package com.gs.action;

import com.opensymphony.xwork2.ActionSupport;

public class FormAction extends ActionSupport {

	private static final long serialVersionUID = -5219311730508481727L;
	
	private String name;
	private String pwd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String execute() {
		System.out.println(name + ":" + pwd);
		return "success";
	}

}
