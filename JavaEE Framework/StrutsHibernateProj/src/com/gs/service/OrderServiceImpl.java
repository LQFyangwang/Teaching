package com.gs.service;

import java.io.Serializable;
import java.util.List;

import com.gs.bean.Order;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.dao.OrderDAO;
import com.gs.dao.OrderDAOImpl;

public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDAO;
	
	public OrderServiceImpl() {
		orderDAO = new OrderDAOImpl();
	}
	
	@Override
	public Order save(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Order t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Order queryById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager4EasyUI<Order> queryByPager(Pager4EasyUI<Order> pager) {
		pager = orderDAO.queryByPager(pager);
		pager.setTotal(orderDAO.count());
		return pager;
	}

	@Override
	public List<Order> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
