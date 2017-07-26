package com.ht.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ht.bean.StuLeave;
import com.ht.common.DateUtil;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.StuLeaveDAO;

public class StuLeaveDAOImpl extends AbstractBaseDAO<StuLeave> implements StuLeaveDAO {

	@Override
	public Pager4EasyUI<StuLeave> queryByPager(String beanName, Pager4EasyUI<StuLeave> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + beanName + " order by leaveDay desc");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<StuLeave> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public Pager4EasyUI<StuLeave> queryByStuId(Pager4EasyUI<StuLeave> pager, Serializable stuId) {
		
		return setPager("byStuId", pager, stuId, "", "");
	}

	@Override
	public Pager4EasyUI<StuLeave> queryByDayAndStuId(Pager4EasyUI<StuLeave> pager, Serializable stuId, String startDay,
			String endDay) {
		
		return setPager("byDay", pager, stuId, startDay, endDay);
	}
	
	private Pager4EasyUI<StuLeave> setPager(String method, Pager4EasyUI<StuLeave> pager, Serializable stuId, String startDay, String endDay) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(StuLeave.class).add(Restrictions.eq("stu.stuId", stuId));;
		if (method.equals("byDay")) {
			c.add(Restrictions.between("leaveDay", DateUtil.toDate(startDay), DateUtil.toDate(endDay)));
		}
		@SuppressWarnings("unchecked")
		List<StuLeave> list = c.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public void updateLeaveStatus(StuLeave stuLeave, String fieldName) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("update t_stuleave set " + fieldName + " = 1 where leaveid = '" + stuLeave.getLeaveId() + "'");
		query.executeUpdate();
	}

}
