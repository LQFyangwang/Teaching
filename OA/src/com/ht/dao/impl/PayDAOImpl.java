package com.ht.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.Pay;
import com.ht.bean.PayType;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.PayDAO;

public class PayDAOImpl extends AbstractBaseDAO<Pay> implements PayDAO {

	@Override
	public PayType queryByType(String typeName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PayType where name='" + typeName + "'");
		return (PayType) query.uniqueResult();
	}

}
