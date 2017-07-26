package com.gs.service;

import java.io.Serializable;
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
	public Product save(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product queryById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager4EasyUI<Product> queryByPager(Pager4EasyUI<Product> pager) {
		pager = productDAO.queryByPager(pager);
		pager.setTotal(productDAO.count());
		return pager;
	}

	@Override
	public List<Product> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
