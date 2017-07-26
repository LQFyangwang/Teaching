package com.gs.service;

import java.util.List;

import com.gs.bean.ProductType;
import com.gs.dao.ProductTypeDAO;
import com.gs.dao.ProductTypeDAOImpl;

public class ProductTypeServiceImpl implements ProductTypeService {
	
	private ProductTypeDAO productTypeDAO;
	
	public ProductTypeServiceImpl() {
		productTypeDAO = new ProductTypeDAOImpl();
	}

	@Override
	public List<ProductType> queryAll() {
		return productTypeDAO.queryAll();
	}

}
