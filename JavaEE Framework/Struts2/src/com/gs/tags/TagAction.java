package com.gs.tags;

import java.util.ArrayList;
import java.util.List;

import com.gs.bean.User;
import com.opensymphony.xwork2.ActionSupport;

public class TagAction extends ActionSupport {

	private static final long serialVersionUID = -1256708247009668135L;
	
	private String test;
	private boolean ok;
	private List<User> users;
	
	
	public String getTest() {
		return test;
	}


	public void setTest(String test) {
		this.test = test;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public String execute() {
		users = new ArrayList<User>();
		User user1 = new User();
		user1.setNo(1);
		user1.setName("user1");
		user1.setPwd("123456");;
		users.add(user1);
		User user2 = new User();
		user2.setNo(2);
		user2.setName("user2");
		user2.setPwd("123456");;
		users.add(user2);
		User user3 = new User();
		user3.setNo(3);
		user3.setName("user3");
		user3.setPwd("123456");;
		users.add(user3);
		User user4 = new User();
		user4.setNo(4);
		user4.setName("user4");
		user4.setPwd("123456");;
		users.add(user4);
		// ok = true;
		return SUCCESS;
	}

}
