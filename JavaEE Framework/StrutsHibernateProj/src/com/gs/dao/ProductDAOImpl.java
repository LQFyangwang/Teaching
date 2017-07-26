package com.gs.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.gs.bean.Product;
import com.gs.common.bean.Pager4EasyUI;

public class ProductDAOImpl implements ProductDAO {

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
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Product");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Product> orders = query.list();
		pager.setRows(orders);
		return pager;
	}

	@Override
	public List<Product> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select count(id) from Product");
		Long count = (Long) query.uniqueResult();
		session.close();
		return count;
	}

}
