package com.ht.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ht.bean.StuChecking;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.StuCheckingDAO;

public class StuCheckingDAOImpl extends AbstractBaseDAO<StuChecking> implements StuCheckingDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Pager4EasyUI<StuChecking> queryPagerByGradeId(Pager4EasyUI<StuChecking> pager, Serializable gradeId, int month) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select stuid from t_stu where gradeid = '" + gradeId + "'");
		List<Object> list = query.list();
		List<StuChecking> stuCheckings = null;
		if (list.size() > 0) {
			Criteria c = session.createCriteria(StuChecking.class).add(Restrictions.in("stu.stuId", list)).add(Restrictions.sqlRestriction("month(checkingday) = " + month)); // Ìõ¼þ²éÑ¯
			stuCheckings = c.list();
		}
		pager.setRows(stuCheckings);
		return pager;
	}

}
