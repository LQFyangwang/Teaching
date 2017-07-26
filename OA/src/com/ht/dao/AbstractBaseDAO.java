package com.ht.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ht.common.bean.Pager4EasyUI;

/**
 * 用于实现一些基本的DAO操作
 * @author Administrator
 *
 * @param <T> 泛型，传进来的对象
 */
public abstract class AbstractBaseDAO<T> implements BaseDAO<T> {
	
	public SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.save(t);
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
	public T queryById(Class<?> clazz, Serializable s) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.load(clazz, s);
	}

	public Pager4EasyUI<T> queryByPager(String beanName, Pager4EasyUI<T> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + beanName);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		pager.setRows(list);
		return pager;
	}
	
	public List<T> queryAll(String beanName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + beanName);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
	}

	public long count(String beanName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from " + beanName);
//		SQLQuery sqlQuery = session.createSQLQuery("select coutn(*) from " + beanName);
		long count = (long) query.uniqueResult();
		return count;
	}
	
	public void updateStatus(String beanName, String idName, int status, String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update " + beanName + " set status = :status where " + idName + " = :id";
		Query query = session.createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public Pager4EasyUI<T> queryByStuName(Pager4EasyUI<T> pager, Serializable stuName, Serializable beanObject) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select temp from " + beanObject + " as temp, Stu as s where s.name like :name and temp.stu.stuId = s.stuId");
		query.setParameter("name", "%" + stuName + "%");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		List<T> t = query.list();
		if (t.size() > 0) {
			pager.setRows(t);
		}
		return pager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager4EasyUI<T> queryByEmpName(Pager4EasyUI<T> pager, Serializable empName, Serializable beanObject) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select temp from " + beanObject + " as temp, Emp as e where e.name like :name and temp.emp.empId = e.empId");
		query.setParameter("name", "%" + empName + "%");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		List<T> t = query.list();
		if (t.size() > 0) {
			pager.setRows(t);
		}
		return pager;
	}

	
	@SuppressWarnings("unchecked")
	public Pager4EasyUI<T> queryByDay(Pager4EasyUI<T> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + beanObject + " where " + attrName + " > ? and " + attrName + " < ?");
		query.setString(0, (String) startDay);
		query.setString(1, (String) endDay);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		List<T> t = query.list();
		if (t.size() > 0) {
			pager.setRows(t);
		}
		return pager;
	}

}
