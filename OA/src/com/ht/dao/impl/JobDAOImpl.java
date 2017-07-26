package com.ht.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ht.bean.Job;
import com.ht.common.DateUtil;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.JobDAO;

public class JobDAOImpl extends AbstractBaseDAO<Job> implements JobDAO {

	@Override
	public Pager4EasyUI<Job> queryByStuId(Pager4EasyUI<Job> pager, Serializable stuId) {
		return setPager("byStuId", pager, stuId, "", "");
	}

	@Override
	public Pager4EasyUI<Job> queryByDayAndStuId(Pager4EasyUI<Job> pager, Serializable stuId, String startDay,
			String endDay) {
		return setPager("byDay", pager, stuId, startDay, endDay);
	}
	
	@SuppressWarnings("unchecked")
	private Pager4EasyUI<Job> setPager(String method, Pager4EasyUI<Job> pager, Serializable stuId, String startDay, String endDay) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Job.class).add(Restrictions.eq("stu.stuId", stuId));;
		if (method.equals("byDay")) {
			c.add(Restrictions.between("hireDay", DateUtil.toDate(startDay), DateUtil.toDate(endDay)));
		}
		List<Job> scores = c.list();
		pager.setRows(scores);
		return pager;
	}

}
