package com.ht.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.info.IncomeInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.IncomeInfoDAO;

public class IncomeInfoDAOImpl extends AbstractBaseDAO<IncomeInfo> implements IncomeInfoDAO {

	@Override
	public Pager4EasyUI<IncomeInfo> queryIncomeInfoPage(int status, Pager4EasyUI<IncomeInfo> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select i.incomeid,e.name as en,it.name as itn,i.incomeday,i.des,i.income,s.name as sn,it.status,it.incometypeid,s.stuid "
				+ "from t_income i,t_incometype it,t_stu s,t_emp e "
				+ "where i.incometypeid=it.incometypeid and i.stuid=s.stuid and i.empid=e.empid and it.status="+status);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<IncomeInfo> pis = new ArrayList<IncomeInfo>();
		for(Object[] obj : list) {
			IncomeInfo pi = new IncomeInfo();
			pi.setIncomeId((String) obj[0]);
			pi.setEmpName((String) obj[1]);
			pi.setIncomeTypeName((String) obj[2]);
			pi.setIncomeDay((Date) obj[3]);
			pi.setDes((String) obj[4]);
			pi.setIncome((double) obj[5]);
			pi.setStuName((String) obj[6]);
			pi.setIncomeTypeStatus((int) obj[7]);
			pi.setIncomeTypeId((String) obj[8]);
			pi.setStuId((String) obj[9]);
			pis.add(pi);
		}
		pager.setRows(pis);
		return pager;
	}

	@Override
	public long countStatus(int status) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(pt.incometypeid) from t_income p,t_incometype pt where p.incometypeid=pt.incometypeid and pt.status = "+status);
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public Pager4EasyUI<IncomeInfo> queryPageName(Pager4EasyUI<IncomeInfo> pager, String name, String value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select i.incomeid,e.name as en,it.name as itn,i.incomeday,i.des,i.income,s.name as sn "
				+ "from t_income i,t_incometype it,t_stu s,t_emp e "
				+ "where i.incometypeid=it.incometypeid and i.stuid=s.stuid and i.empid=e.empid "
				+ "and "+name+" like '%"+value+"%'");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<IncomeInfo> pis = new ArrayList<IncomeInfo>();
		for(Object[] obj : list) {
			IncomeInfo pi = new IncomeInfo();
			pi.setIncomeId((String) obj[0]);
			pi.setEmpName((String) obj[1]);
			pi.setIncomeTypeName((String) obj[2]);
			pi.setIncomeDay((Date) obj[3]);
			pi.setDes((String) obj[4]);
			pi.setIncome((double) obj[5]);
			pi.setStuName((String) obj[6]);
			pis.add(pi);
		}
		pager.setRows(pis);
		return pager;
	}

	@Override
	public long countName(String name, String value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(i.empid) from t_income i,t_incometype it,t_stu s,t_emp e "
				+ "where i.incometypeid=it.incometypeid and i.stuid=s.stuid and i.empid=e.empid "
				+ "and "+name+" like '%"+value+"%'");
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public Pager4EasyUI<IncomeInfo> queryPageDay(Pager4EasyUI<IncomeInfo> pager, Serializable startDay, Serializable endDay) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select i.incomeid,e.name as en,it.name as itn,i.incomeday,i.des,i.income,s.name as sn,it.status "
				+ "from t_income i,t_incometype it,t_stu s,t_emp e "
				+ "where i.incometypeid=it.incometypeid and i.stuid=s.stuid and i.empid=e.empid "
				+ "and i.incomeday between '" + startDay + "' and '" + endDay + "'";
		Query query = session.createSQLQuery(sql);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<IncomeInfo> pis = new ArrayList<IncomeInfo>();
		for(Object[] obj : list) {
			IncomeInfo pi = new IncomeInfo();
			pi.setIncomeId((String) obj[0]);
			pi.setEmpName((String) obj[1]);
			pi.setIncomeTypeName((String) obj[2]);
			pi.setIncomeDay((Date) obj[3]);
			pi.setDes((String) obj[4]);
			pi.setIncome((double) obj[5]);
			pi.setStuName((String) obj[6]);
			pi.setIncomeTypeStatus((int) obj[7]);
			pis.add(pi);
		}
		pager.setRows(pis);
		return pager;
	}

}
