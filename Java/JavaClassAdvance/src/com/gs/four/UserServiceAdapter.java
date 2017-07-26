package com.gs.four;

/**
 * 适配器模式，增加该抽象类，解决某个想要实现接口里的部分方法的类的问题
 *
 */
public class UserServiceAdapter implements UserService {

	@Override
	public boolean login(User user) {
		return false;
	}

	@Override
	public boolean logout(User user) {
		return false;
	}

	@Override
	public User update(User user) {
		return null;
	}

	@Override
	public boolean inactive(User user) {
		return false;
	}
	
	@Override
	public boolean active(User user) {
		return false;
	}

	@Override
	public User register(User user) {
		return null;
	}

}
