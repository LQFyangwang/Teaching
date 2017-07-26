package com.gs.service;

import com.gs.bean.User;

public interface UserService {
	
	public User queryByNamePwd(String name, String pwd);

}
