package com.gs.four;

public class AdminServiceImpl extends UserServiceAdapter {

	@Override
	public boolean inactive(User user) {
		user.setActive(false);
		return true;
	}
	
	@Override
	public boolean active(User user) {
		user.setActive(true);
		return true;
	}
}
