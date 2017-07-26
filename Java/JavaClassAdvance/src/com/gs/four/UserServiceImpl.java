package com.gs.four;

public class UserServiceImpl extends UserServiceAdapter {

	@Override
	public boolean login(User user) {
		if (user.getEmail().equals("123@126.com") && user.getPassword().equals("123")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean logout(User user) {
		return true;
	}

	@Override
	public User update(User user) {
		user.setNickname("AAA");
		return user;
	}

	@Override
	public User register(User user) {
		user.setEmail("234@126.com");
		user.setPassword("123");
		return user;
	}

}
