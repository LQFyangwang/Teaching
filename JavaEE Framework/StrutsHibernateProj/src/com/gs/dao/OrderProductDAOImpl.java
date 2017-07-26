package com.gs.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gs.bean.OrderProduct;
import com.gs.common.bean.Pager4EasyUI;

public class OrderProductDAOImpl implements OrderProductDAO {

	@Override
	public OrderProduct save(OrderProduct t) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(t);
		transaction.commit();
		return t;
	}

	@Override
	public void update(OrderProduct t) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderProduct queryById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager4EasyUI<OrderProduct> queryByPager(Pager4EasyUI<OrderProduct> pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderProduct> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
