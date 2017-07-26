package com.gs.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gs.bean.User;
import com.gs.common.bean.Pager4EasyUI;

public class UserDAOImpl implements UserDAO {

	@Override
	public User save(User t) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(t);
		transaction.commit();
		session.close();
		return t;
	}

	@Override
	public void update(User t) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(t);
		transaction.commit();
		session.close();
	}

	@Override
	public User queryById(Serializable id) {
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, id);
		return user;
	}

	@Override
	public Pager4EasyUI<User> queryByPager(Pager4EasyUI<User> pager) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<User> users = query.list();
		pager.setRows(users);
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryAll() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User");
		return query.list();
	}

	@Override
	public long count() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select count(id) from User");
		Long count = (Long) query.uniqueResult();
		session.close();
		return count;
	}

}
