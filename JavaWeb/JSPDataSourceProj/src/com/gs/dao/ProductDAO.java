package com.gs.dao;

import java.util.List;

import com.gs.bean.Pager;
import com.gs.bean.Product;

public interface ProductDAO {
	
	public List<Product> queryAll();
	
	public Pager<Product> queryByPager(int pageNo, int pageSize);

	public int count();
	
}
