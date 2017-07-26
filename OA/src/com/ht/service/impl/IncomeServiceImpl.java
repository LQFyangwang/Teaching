package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Income;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.IncomeDAO;
import com.ht.service.IncomeService;

public class IncomeServiceImpl implements IncomeService {
	
	private IncomeDAO incomeDAO;

	public IncomeDAO getIncomeDAO() {
		return incomeDAO;
	}
	public void setIncomeDAO(IncomeDAO incomeDAO) {
		this.incomeDAO = incomeDAO;
	}

	@Override
	public void save(Income t) {
		incomeDAO.save(t);
	}

	@Override
	public void delete(Income t) {
		incomeDAO.delete(t);
	}

	@Override
	public void update(Income t) {
		incomeDAO.update(t);
	}

	@Override
	public Income queryById(Class<?> clazz, Serializable s) {
		return incomeDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Income> queryByPager(String beanName, Pager4EasyUI<Income> pager) {
		return incomeDAO.queryByPager(beanName, pager);
	}

	@Override
	public List<Income> queryAll(String beanName) {
		return incomeDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return incomeDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		incomeDAO.updateStatus(beanName, idName, status, id);
	}
	@Override
	public Pager4EasyUI<Income> queryByStuName(Pager4EasyUI<Income> pager, Serializable stuName,
			Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pager4EasyUI<Income> queryByEmpName(Pager4EasyUI<Income> pager, Serializable empName,
			Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pager4EasyUI<Income> queryByDay(Pager4EasyUI<Income> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		// TODO Auto-generated method stub
		return null;
	}

}
