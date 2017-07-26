package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.EmpChecking;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.EmpCheckingDAO;
import com.ht.service.EmpCheckingService;

public class EmpCheckingServiceImpl implements EmpCheckingService {

	private EmpCheckingDAO empCheckingDAO;
	
	public EmpCheckingDAO getEmpCheckingDAO() {
		return empCheckingDAO;
	}

	public void setEmpCheckingDAO(EmpCheckingDAO empCheckingDAO) {
		this.empCheckingDAO = empCheckingDAO;
	}

	@Override
	public void save(EmpChecking t) {
		empCheckingDAO.save(t);
	}

	@Override
	public void delete(EmpChecking t) {
		empCheckingDAO.delete(t);
	}

	@Override
	public void update(EmpChecking t) {
		empCheckingDAO.update(t);
	}

	@Override
	public EmpChecking queryById(Class<?> clazz, Serializable s) {
		return empCheckingDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<EmpChecking> queryByPager(String beanName, Pager4EasyUI<EmpChecking> pager) {
		return empCheckingDAO.queryByPager(beanName, pager);
	}

	@Override
	public List<EmpChecking> queryAll(String beanName) {
		return empCheckingDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return empCheckingDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		empCheckingDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<EmpChecking> queryByStuName(Pager4EasyUI<EmpChecking> pager, Serializable stuName,
			Serializable beanObject) {
		return empCheckingDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<EmpChecking> queryByEmpName(Pager4EasyUI<EmpChecking> pager, Serializable empName,
			Serializable beanObject) {
		return empCheckingDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<EmpChecking> queryByDay(Pager4EasyUI<EmpChecking> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		return empCheckingDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

}
