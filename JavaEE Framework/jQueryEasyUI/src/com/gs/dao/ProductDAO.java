package com.gs.dao;

import java.util.List;

import com.gs.bean.Product;
import com.gs.common.bean.Pager4EasyUI;

public interface ProductDAO {
	
	public List<Product> queryAll();
	public int countAll();
	
	public Product add(Product product);
	
	public Product edit(Product product);
	
	public void delete(long id);
	
	public Pager4EasyUI<Product> queryByPager(Pager4EasyUI<Product> pager);

}
