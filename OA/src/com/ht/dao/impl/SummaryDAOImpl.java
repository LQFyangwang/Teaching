package com.ht.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ht.bean.Summary;
import com.ht.common.DateUtil;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.SummaryDAO;

public class SummaryDAOImpl extends AbstractBaseDAO<Summary> implements SummaryDAO {

	@Override
	public Pager4EasyUI<Summary> queryByStuId(Pager4EasyUI<Summary> pager, Serializable stuId) {
		return setPager("", pager, stuId, "", "");
	}

	@Override
	public Pager4EasyUI<Summary> queryByDayAndStuId(Pager4EasyUI<Summary> pager, Serializable stuId, String startDay,
			String endDay) {
		return setPager("byDay", pager, stuId, startDay, endDay);
	}
	
	private Pager4EasyUI<Summary> setPager(String method, Pager4EasyUI<Summary> pager, Serializable stuId, String startDay, String endDay) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Summary.class).add(Restrictions.eq("stu.stuId", stuId));;
		if (method.equals("byDay")) {
			c.add(Restrictions.between("summaryDay", DateUtil.toDate(startDay), DateUtil.toDate(endDay)));
		}
		@SuppressWarnings("unchecked")
		List<Summary> list = c.list();
		pager.setRows(list);
		return pager;
	}

}
