package com.ht.dao;

import java.io.Serializable;
import java.util.List;

import com.ht.common.bean.Pager4EasyUI;

public interface BaseDAO<T> {

	/**
	 * 根据传递进来的对象保存对象到数据库
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * 根据传递进来对象删除数据库中的数据
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * 根据传递进来的对象更新数据库中的数据
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 根据Id查询数据库中的数据
	 * @param clazz
	 * @param s
	 * @return
	 */
	public T queryById(Class<?> clazz, Serializable s);
	
	/**
	 * MySQL的方式分页查询数据库中的数据
	 * @param beanName
	 * @param pager
	 * @return
	 */
	public Pager4EasyUI<T> queryByPager(String beanName, Pager4EasyUI<T> pager);
	
	/**
	 * 根据传递进来的简单类名查询数据库中的所有数据
	 * @return
	 */
	public List<T> queryAll(String beanName);
	
	/**
	 * 计算传递进来对象的所有数据的总个数
	 * @param clazz
	 * @return
	 */
	public long count(String beanName);
	
	/**
	 * 根据对象的简单类名和id字段的名称，和状态来更新状态
	 * @param beanName 简单类名
	 * @param idName 类所对应的id属性名
	 * @param status 状态
	 * @param id id
	 * @return
	 */
	public void updateStatus(String beanName, String idName, int status, String id);
	
	/**
	 * 根据学生姓名和类对象的简单类名模糊搜索数据，注意：必须是和Stu形成多对一的关系才可以调用此方法
	 * @param pager 分页查询
	 * @param stuName 学生姓名
	 * @param beanObject 类的简单类名
	 * @return
	 */
	public Pager4EasyUI<T> queryByStuName(Pager4EasyUI<T> pager, Serializable stuName, Serializable beanObject);
	
	/**
	 * 根据员工姓名和类对象的简单类名模糊搜索数据，注意：必须是和Emp形成多对一的关系才可以调用此方法
	 * @param pager 分页查询
	 * @param empName 员工姓名
	 * @param beanObject 类的简单类名
	 * @return
	 */
	public Pager4EasyUI<T> queryByEmpName(Pager4EasyUI<T> pager, Serializable empName, Serializable beanObject);
	
	/**
	 * 根据时间段搜索，根据传递进来的开始时间和结束时间，和对象的简单类名，和开始对象中时间的属性名称进行搜索
	 * @param pager 分页搜索
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @param beanObject 对象的简单类名
	 * @param attrName 对象中需要匹配的属性名
	 * @return
	 */
	public Pager4EasyUI<T> queryByDay(Pager4EasyUI<T> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName);
}
