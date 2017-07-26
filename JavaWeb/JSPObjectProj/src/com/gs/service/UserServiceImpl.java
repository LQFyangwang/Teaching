package com.gs.service;

import com.gs.bean.User;
import com.gs.dao.UserDAO;
import com.gs.dao.UserDAOImpl;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	public UserServiceImpl() {
		userDAO = new UserDAOImpl();
	}
	
	@Override
	public User queryByNamePwd(String name, String pwd) {
		return userDAO.queryByNamePwd(name, pwd); // 如果获取到用户后，还需要对用户数据进行加工，则可以在此service方法中完成
	}

}
