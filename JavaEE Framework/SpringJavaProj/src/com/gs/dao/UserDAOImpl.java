package com.gs.dao;

import com.gs.bean.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User queryByNameAndPwd(String name, String pwd) {
		if (name.equals("abc") && pwd.equals("123456")) {
			User u = new User();
			u.setId(1);
			u.setName("abc");
			u.setPwd("123456");
			return u;
		}
		return null;
	}

}
