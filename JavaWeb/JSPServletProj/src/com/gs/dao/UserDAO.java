package com.gs.dao;

import com.gs.bean.User;

public interface UserDAO {
	
	public User queryByNamePwd(String name, String pwd);

}
