package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.EmpLeave;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.EmpLeaveDAO;
import com.ht.service.EmpLeaveService;

public class EmpLeaveServiceImpl implements EmpLeaveService {

	private EmpLeaveDAO empLeaveDAO;

	public EmpLeaveDAO getEmpLeaveDAO() {
		return empLeaveDAO;
	}

	public void setEmpLeaveDAO(EmpLeaveDAO empLeaveDAO) {
		this.empLeaveDAO = empLeaveDAO;
	}

	@Override
	public void save(EmpLeave t) {
		empLeaveDAO.save(t);
	}

	@Override
	public void delete(EmpLeave t) {
		empLeaveDAO.delete(t);
	}

	@Override
	public void update(EmpLeave t) {
		empLeaveDAO.update(t);
	}

	@Override
	public EmpLeave queryById(Class<?> clazz, Serializable s) {
		return empLeaveDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<EmpLeave> queryByPager(String beanName, Pager4EasyUI<EmpLeave> pager) {
		pager = empLeaveDAO.queryByPager(beanName, pager);
		pager.setTotal(empLeaveDAO.count(beanName));
		return pager;
	}

	@Override
	public List<EmpLeave> queryAll(String beanName) {
		return empLeaveDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return empLeaveDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		empLeaveDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<EmpLeave> queryByStuName(Pager4EasyUI<EmpLeave> pager, Serializable stuName,
			Serializable beanObject) {
		return empLeaveDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<EmpLeave> queryByDay(Pager4EasyUI<EmpLeave> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return empLeaveDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<EmpLeave> queryByEmpName(Pager4EasyUI<EmpLeave> pager, Serializable empName,
			Serializable beanObject) {
		return empLeaveDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public void updateSecondLevel(String beanName, String levelName, String idName, int secondLevel, String id) {
		empLeaveDAO.updateSecondLevel(beanName, levelName, idName, secondLevel, id);
	}

	@Override
	public Pager4EasyUI<EmpLeave> byIdPager(Pager4EasyUI<EmpLeave> pager, String empId) {
		return empLeaveDAO.byIdPager(pager, empId);
	}

	@Override
	public Pager4EasyUI<EmpLeave> byDepIdPager(Pager4EasyUI<EmpLeave> pager, String depId) {
		return empLeaveDAO.byDepIdPager(pager, depId);
	}

}
