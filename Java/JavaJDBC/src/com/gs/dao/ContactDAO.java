package com.gs.dao;

import java.util.List;

import com.gs.bean.Contact;

public interface ContactDAO {
	
	/**
	 * 根据id查找指定的联系人
	 * @param id
	 * @return
	 */
	public Contact queryById(int id);
	
	/**
	 * 查找所有联系人
	 * @return
	 */
	public List<Contact> queryAll();
	
	/**
	 * 添加联系人，如果添加成功，则返回此联系人，否则返回空
	 * @param contact
	 * @return
	 */
	public Contact add(Contact contact);
	
	/**
	 * 根据id删除联系人
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 更新联系人
	 * @return
	 */
	public void update(Contact contact);
	
}
