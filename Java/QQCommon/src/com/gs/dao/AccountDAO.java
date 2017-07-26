package com.gs.dao;

import java.util.List;

import com.gs.bean.Account;

public interface AccountDAO {
	
	public Account add(Account account);
	public Account query(Account account);
	/**
	 * 根据用户账号与密码，查找数据库中是否有该账号，如果有，则返回此账号(包含有完整的属性信息)，如果没有，则返回null
	 * @param number
	 * @param pwd
	 * @return
	 */
	public Account query(String number, String pwd);
	/**
	 * 查询所有的用户
	 * @return
	 */
	public List<Account> queryAll();
	
	/**
	 * 直接通过账号查找信息
	 * @param number
	 * @return
	 */
	public Account query(String number);
	/**
	 * 添加好友，每一个参数是自己的账号，第二个参数是好友的账号
	 */
	public void addFriend(String selfNumber, String number);
	
	/**
	 * 根据自己的账号去查找该账号的所有好友
	 * @param number
	 * @return
	 */
	public List<Account> queryFriends(String number);
	
	/**
	 * 查找所有非离线的好友
	 * @param number
	 * @return
	 */
	public List<Account> queryNotLeaveFriends(String number);
	
	/**
	 * 根据用户账号修改状态
	 * @param status
	 */
	public void updateStatus(String number, String status);

}
