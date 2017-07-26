package com.gs.service;

import java.util.List;

import com.gs.bean.Product;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.dao.ProductDAO;
import com.gs.dao.ProductDAOImpl;

public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		productDAO = new ProductDAOImpl();
	}

	@Override
	public List<Product> queryAll() {
		return productDAO.queryAll();
	}

	@Override
	public int countAll() {
		return productDAO.countAll();
	}
	
	@Override
	public Product add(Product product) {
		return productDAO.add(product);
	}
	
	@Override
	public Product edit(Product product) {
		return productDAO.edit(product);
	}
	
	@Override
	public void delete(long id) {
		productDAO.delete(id);
	}
	
	@Override
	public Pager4EasyUI<Product> queryByPager(Pager4EasyUI<Product> pager) {
		return productDAO.queryByPager(pager);
	}

}
