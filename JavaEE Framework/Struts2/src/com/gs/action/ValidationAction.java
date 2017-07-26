package com.gs.action;

import com.opensymphony.xwork2.ActionSupport;

public class ValidationAction extends ActionSupport {

	private static final long serialVersionUID = -3317226351342526148L;
	
	private String name;
	private Integer age;
	private String email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String execute() {
		System.out.println(name + ", " + age + ", " + email);
		return SUCCESS;
	}

}
