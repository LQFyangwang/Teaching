package com.gs.action;

import com.gs.bean.User;
import com.gs.service.UserService;

public class LoginAction {
	
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String execute() {
		User user = userService.queryByNameAndPwd("abc", "123456");
		System.out.println(user);
		return "query";
	}

}
