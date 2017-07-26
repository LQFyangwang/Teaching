package com.gs.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.gs.common.bean.Pager4EasyUI;

public abstract class AbstractBaseDAO<T> {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public T save(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.save(t);
		return t;
	}

	public void delete(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
	}

	public void update(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.update(t);
	}

	@SuppressWarnings("unchecked")
	public T queryById(Class<?> clazz, Serializable id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.load(clazz, id);
	}

	public Pager4EasyUI<T> queryByPager(String beanName, Pager4EasyUI<T> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + beanName);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) query.list();
		pager.setRows(list);
		return pager;
	}

	public long count() {
		return 0;
	}

}
