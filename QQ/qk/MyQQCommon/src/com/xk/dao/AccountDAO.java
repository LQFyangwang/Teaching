package com.xk.dao;

import java.util.List;

import com.xk.bean.Account;

public interface AccountDAO {
	
	/**
	 *   // 添加信息到数据库，注册时用到
	 * @param status
	 */
	public Account add(Account account); // 添加信息到数据库，注册时用到
	
	/**
	 *   查询是否有这个账号密码 ， 登陆时用到
	 * @param status
	 */
	public Account query(String number,String pwd);  // 查询是否有这个账号密码 ， 登陆时用到
	/**
	 *  查询是否有这个账号，有则在生成一个账号，注册生成账号是用到
	 * @param status
	 */
	
	public boolean query(String number);	// 查询是否有这个账号，有则在生成一个账号，注册生成账号是用到
	/**
	 * 更新用户所有信息
	 * @param status
	 */
	
	public void update(Account account);	// 更新信息，
	
	/**
	 * 查询所有账号并放到list列表中
	 * @param status
	 */
	
	public List<Account> queryAll(String number);		// 查询所有账号并放在list列表中
	/**
	 * 根据用账号昵称来查数据库有没有这个人
	 * @param status
	 */
	
	public List<Account> queryAll(String number, String nickName); // 根据账号昵称来查数据库有这个账号
	/**
	 * 根据账号查询这个人
	 * @param status
	 */
	
	public Account queryFriend(String number);  // 根据账号查询这个人
	/**
	 * 添加好友
	 * @param status
	 */
	
	public void addFriend(String selfNumber, String number); // 添加好友
	/**
	 * 查询好友列表
	 * @param status
	 */
	
	public List<Account> queryFroemds(String number); // 查询好友列表
	
	/**
	 * 不能重复添加好友
	 * @param status
	 */
	
	
	public boolean queryFriend(String number,String toNumber);
	
	/**
	 * 删除好友
	 * @param status
	 */
	
	public void deleteFriend(String number,String toNumber);
	
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
	
	public void updateStatus(String number,String status);
	
	/**
	 * 添加至黑名单
	 * @param status
	 */
	
	public void addBlack(String selfNumber,String number);
	
	/**
	 * 查询黑名单表
	 * @param status
	 */
	public List<Account> BlackList(String number);
	
	
	/**
	 * 从黑名单里添加回来变成好友
	 * @param status
	 */
	
	public void deleteBalck(String number,String toNumber);
} 
