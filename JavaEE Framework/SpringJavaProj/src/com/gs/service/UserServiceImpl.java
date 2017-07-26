package com.gs.service;

import com.gs.bean.User;
import com.gs.dao.UserDAO;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User queryByNameAndPwd(String name, String pwd) {
		return userDAO.queryByNameAndPwd(name, pwd);
	}

	
	
}
