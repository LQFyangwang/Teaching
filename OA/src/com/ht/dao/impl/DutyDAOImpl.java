package com.ht.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ht.bean.Duty;
import com.ht.bean.info.TodayDutyInfo;
import com.ht.common.DateUtil;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.DutyDAO;

public class DutyDAOImpl extends AbstractBaseDAO<Duty> implements DutyDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Pager4EasyUI<Duty> queryByPager2(Pager4EasyUI<Duty> pager) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Duty.class).add(Restrictions.sqlRestriction("dutyid!='' order by weekday "));
		criteria.setFirstResult(pager.getBeginIndex());
		criteria.setMaxResults(pager.getPageSize());
		List<Duty> list = criteria.list();
		pager.setRows(list);
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TodayDutyInfo> queryByToday() {
		Session session = sessionFactory.getCurrentSession();
		String today = DateUtil.getWeekOfDate(DateUtil.getDate()); // 今天的星期
//		Query query = session.createQuery("from Duty where weekDay =:weekDay");
//		query.setParameter("weekDay", today);
//		Duty duty = (Duty) query.uniqueResult();
		SQLQuery query = session.createSQLQuery("select e.name, d.add1, e.phone from t_emp e, t_duty d where e.empid = d.empid1 " + 
				"and d.weekday = '" + today + "' " +
				"UNION " +
				"select e.name, d.add2, e.phone from t_emp e, t_duty d where e.empid = d.empid2 " +
				"and d.weekday = '" + today + "' " +
				"UNION " +
				"select e.name, d.add3, e.phone from t_emp e, t_duty d where e.empid = d.empid3 " +
				"and d.weekday = '" + today + "' " +
				"UNION " +
				"select e.name, d.add4, e.phone from t_emp e, t_duty d where e.empid = d.empid4 " +
				"and d.weekday = '" + today + "';");
		List<Object[]> objs = query.list();
		List<TodayDutyInfo> tdis = new ArrayList<TodayDutyInfo>();
		for (Object[] obj : objs) {
			TodayDutyInfo tdi = new TodayDutyInfo();
			tdi.setName((String) obj[0]);
			tdi.setAdd((String) obj[1]);
			tdi.setPhone((String) obj[2]);
			tdis.add(tdi);
		}
		return tdis;
	}
	
}
