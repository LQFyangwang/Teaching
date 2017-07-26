package com.gs.service;

import java.util.List;

import com.gs.bean.User;

public interface UserService {
public List<User> queryAll();
	
	public User queryByEmailAndPwd(String email, String pwd);

}
