package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.IncomeType;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.IncomeTypeDAO;
import com.ht.service.IncomeTypeService;

public class IncomeTypeServiceImpl implements IncomeTypeService {
	
	private IncomeTypeDAO itDAO;
	public IncomeTypeDAO getItDAO() {
		return itDAO;
	}
	public void setItDAO(IncomeTypeDAO itDAO) {
		this.itDAO = itDAO;
	}

	@Override
	public void save(IncomeType t) {
		itDAO.save(t);
		
	}

	@Override
	public void delete(IncomeType t) {
		itDAO.delete(t);
	}

	@Override
	public void update(IncomeType t) {
		itDAO.update(t);
	}

	@Override
	public IncomeType queryById(Class<?> clazz, Serializable s) {
		return itDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<IncomeType> queryByPager(String beanName, Pager4EasyUI<IncomeType> pager) {
		return itDAO.queryByPager(beanName, pager);
	}

	@Override
	public List<IncomeType> queryAll(String beanName) {
		return itDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return itDAO.count(beanName);
	}
	
	@Override
	public Pager4EasyUI<IncomeType> queryFreeze(String name, int status, Pager4EasyUI<IncomeType> pager) {
		return itDAO.queryFreeze(name, status, pager);
	}
	@Override
	public long countStatus(String beanName, int status) {
		return itDAO.countStatus(beanName, status);
	}
	@Override
	public Pager4EasyUI<IncomeType> queryIncomeTypeName(Pager4EasyUI<IncomeType> pager, String name, String value) {
		return itDAO.queryIncomeTypeName(pager, name, value);
	}
	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		itDAO.updateStatus(beanName, idName, status, id);
	}
	@Override
	public List<IncomeType> queryType(int status) {
		return itDAO.queryType(status);
	}
	@Override
	public Pager4EasyUI<IncomeType> queryByStuName(Pager4EasyUI<IncomeType> pager, Serializable stuName,
			Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pager4EasyUI<IncomeType> queryByEmpName(Pager4EasyUI<IncomeType> pager, Serializable empName,
			Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pager4EasyUI<IncomeType> queryByDay(Pager4EasyUI<IncomeType> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long countName(String name, String value) {
		return itDAO.countName(name, value);
	}
	@Override
	public String queryByName(String name) {
		return itDAO.queryByName(name);
	}

}
