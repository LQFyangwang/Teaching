package com.ht.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ht.bean.StuFeedback;
import com.ht.common.DateUtil;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.StuFeedbackDAO;

public class StuFeedbackDAOImpl extends AbstractBaseDAO<StuFeedback> implements StuFeedbackDAO {

	@Override
	public Pager4EasyUI<StuFeedback> queryByStuId(Pager4EasyUI<StuFeedback> pager, Serializable stuId) {
		return setPager("byStuId", pager, stuId, "", "");
	}

	@Override
	public Pager4EasyUI<StuFeedback> queryByDayAndStuId(Pager4EasyUI<StuFeedback> pager, Serializable stuId, String startDay,
			String endDay) {
		return setPager("byDay", pager, stuId, startDay, endDay);
	}
	
	private Pager4EasyUI<StuFeedback> setPager(String method, Pager4EasyUI<StuFeedback> pager, Serializable stuId, String startDay, String endDay) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(StuFeedback.class).add(Restrictions.eq("stu.stuId", stuId));;
		if (method.equals("byDay")) {
			c.add(Restrictions.between("feedbackDay", DateUtil.toDate(startDay), DateUtil.toDate(endDay)));
		}
		@SuppressWarnings("unchecked")
		List<StuFeedback> list = c.list();
		pager.setRows(list);
		return pager;
	}

}
