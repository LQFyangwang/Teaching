package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Dept;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.DeptDAO;
import com.ht.service.DeptService;

public class DeptServiceImpl implements DeptService {

	private DeptDAO deptDAO;

	public DeptDAO getDeptDAO() {
		return deptDAO;
	}

	public void setDeptDAO(DeptDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

	@Override
	public void save(Dept t) {
		deptDAO.save(t);
	}

	@Override
	public void delete(Dept t) {
		deptDAO.delete(t);
	}

	@Override
	public void update(Dept t) {
		deptDAO.update(t);
	}

	@Override
	public Dept queryById(Class<?> clazz, Serializable s) {
		return deptDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Dept> queryByPager(String beanName, Pager4EasyUI<Dept> pager) {
		pager = deptDAO.queryByPager(beanName, pager);
		pager.setTotal(deptDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Dept> queryAll(String beanName) {
		return deptDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return deptDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		deptDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Dept> queryByStuName(Pager4EasyUI<Dept> pager, Serializable stuName, Serializable beanObject) {
		return deptDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Dept> queryByDay(Pager4EasyUI<Dept> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return deptDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Dept> queryByEmpName(Pager4EasyUI<Dept> pager, Serializable empName, Serializable beanObject) {
		return deptDAO.queryByEmpName(pager, empName, beanObject);
	}
}
