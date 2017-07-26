package com.gs.service;

import java.io.Serializable;
import java.util.List;

import com.gs.bean.OrderInfo;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.dao.OrderInfoDAO;
import com.gs.dao.OrderInfoDAOImpl;

public class OrderInfoServiceImpl implements OrderInfoService {

	private OrderInfoDAO orderInfoDAO;
	
	public OrderInfoServiceImpl() {
		orderInfoDAO = new OrderInfoDAOImpl();
	}
	
	@Override
	public OrderInfo save(OrderInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(OrderInfo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderInfo queryById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager4EasyUI<OrderInfo> queryByPager(Pager4EasyUI<OrderInfo> pager) {
		pager = orderInfoDAO.queryByPager(pager);
		pager.setTotal(orderInfoDAO.count());
		return pager;
	}

	@Override
	public List<OrderInfo> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
