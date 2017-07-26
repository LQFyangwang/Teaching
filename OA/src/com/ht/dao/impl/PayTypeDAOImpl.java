package com.ht.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.PayType;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.PayTypeDAO;

public class PayTypeDAOImpl extends AbstractBaseDAO<PayType> implements PayTypeDAO {

	@Override
	public Pager4EasyUI<PayType> queryFreeze(String name, int status, Pager4EasyUI<PayType> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from "+name+" n where n.status = "+status);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<PayType> list = query.list();
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
	public Pager4EasyUI<PayType> queryPayTypeName(Pager4EasyUI<PayType> pager, String name, String value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PayType where "+name+" like '%"+value+"%'");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<PayType> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public List<PayType> queryType(int status) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select p.paytypeid,p.name from t_paytype p where p.status = "+status);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<PayType> pts = new ArrayList<PayType>();
		for(Object[] obj : list) {
			PayType pt = new PayType();
			pt.setPayTypeId((String) obj[0]);
			pt.setName((String) obj[1]);
			pts.add(pt);
		}
		return pts;
	}

	@Override
	public long countName(String name, String value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(paytypeid) from t_paytype where "+name+" like '%"+value+"%'");
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

}
