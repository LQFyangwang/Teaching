package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Salary;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.SalaryDAO;
import com.ht.service.SalaryService;

public class SalaryServiceImpl implements SalaryService {

	private SalaryDAO salaryDAO;
	
	public SalaryDAO getSalaryDAO() {
		return salaryDAO;
	}

	public void setSalaryDAO(SalaryDAO salaryDAO) {
		this.salaryDAO = salaryDAO;
	}

	@Override
	public void save(Salary t) {
		salaryDAO.save(t);
	}

	@Override
	public void delete(Salary t) {
		salaryDAO.delete(t);
	}

	@Override
	public void update(Salary t) {
		salaryDAO.update(t);
	}

	@Override
	public Salary queryById(Class<?> clazz, Serializable s) {
		return salaryDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Salary> queryByPager(String beanName, Pager4EasyUI<Salary> pager) {
		pager = salaryDAO.queryByPager(beanName, pager);
		pager.setTotal(salaryDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Salary> queryAll(String beanName) {
		return salaryDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return salaryDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		salaryDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Salary> queryByStuName(Pager4EasyUI<Salary> pager, Serializable stuName,
			Serializable beanObject) {
		return salaryDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Salary> queryByEmpName(Pager4EasyUI<Salary> pager, Serializable empName,
			Serializable beanObject) {
		return salaryDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<Salary> queryByDay(Pager4EasyUI<Salary> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return salaryDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

}
