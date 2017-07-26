package com.ht.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.ht.bean.Talk;
import com.ht.bean.info.TalkInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.TalkInfoDAO;

public class TalkInfoDAOImpl extends AbstractBaseDAO<TalkInfo> implements TalkInfoDAO {
	
	private Pager4EasyUI<TalkInfo> setPager(String sql, Pager4EasyUI<TalkInfo> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<TalkInfo> talkInfos = new ArrayList<TalkInfo>();
		for (Object[] objs : list) {
			TalkInfo ti = new TalkInfo();
			ti.setTalkId((String) objs[0]);
			ti.setEmpName((String) objs[1]);
			ti.setStuName((String) objs[2]);
			ti.setTalkDay((Date) objs[3]);
			ti.setDes((String) objs[4]);
			ti.setStatus((int) objs[5]);
			ti.setStuId((String) objs[6]);
			ti.setEmpId((String) objs[7]);
			talkInfos.add(ti);
		}
		pager.setRows(talkInfos);
		return pager;
	}

	@Override
	public Pager4EasyUI<TalkInfo> queryByPager(Pager4EasyUI<TalkInfo> pager) {
		String sql = "select t.talkid, e.name as emp_name, s.name as_stu_name, t.talkday, t.des, t.status, s.stuid, e.empid from t_talk t, t_emp e, t_stu s where t.empid = e.empid and t.stuid = s.stuid";
		return setPager(sql, pager);
	}

	@Override
	public long count() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Talk.class);
		long count = (long) criteria.setProjection(Projections.count("talkId")).uniqueResult();
		session.close();
		return count;
	}

	@Override
	public Pager4EasyUI<TalkInfo> queryByStuId(Pager4EasyUI<TalkInfo> pager, Serializable stuId) {
		String sql = "select t.talkid, e.name as emp_name, s.name as_stu_name, t.talkday, t.des, t.status, s.stuid, e.empid from t_talk t, t_emp e, t_stu s where t.empid = e.empid and t.stuid = s.stuid and t.stuid = '" + stuId + "'";
		return setPager(sql, pager);
	}

	@Override
	public Pager4EasyUI<TalkInfo> queryByDayAndStuId(Pager4EasyUI<TalkInfo> pager, Serializable stuId, String startDay,
			String endDay) {
		String sql = "select t.talkid, e.name as emp_name, s.name as_stu_name, t.talkday, t.des, t.status, s.stuid, e.empid from t_talk t, t_emp e, t_stu s where t.empid = e.empid and t.stuid = s.stuid and t.stuid = '" + stuId + "' and t.talkday > '" + startDay + "' and t.talkday < '" + endDay + "'";
		
		return setPager(sql, pager);
	}

}
