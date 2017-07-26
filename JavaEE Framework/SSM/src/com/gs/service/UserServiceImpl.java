package com.gs.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gs.bean.User;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDAO userDAO;

	@Override
	public List<User> queryAll() {
		return userDAO.queryAll();
	}

	@Override
	public User queryById(Serializable id) {
		return userDAO.queryById(id);
	}

	@Override
	public void add(User t) {
		userDAO.add(t);
	}

	@Override
	public void update(User t) {
		userDAO.update(t);
	}

	@Override
	public void deleteById(Serializable id) {
		userDAO.deleteById(id);
	}

	@Override
	public Pager4EasyUI<User> queryByPagerAndCriteria(Pager4EasyUI<User> pager, User t) {
		List<User> users = userDAO.queryByPagerAndCriteria(pager, t);
		pager.setRows(users);
		pager.setTotal(userDAO.countByCriteria(t));
		return pager;
	}

}
