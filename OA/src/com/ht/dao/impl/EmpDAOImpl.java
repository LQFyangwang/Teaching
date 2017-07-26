package com.ht.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ht.bean.Emp;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.EmpDAO;

public class EmpDAOImpl extends AbstractBaseDAO<Emp> implements EmpDAO {

	@Override
	public Emp queryByLogin(Emp emp) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Emp e where e.email =:eemail and e.pwd =:epwd and status = 1");
		query.setParameter("eemail", emp.getEmail());
		query.setParameter("epwd", emp.getPwd());
		emp = (Emp) query.uniqueResult();
		if (emp != null) {
			return emp;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager4EasyUI<Emp> queryByPager2(Pager4EasyUI<Emp> pager, String depId) {
		System.out.println(depId);
		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("select empid, name from t_emp where depid='" + depId + "' limit " + pager.getBeginIndex() + ", " + pager.getPageSize());
		List<Object[]> objs = sqlQuery.list();
		List<Emp> emps = new ArrayList<Emp>();
		for (Object[] o : objs) {
			Emp emp = new Emp();
			emp.setEmpId((String)o[0]);
			emp.setName((String)o[1]);
			emps.add(emp);
		}
		pager.setRows(emps);
		return pager;
	}

	@Override
	public Long depIdCount(String depId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Emp.class).setProjection(Projections.count("empid"));
		criteria.add(Restrictions.eq("depid", depId));
		long count = (long) criteria.uniqueResult();
		System.out.println(count);
		return count;
	}

	@Override
	public List<Emp> queryType(int status) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select p.empid,p.name from t_emp p where p.status = "+status);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<Emp> pts = new ArrayList<Emp>();
		for(Object[] obj : list) {
			Emp pt = new Emp();
			pt.setEmpId((String) obj[0]);
			pt.setName((String) obj[1]);
			pts.add(pt);
		}
		return pts;
	}
	
	@Override
	public Pager4EasyUI<Emp> queryByEmpIdPager(Pager4EasyUI<Emp> pager, Serializable roleId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Emp where role.roleId = ?");
		query.setParameter(0, roleId);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Emp> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public long empCount(Serializable roleId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Emp where role.roleId = ?");
		query.setParameter(0, roleId);
		long count = (long) query.uniqueResult();
		return count;
	}

	@Override
	public Pager4EasyUI<Emp> queryEmpByRoleId(Pager4EasyUI<Emp> pager, String roleId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Emp where roleId = ?");
		query.setParameter(0, roleId);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Emp> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public Pager4EasyUI<Emp> queryByName(Pager4EasyUI<Emp> pager, Serializable empName,Serializable beanObject) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + beanObject + " as temp where temp.name like :name");
		query.setParameter("name", "%" + empName + "%");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Emp> t = query.list();
		if (t.size() > 0) {
			pager.setRows(t);
		}
		return pager;
	}

	@Override
	public boolean queryByEmail(Emp emp) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Emp where email = ?");
		query.setParameter(0, emp.getEmail());
		Emp e = (Emp) query.uniqueResult();
		if (e != null) {
			return true;
		}
		return false;
	}

	@Override
	public long empRoleIdCount(String roleId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Emp where roleId = ?");
		query.setParameter(0, roleId);
		long count = (long) query.uniqueResult();
		return count;
	}

	@Override
	public Pager4EasyUI<Emp> queryByPagerEmpId(Pager4EasyUI<Emp> pager, String empId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Emp where empid !=?");
		query.setParameter(0, empId);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Emp> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public Emp QueryEmp(String empId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Emp where empid =?");
		query.setParameter(0, empId);
		Emp emp = (Emp)query.list();
		return emp;
	}

	@Override
	public void updateSalaryInfo(Emp emp) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("update t_emp set accountno = ?,bankname = ? where empid = ?");
		query.setParameter(0, emp.getAccountNo());
		query.setParameter(1, emp.getBankName());
		query.setParameter(2, emp.getEmpId());
		query.executeUpdate();
	}
}
