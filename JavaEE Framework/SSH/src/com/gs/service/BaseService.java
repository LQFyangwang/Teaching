package com.gs.service;

import java.io.Serializable;

import com.gs.common.bean.Pager4EasyUI;

public interface BaseService<T> {
	public T save(T t);

	public void delete(T t);
	
	public void update(T t);

	public T queryById(Class<?> clazz, Serializable id);

	public Pager4EasyUI<T> queryByPager(String beanName, Pager4EasyUI<T> pager);

	public long count();
}
