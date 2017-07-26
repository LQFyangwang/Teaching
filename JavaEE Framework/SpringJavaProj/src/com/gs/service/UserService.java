package com.gs.service;

import com.gs.bean.User;

public interface UserService {
	
	public User queryByNameAndPwd(String name, String pwd);

}
