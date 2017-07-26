package com.ht.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.Dept;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.DeptTypeDAO;

public class DeptTypeDAOImpl extends AbstractBaseDAO<Dept> implements DeptTypeDAO {

	@Override
	public List<Dept> queryNameAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Dept");
		@SuppressWarnings("unchecked")
		List<Dept> dept = query.list();
		return dept;
	}

	

}
