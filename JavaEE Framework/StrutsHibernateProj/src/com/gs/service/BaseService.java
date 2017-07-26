package com.gs.service;

import java.io.Serializable;
import java.util.List;

import com.gs.common.bean.Pager4EasyUI;

public interface BaseService<T> {
	
	public T save(T t);
	
	public void update(T t);
	
	public T queryById(Serializable id);
	
	public Pager4EasyUI<T> queryByPager(Pager4EasyUI<T> pager);
	
	public List<T> queryAll();
	
	public long count();

}
