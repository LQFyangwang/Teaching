package com.ht.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.Grade;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.GradeDAO;

public class GradeDAOImpl extends AbstractBaseDAO<Grade> implements GradeDAO {

	@Override
	public Pager4EasyUI<Grade> queryByGradeName(Pager4EasyUI<Grade> pager, String gradeName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Grade where name like ?");
		query.setParameter(0, "%" + gradeName + "%");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Grade> list = query.list();
		if (list.size() > 0) {
			pager.setRows(list);
		}
		return pager;
	}

}
