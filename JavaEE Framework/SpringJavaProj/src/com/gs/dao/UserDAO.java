package com.gs.dao;

import com.gs.bean.User;

public interface UserDAO {
	
	public User queryByNameAndPwd(String name, String pwd);
	

}
