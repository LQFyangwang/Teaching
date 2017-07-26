package com.gs.service;

import java.io.Serializable;
import java.util.List;

import com.gs.common.bean.Pager4EasyUI;

public interface BaseService<T> {
	
	public List<T> queryAll();
	
	public T queryById(Serializable id);
	
	public void add(T t);
	
	public void update(T t);
	
	public void deleteById(Serializable id);
	
	public Pager4EasyUI<T> queryByPagerAndCriteria(Pager4EasyUI<T> pager, T t);

}
