package com.ht.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.info.DeptInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.DeptInfoDAO;

public class DeptInfoDAOImpl extends AbstractBaseDAO<DeptInfo> implements DeptInfoDAO {

	@Override
	public Pager4EasyUI<DeptInfo> queryByPager(Pager4EasyUI<DeptInfo> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(
				"select d.depid, d.name as d_name, d.empid, e.name as e_name, d.des,  d.status from t_dept as d  left join t_emp as e on d.empid = e.empid");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<DeptInfo> deptInfos = new ArrayList<DeptInfo>();
		for (Object[] objs : list) {
			DeptInfo di = new DeptInfo();
			di.setDepId((String) objs[0]);
			di.setDepName((String) objs[1]);
			di.setEmpId((String) objs[2]);
			if (di.getEmpId() == null) {
				di.setEmpName("暂无负责人");
			} else {
				di.setEmpName((String) objs[3]);
			}
			di.setDes((String) objs[4]);
			di.setStatus((int) objs[5]);
			deptInfos.add(di);
		}
		pager.setRows(deptInfos);
		return pager;
	}

	@Override
	public Pager4EasyUI<DeptInfo> queryBeforeEmp(Pager4EasyUI<DeptInfo> pager, String beforeId) {
		System.out.println("进入78997," + beforeId);
		if (beforeId != null && !beforeId.equals("")) {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("select empid, name from t_emp where depId = '" + beforeId + "'");
			query.setFirstResult(pager.getBeginIndex());
			query.setMaxResults(pager.getPageSize());
			@SuppressWarnings("unchecked")
			List<Object[]> list = query.list();
			List<DeptInfo> deptInfos = new ArrayList<DeptInfo>();
			for (Object[] objs : list) {
				DeptInfo di = new DeptInfo();
				di.setEmpId2((String) objs[0]);
				di.setEmpName2((String) objs[1]);
				deptInfos.add(di);
			}
			pager.setRows(deptInfos);
		}
		return pager;
	}
}
