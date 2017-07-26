package com.gs.service;

import java.io.Serializable;

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
	public User queryById(Serializable id) {
		userDAO.queryById(id);
		return null;
	}

}
