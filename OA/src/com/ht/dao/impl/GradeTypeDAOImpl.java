package com.ht.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.Grade;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.GradeTypeDAO;

public class GradeTypeDAOImpl extends AbstractBaseDAO<Grade> implements GradeTypeDAO{

	@Override
	public List<Grade> queryNameAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Grade");
		@SuppressWarnings("unchecked")
		List<Grade> grade = query.list();
		return grade;
	}

}
