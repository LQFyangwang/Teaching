package com.ht.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.info.PayInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.PayInfoDAO;

public class PayInfoDAOImpl extends AbstractBaseDAO<PayInfo> implements PayInfoDAO {

	@Override
	public Pager4EasyUI<PayInfo> queryPayInfoPage(int status, Pager4EasyUI<PayInfo> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select p.payid,e.name as en,pt.name as ptn,p.payday,p.des,p.pay,p.toname,p.tophone,pt.status,pt.paytypeid "
				+ "from t_emp e,t_pay p,t_paytype pt "
				+ "where p.empid=e.empid and p.paytypeid=pt.paytypeid and pt.status="+status);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<PayInfo> pis = new ArrayList<PayInfo>();
		for(Object[] obj : list) {
			PayInfo pi = new PayInfo();
			pi.setPayId((String) obj[0]);
			pi.setEmpName((String) obj[1]);
			pi.setPayTypeName((String) obj[2]);
			pi.setPayDay((Date) obj[3]);
			pi.setDes((String) obj[4]);
			pi.setPay((double) obj[5]);
			pi.setToName((String) obj[6]);
			pi.setToPhone((String) obj[7]);
			pi.setPayTypeStatus((int) obj[8]);
			pi.setPayTypeId((String) obj[9]);
			pis.add(pi);
		}
		pager.setRows(pis);
		return pager;
	}

	@Override
	public long countStatus(int status) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(pt.paytypeid) from t_pay p,t_paytype pt where p.paytypeid=pt.paytypeid and pt.status = "+status);
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public Pager4EasyUI<PayInfo> queryPageName(Pager4EasyUI<PayInfo> pager, String name, String value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select p.payid,e.name as en,pt.name as ptn,p.payday,p.des,p.pay,p.toname,p.tophone "
				+ "from t_emp e,t_pay p,t_paytype pt "
				+ "where p.empid=e.empid and p.paytypeid=pt.paytypeid "
				+ "and "+name+" like '%"+value+"%'");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<PayInfo> pis = new ArrayList<PayInfo>();
		for(Object[] obj : list) {
			PayInfo pi = new PayInfo();
			pi.setPayId((String) obj[0]);
			pi.setEmpName((String) obj[1]);
			pi.setPayTypeName((String) obj[2]);
			pi.setPayDay((Date) obj[3]);
			pi.setDes((String) obj[4]);
			pi.setPay((double) obj[5]);
			pi.setToName((String) obj[6]);
			pi.setToPhone((String) obj[7]);
			pis.add(pi);
		}
		pager.setRows(pis);
		return pager;
	}

	@Override
	public long countName(String name, String value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(p.empid) from t_emp e,t_pay p,t_paytype pt "
				+ "where p.empid=e.empid and p.paytypeid=pt.paytypeid "
				+ "and "+name+" like '%"+value+"%'");
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public Pager4EasyUI<PayInfo> queryPageDesc(Pager4EasyUI<PayInfo> pager, String desc) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select p.payid,e.name as en,pt.name as ptn,p.payday,p.des,p.pay,p.toname,p.tophone "
				+ "from t_emp e,t_pay p,t_paytype pt "
				+ "where p.empid=e.empid and p.paytypeid=pt.paytypeid "
				+ "order by p.pay "+desc);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<PayInfo> pis = new ArrayList<PayInfo>();
		for(Object[] obj : list) {
			PayInfo pi = new PayInfo();
			pi.setPayId((String) obj[0]);
			pi.setEmpName((String) obj[1]);
			pi.setPayTypeName((String) obj[2]);
			pi.setPayDay((Date) obj[3]);
			pi.setDes((String) obj[4]);
			pi.setPay((double) obj[5]);
			pi.setToName((String) obj[6]);
			pi.setToPhone((String) obj[7]);
			pis.add(pi);
		}
		pager.setRows(pis);
		return pager;
	}

	@Override
	public Pager4EasyUI<PayInfo> queryPageDay(Pager4EasyUI<PayInfo> pager, Serializable startDay, Serializable endDay) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select p.payid,e.name as en,pt.name as ptn,p.payday,p.des,p.pay,p.toname,p.tophone "
				+ "from t_emp e,t_pay p,t_paytype pt "
				+ "where p.empid=e.empid and p.paytypeid=pt.paytypeid "
				+ "and p.payday between '" + startDay + "' and '" + endDay + "'");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<PayInfo> pis = new ArrayList<PayInfo>();
		for(Object[] obj : list) {
			PayInfo pi = new PayInfo();
			pi.setPayId((String) obj[0]);
			pi.setEmpName((String) obj[1]);
			pi.setPayTypeName((String) obj[2]);
			pi.setPayDay((Date) obj[3]);
			pi.setDes((String) obj[4]);
			pi.setPay((double) obj[5]);
			pi.setToName((String) obj[6]);
			pi.setToPhone((String) obj[7]);
			pis.add(pi);
		}
		pager.setRows(pis);
		return pager;
	}
	

}
