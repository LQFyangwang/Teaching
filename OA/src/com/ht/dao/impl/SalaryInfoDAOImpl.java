package com.ht.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.SalaryInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.SalaryInfoDAO;

public class SalaryInfoDAOImpl extends AbstractBaseDAO<SalaryInfo> implements SalaryInfoDAO {

	@Override
	public Pager4EasyUI<SalaryInfo> querySalaryInfoPage(String id, Pager4EasyUI<SalaryInfo> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select * from t_salaryinfo n where n.empid = "+id);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<SalaryInfo> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public long countEmp(String id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(s.empid) from t_salaryinfo s where s.empid = "+id);
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

}
