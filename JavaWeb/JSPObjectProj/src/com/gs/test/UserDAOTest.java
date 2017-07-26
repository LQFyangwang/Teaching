package com.gs.test;

import org.junit.Test;

import com.gs.bean.User;
import com.gs.dao.UserDAO;
import com.gs.dao.UserDAOImpl;

public class UserDAOTest {
	
	@Test
	public void testQueryByNamePwd() {
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.queryByNamePwd("Wgssmart", "123456");
		System.out.println(user);
	}

}
