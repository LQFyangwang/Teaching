package com.ht.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.ht.bean.IncomeType;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.IncomeTypeDAO;

public class IncomeTypeDAOImpl extends AbstractBaseDAO<IncomeType> implements IncomeTypeDAO {

	@Override
	public Pager4EasyUI<IncomeType> queryFreeze(String name, int status, Pager4EasyUI<IncomeType> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from "+name+" n where n.status = "+status);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<IncomeType> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public long countStatus(String beanName, int status) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(id) from "+beanName+" where status = "+status);
		long count = (Long) query.uniqueResult();
		return count;
	}

	@Override
	public Pager4EasyUI<IncomeType> queryIncomeTypeName(Pager4EasyUI<IncomeType> pager, String name, String value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from IncomeType where "+name+" like '%"+value+"%'");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<IncomeType> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public List<IncomeType> queryType(int status) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select p.incometypeid,p.name from t_incometype p where p.status = "+status);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<IncomeType> pts = new ArrayList<IncomeType>();
		for(Object[] obj : list) {
			IncomeType pt = new IncomeType();
			pt.setIncomeTypeId((String) obj[0]);
			pt.setName((String) obj[1]);
			pts.add(pt);
		}
		return pts;
	}

	@Override
	public long countName(String name, String value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(incometypeid) from t_incometype where "+name+" like '%"+value+"%'");
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public String queryByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select incometypeid from t_incometype where name = '" + name + "'");
		return (String) query.uniqueResult();
	}

}
