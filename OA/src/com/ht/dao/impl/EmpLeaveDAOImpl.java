package com.ht.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.Emp;
import com.ht.bean.EmpLeave;
import com.ht.bean.Role;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.EmpLeaveDAO;

public class EmpLeaveDAOImpl extends AbstractBaseDAO<EmpLeave> implements EmpLeaveDAO {

	@Override
	public void updateSecondLevel(String beanName, String levelName, String idName, int secondLevel, String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update " + beanName + " set " + levelName + " = :secondLevel where " + idName + " = :id";
		Query query = session.createQuery(hql);
		query.setParameter("secondLevel", secondLevel);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public Pager4EasyUI<EmpLeave> byIdPager(Pager4EasyUI<EmpLeave> pager, String empId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from EmpLeave where empId='" + empId + "'");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<EmpLeave> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public Pager4EasyUI<EmpLeave> byDepIdPager(Pager4EasyUI<EmpLeave> pager, String depId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select l.*, e.name, e.roleid from t_empleave as l, t_emp as e, t_dept as d where l.empid = e.empid and e.depid = d.depid and e.depid='" + depId +"' order by l.starttime";
		Query query = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> objs = query.list();
		List<EmpLeave> leaves = new ArrayList<EmpLeave>();
		for (Object[] obj : objs) {
			EmpLeave el = new EmpLeave();
			Emp emp = new Emp();
			el.setLeaveId((String) obj[0]);
			emp.setEmpId((String) obj[1]);
			emp.setName((String) obj[10]);
			Role role = new Role();
			role.setRoleId((String) obj[11]);
			emp.setRole(role);
			el.setEmp(emp);
			el.setStartTime((Date) obj[2]);
			el.setEndTime((Date) obj[3]);
			el.setLeaveDay((Date) obj[4]);
			el.setDes((String) obj[5]);
			el.setStatus((int) obj[6]);
			el.setFirstLevel((int) obj[7]);
			el.setSecondLevel((int) obj[8]);
			el.setPass((int) obj[9]);
			leaves.add(el);
		}
		pager.setRows(leaves);
		return pager;
	}
	
}
