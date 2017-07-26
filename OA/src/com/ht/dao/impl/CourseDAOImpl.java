package com.ht.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.Course;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.CourseDAO;

public class CourseDAOImpl extends AbstractBaseDAO<Course> implements CourseDAO{

	@Override
	public Pager4EasyUI<Course> queryByCourseName(Pager4EasyUI<Course> pager, String courseName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Course where name like ?");
		query.setParameter(0, "%" + courseName + "%");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Course> list = query.list();
		if (list.size() > 0) {
			pager.setRows(list);
		}
		return pager;
	}


}
