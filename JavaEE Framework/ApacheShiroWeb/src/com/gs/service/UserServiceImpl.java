package com.gs.service;

import java.util.List;

import com.gs.bean.User;
import com.gs.dao.UserDAO;
import com.gs.dao.UserDAOImpl;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOImpl();
	
	@Override
	public List<User> queryAll() {
		return userDAO.queryAll();
	}

	@Override
	public User queryByEmailAndPwd(String email, String pwd) {
		return userDAO.queryByEmailAndPwd(email, pwd);
	}

}
