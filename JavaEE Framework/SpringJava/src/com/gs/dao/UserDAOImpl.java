package com.gs.dao;

import java.io.Serializable;

import com.gs.bean.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User queryById(Serializable id) {
		System.out.println("query by id");
		return null;
	}

}
