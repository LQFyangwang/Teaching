package com.gs.dao;

import java.io.Serializable;

import com.gs.bean.User;

public interface UserDAO {
	
	public User queryById(Serializable id);

}
