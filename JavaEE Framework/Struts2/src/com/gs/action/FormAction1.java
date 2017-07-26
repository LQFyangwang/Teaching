package com.gs.action;

import com.gs.bean.User;
import com.opensymphony.xwork2.ActionSupport;

public class FormAction1 extends ActionSupport {
	
	private static final long serialVersionUID = 7588814322657208341L;
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {
		System.out.println(user.getName() + ": " + user.getPwd());
		return "success";
	}

}
