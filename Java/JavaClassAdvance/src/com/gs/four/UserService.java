package com.gs.four;

public interface UserService {
	
	/**
	 * 用户登录操作，如果登录成功返回true，否则返回false
	 * @param user
	 * @return
	 */
	public boolean login(User user);
	
	public boolean logout(User user);
	
	/**
	 * 根据传递进来的用户对象更新用户的属性，并返回更新后的user对象
	 * @param user
	 * @return
	 */
	public User update(User user);
	
	/**
	 * 把传递进来的user设置为非法状态
	 * @param user
	 * @return
	 */
	public boolean inactive(User user);
	
	public boolean active(User user);
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public User register(User user);

}
