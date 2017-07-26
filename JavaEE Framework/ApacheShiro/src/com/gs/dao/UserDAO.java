package com.gs.dao;

import java.util.List;

import com.gs.bean.User;

public interface UserDAO {
	
	public List<User> queryAll();
	
	public User queryByEmailAndPwd(String email, String pwd);

}
