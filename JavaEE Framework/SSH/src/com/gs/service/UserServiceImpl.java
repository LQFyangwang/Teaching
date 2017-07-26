package com.gs.service;

import java.io.Serializable;

import com.gs.bean.User;
import com.gs.common.bean.Pager4EasyUI;
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
	public User save(User t) {
		return userDAO.save(t);
	}

	@Override
	public void delete(User t) {
		userDAO.delete(t);
	}

	@Override
	public void update(User t) {
		userDAO.update(t);
	}

	@Override
	public User queryById(Class<?> clazz, Serializable id) {
		return userDAO.queryById(clazz, id);
	}

	@Override
	public Pager4EasyUI<User> queryByPager(String beanName, Pager4EasyUI<User> pager) {
		return userDAO.queryByPager(beanName, pager);
	}

	@Override
	public long count() {
		return userDAO.count();
	}

}
