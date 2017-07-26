package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Dept;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.DeptTypeDAO;
import com.ht.service.DeptTypeService;

public class DeptTypeServiceImpl implements DeptTypeService {
	private DeptTypeDAO deptTypeDao;
	
	public DeptTypeDAO getDeptTypeDao() {
		return deptTypeDao;
	}

	public void setDeptTypeDao(DeptTypeDAO deptTypeDao) {
		this.deptTypeDao = deptTypeDao;
	}

	@Override
	public void save(Dept t) {
		
	}

	@Override
	public void delete(Dept t) {
		
	}

	@Override
	public void update(Dept t) {
		
	}

	@Override
	public Dept queryById(Class<?> clazz, Serializable s) {
		return null;
	}

	@Override
	public Pager4EasyUI<Dept> queryByPager(String beanName, Pager4EasyUI<Dept> pager) {
		pager = deptTypeDao.queryByPager(beanName, pager);
		pager.setTotal(deptTypeDao.count(beanName));
		return pager;
	}

	@Override
	public List<Dept> queryAll(String beanName) {
		return null;
	}

	@Override
	public long count(String beanName) {
		return 0;
	}

	@Override
	public List<Dept> queryNameAll() {
		return deptTypeDao.queryNameAll();
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		deptTypeDao.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Dept> queryByStuName(Pager4EasyUI<Dept> pager, Serializable stuName, Serializable beanObject) {
		
		return deptTypeDao.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Dept> queryByDay(Pager4EasyUI<Dept> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return deptTypeDao.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Dept> queryByEmpName(Pager4EasyUI<Dept> pager, Serializable empName, Serializable beanObject) {
		return deptTypeDao.queryByEmpName(pager, empName, beanObject);
	}

}
