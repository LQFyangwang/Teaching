package com.ht.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ht.bean.Score;
import com.ht.common.DateUtil;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.ScoreDAO;

public class ScoreDAOImpl extends AbstractBaseDAO<Score> implements ScoreDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Pager4EasyUI<Score> queryPagerByGradeId(Pager4EasyUI<Score> pager, Serializable gradeId, Serializable courseId) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select stuid from t_stu where gradeid = '" + gradeId + "'");
		List<Object> list = query.list();
		List<Score> scores = null;
		if (list.size() > 0) {
			Criteria c = session.createCriteria(Score.class).add(Restrictions.in("stu.stuId", list)).add(Restrictions.eq("course.courseId", courseId)); // Ìõ¼þ²éÑ¯
			scores = c.list();
		}
		pager.setRows(scores);
		return pager;
	}

	@Override
	public Pager4EasyUI<Score> queryByStuId(Pager4EasyUI<Score> pager, Serializable stuId) {
		return setPager("queryByStuId", pager, stuId, "", "");
	}

	@Override
	public Pager4EasyUI<Score> queryByDayAndStuId(Pager4EasyUI<Score> pager, Serializable stuId, String startDay,
			String endDay) {
		return setPager("queryByDay", pager, stuId, startDay, endDay);
	}
	
	@SuppressWarnings("unchecked")
	private Pager4EasyUI<Score> setPager(String method, Pager4EasyUI<Score> pager, Serializable stuId, String startDay, String endDay) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Score.class).add(Restrictions.eq("stu.stuId", stuId));;
		if (method.equals("queryByDay")) {
			c.add(Restrictions.between("testDay", DateUtil.toDate(startDay), DateUtil.toDate(endDay)));
		}
		
		List<Score> scores = c.list();
		pager.setRows(scores);
		return pager;
	}

}
