package com.gs.service;

import java.io.Serializable;

import com.gs.bean.User;

public interface UserService {
	
	public User queryById(Serializable id);

}
