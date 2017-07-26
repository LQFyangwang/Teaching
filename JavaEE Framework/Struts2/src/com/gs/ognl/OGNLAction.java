package com.gs.ognl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gs.bean.User;
import com.opensymphony.xwork2.ActionSupport;

public class OGNLAction extends ActionSupport {

	private static final long serialVersionUID = -2374202046076137773L;
	
	private String name;
	private int age;
	private User user;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {
		name = "ongl";
		age = 5;
		user = new User();
		user.setName("ÄãºÃ");
		user.setPwd("123456");
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("user", user);
		ServletContext sc = ServletActionContext.getServletContext();
		sc.setAttribute("user", user);
		return SUCCESS;
	}

}
