package com.gs.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.gs.common.bean.Pager4EasyUI;
import com.gs.util.HibernateUtil;

public interface BaseDAO<T> {
	
	public SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public T save(T t);
	
	public void update(T t);
	
	public T queryById(Serializable id);
	
	public Pager4EasyUI<T> queryByPager(Pager4EasyUI<T> pager);
	
	public List<T> queryAll();
	
	public long count();

}
