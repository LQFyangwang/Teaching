package com.gs.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gs.bean.Order;
import com.gs.common.bean.Pager4EasyUI;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public Order save(Order t) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(t);
		transaction.commit();
		session.close();
		return t;
	}

	@Override
	public void update(Order t) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(t);
		transaction.commit();
		session.close();
	}

	@Override
	public Order queryById(Serializable id) {
		Session session = sessionFactory.openSession();
		Order order = (Order) session.get(Order.class, id);
		return order;
	}

	@Override
	public Pager4EasyUI<Order> queryByPager(Pager4EasyUI<Order> pager) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Order");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Order> orders = query.list();
		pager.setRows(orders);
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryAll() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Order");
		return query.list();
	}

	@Override
	public long count() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select count(id) from Order");
		Long count = (Long) query.uniqueResult();
		session.close();
		return count;
	}

}
