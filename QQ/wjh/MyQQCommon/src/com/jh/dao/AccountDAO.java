package com.jh.dao;

import java.util.List;

import com.jh.bean.Account;
import com.jh.bean.Message;

public interface AccountDAO {

	/**
	 * 根据Account对象给数据库添加数据
	 * @param account
	 * @return
	 */
	public Account add(Account account); 
	
	/**
	 * 查询出所有已经注册的Account
	 * @param account
	 * @return
	 */
	public List<Account> queryAll();
	
	/**
	 * 根据账号密码查询数据库
	 * @param number
	 * @param pwd
	 * @return
	 */
	public Account query(String number, String pwd);
	
	/**
	 * 根据账号去查询数据库
	 * @param number
	 * @return
	 */
	public Account query(String number);
	
	/**
	 * 查询数据库的所有信息,出了传递进来的对象
	 * @return
	 */
	public List<Account> queryAll(Account account); 
	
	/**
	 * 根据账号或昵称查询信息
	 * @param number
	 * @param nickname
	 * @return
	 */
	public List<Account> querySingle(String number, String nickname); 
	
	/**
	 * 根据账号更新数据库的数据
	 * @param account
	 */
	public void update(Account account); 
	
	/**
	 * 根据账号去更新密码
	 * @param account
	 */
	public void updatePwd(Account account);
	/**
	 * 把数据添加到t_friends表中
	 * @param selfNumber
	 * @param number
	 */
	public void addFriends(String selfNumber, String number); 
	
	/**
	 * 根据账号去查询好友表
	 * @param number
	 * @return
	 */
	public List<Account> queryFriends(String number);

	/**
	 * 根据账号查询非离线的好友信息
	 * @param number
	 * @return
	 */
	public List<Account> queryNotOfflineFriends(String number);
	
	/**
	 * 根据账号去查询数据库t_friends表,判断这个用户是不是已经是你的好友
	 */
	public boolean queryFriend(String number, String toNumber);
	
	/**
	 * 保存消息内容
	 * @param message
	 * @return
	 */
	public Message add(Message message); 
}
