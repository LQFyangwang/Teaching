package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Check;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.CheckDAO;
import com.ht.service.CheckService;

public class CheckServiceImpl implements CheckService {

	private CheckDAO checkDAO;
	
	public CheckDAO getCheckDAO() {
		return checkDAO;
	}

	public void setCheckDAO(CheckDAO checkDAO) {
		this.checkDAO = checkDAO;
	}

	@Override
	public void save(Check t) {
		checkDAO.save(t);
	}

	@Override
	public void delete(Check t) {
		checkDAO.delete(t);
	}

	@Override
	public void update(Check t) {
		checkDAO.update(t);
	}

	@Override
	public Check queryById(Class<?> clazz, Serializable s) {
		return checkDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Check> queryByPager(String beanName, Pager4EasyUI<Check> pager) {
		pager = checkDAO.queryByPager(beanName, pager);
		pager.setTotal(checkDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Check> queryAll(String beanName) {
		return checkDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return checkDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		checkDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Check> queryByStuName(Pager4EasyUI<Check> pager, Serializable stuName,
			Serializable beanObject) {
		return checkDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Check> queryByDay(Pager4EasyUI<Check> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return checkDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Check> queryByEmpName(Pager4EasyUI<Check> pager, Serializable empName,
			Serializable beanObject) {
		return checkDAO.queryByEmpName(pager, empName, beanObject);
	}

}
