package com.ht.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.info.EmpSalaryInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.EmpSalaryInfoDAO;

public class EmpSalaryInfoDAOImpl extends AbstractBaseDAO<EmpSalaryInfo> implements EmpSalaryInfoDAO {

	@Override
	public Pager4EasyUI<EmpSalaryInfo> queryEmpSalaryInfoPage(int status, Pager4EasyUI<EmpSalaryInfo> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select e.empid,e.name as en,e.gender,r.name as rn,d.name as dn,e.idcard,"
				+ "e.bankname,e.accountname,e.accountno,si.basicsalary,si.jobsalary,si.salaryinfoid "
				+ "from t_emp e,t_dept d,t_role r,t_salaryinfo si "
				+ "where e.depid=d.depid and e.roleid=r.roleid and e.empid=si.empid and e.status="+status);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<EmpSalaryInfo> esi = new ArrayList<EmpSalaryInfo>();
		for(Object[] obj : list) {
			EmpSalaryInfo e = new EmpSalaryInfo();
			e.setEmpId((String) obj[0]);
			e.setName((String) obj[1]);
			e.setGender((String) obj[2]);
			e.setRoleName((String) obj[3]);
			e.setDeptName((String) obj[4]);
			e.setIdCard((String) obj[5]);
			e.setBankName((String) obj[6]);
			e.setAccountName((String) obj[7]);
			e.setAccountNo((String) obj[8]);
			e.setBasicSalary((double) obj[9]);
			e.setJobSalary((double) obj[10]);
			e.setSalaryinfoId((String) obj[11]);
			esi.add(e);
		}
		pager.setRows(esi);
		return pager;
	}

	@Override
	public long countStatus(int status) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(e.empid) "
				+ "from t_emp e,t_dept d,t_role r,t_salaryinfo si "
				+ "where e.depid=d.depid and e.roleid=r.roleid and e.empid=si.empid "
				+ "and e.status = "+status);
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

	@Override
	public Pager4EasyUI<EmpSalaryInfo> queryEmpSalaryInfoName(int status, Pager4EasyUI<EmpSalaryInfo> pager,
			String name, String value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select e.empid,e.name as en,e.gender,r.name as rn,d.name as dn,e.idcard,"
				+ "e.bankname,e.accountname,e.accountno,si.basicsalary,si.jobsalary,si.salaryinfoid "
				+ "from t_emp e,t_dept d,t_role r,t_salaryinfo si "
				+ "where e.depid=d.depid and e.roleid=r.roleid and e.empid=si.empid "
				+ "and "+name+" like '%"+value+"%' and e.status="+status);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<EmpSalaryInfo> esi = new ArrayList<EmpSalaryInfo>();
		for(Object[] obj : list) {
			EmpSalaryInfo e = new EmpSalaryInfo();
			e.setEmpId((String) obj[0]);
			e.setName((String) obj[1]);
			e.setGender((String) obj[2]);
			e.setRoleName((String) obj[3]);
			e.setDeptName((String) obj[4]);
			e.setIdCard((String) obj[5]);
			e.setBankName((String) obj[6]);
			e.setAccountName((String) obj[7]);
			e.setAccountNo((String) obj[8]);
			e.setBasicSalary((double) obj[9]);
			e.setJobSalary((double) obj[10]);
			e.setSalaryinfoId((String) obj[11]);
			esi.add(e);
		}
		pager.setRows(esi);
		return pager;
	}

	@Override
	public long countName(int status, String name, String value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(e.empid) "
				+ "from t_emp e,t_dept d,t_role r,t_salaryinfo si "
				+ "where e.depid=d.depid and e.roleid=r.roleid and e.empid=si.empid "
				+ "and "+name+" like '%"+value+"%' and e.status = "+status);
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.longValue();
	}

}
