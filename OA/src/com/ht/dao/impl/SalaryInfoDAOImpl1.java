package com.ht.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.ht.bean.Salary;
import com.ht.bean.info.SalaryInfo1;
import com.ht.common.SalaryUtil;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.SalaryInfoDAO1;

public class SalaryInfoDAOImpl1 extends AbstractBaseDAO<SalaryInfo1> implements SalaryInfoDAO1 {

	private Pager4EasyUI<SalaryInfo1> setPager(Pager4EasyUI<SalaryInfo1> pager, String sql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<SalaryInfo1> salaryInfos = new ArrayList<SalaryInfo1>();
		if (list != null && list.size() > 0) {
			for (Object[] objs : list) {
				SalaryInfo1 si = new SalaryInfo1();
				si.setEmpId((String) objs[0]);
				si.setEmpName((String) objs[1]);
				si.setBasicSalary((double) objs[2]);
				si.setJobSalary((double) objs[3]);
				si.setExtraSalary((double) objs[4]);
				si.setSubSalary((double) objs[5]);
				si.setSalaryDay((Date) objs[6]);
				si.setTotalSalary((double) objs[7]);
				si.setSalaryId((String) objs[8]);
				si.setSalaryInfoId((String) objs[9]);
				si.setShouldSalary(SalaryUtil.shouldSalary(si.getBasicSalary(), si.getJobSalary(), si.getExtraSalary(), si.getSubSalary()));
				salaryInfos.add(si);
			}
			pager.setRows(salaryInfos);
			return pager;
		}
		return null;
	}
	
	@Override
	public Pager4EasyUI<SalaryInfo1> queryByPager(Pager4EasyUI<SalaryInfo1> pager) {
		String sql = "select e.empid, e.name, si.basicsalary, si.jobsalary, s.extrasalary, s.subsalary, s.salaryday, s.totalsalary, s.salaryid, si.salaryinfoid "
				+ "from t_emp e, t_salaryinfo si, t_salary s "
				+ "where e.empid = si.empid and e.empid = s.empid";
		return setPager(pager, sql);
	}

	@Override
	public long count() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Salary.class);
		long count = (long) criteria.setProjection(Projections.count("salaryId")).uniqueResult();
		session.close();
		return count;
	}

	@Override
	public Pager4EasyUI<SalaryInfo1> queryByDay(Pager4EasyUI<SalaryInfo1> pager, Serializable startDay,
			Serializable endDay) {
		String sql = "select e.empid, e.name, si.basicsalary, si.jobsalary, s.extrasalary, s.subsalary, s.salaryday, s.totalsalary, s.salaryid, si.salaryinfoid "
				+ "from t_emp e, t_salaryinfo si, t_salary s "
				+ "where s.salaryday between '" + startDay + "' and '" + endDay + "' and e.empid = si.empid and e.empid = s.empid";
		return setPager(pager, sql);
	}

	@Override
	public Pager4EasyUI<SalaryInfo1> queryByName(Pager4EasyUI<SalaryInfo1> pager, Serializable empName) {
		String sql = "select e.empid, e.name, si.basicsalary, si.jobsalary, s.extrasalary, s.subsalary, s.salaryday, s.totalsalary, s.salaryid, si.salaryinfoid "
				+ "from t_emp e, t_salaryinfo si, t_salary s "
				+ "where e.name like '%" + empName + "%' and e.empid = si.empid and e.empid = s.empid";
		return setPager(pager, sql);
	}

	@Override
	public Pager4EasyUI<SalaryInfo1> queryPageEmpId(Pager4EasyUI<SalaryInfo1> pager, Serializable empId) {
		String sql = "select e.empid, e.name, si.basicsalary, si.jobsalary, s.extrasalary, s.subsalary, s.salaryday, s.totalsalary, s.salaryid, si.salaryinfoid "
				+ "from t_emp e, t_salaryinfo si, t_salary s "
				+ "where e.empid = '" + empId + "' and e.empid = si.empid and e.empid = s.empid";
		return setPager(pager, sql);
	}

	@Override
	public Pager4EasyUI<SalaryInfo1> queryByDayAndEmpId(Pager4EasyUI<SalaryInfo1> pager, Serializable startDay,
			Serializable endDay, Serializable empId) {
		String sql = "select e.empid, e.name, si.basicsalary, si.jobsalary, s.extrasalary, s.subsalary, s.salaryday, s.totalsalary, s.salaryid, si.salaryinfoid "
				+ "from t_emp e, t_salaryinfo si, t_salary s "
				+ "where s.salaryday between '" + startDay + "' and '" + endDay + "' and e.empid = si.empid and e.empid = s.empid and e.empid = '" + empId + "'";
		return setPager(pager, sql);
	}


}
