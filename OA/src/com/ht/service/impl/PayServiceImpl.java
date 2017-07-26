package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Pay;
import com.ht.bean.PayType;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.PayDAO;
import com.ht.service.PayService;

public class PayServiceImpl implements PayService {

	private PayDAO payDAO;
	
	public PayDAO getPayDAO() {
		return payDAO;
	}

	public void setPayDAO(PayDAO payDAO) {
		this.payDAO = payDAO;
	}

	@Override
	public void save(Pay t) {
		payDAO.save(t);
	}

	@Override
	public void delete(Pay t) {
		payDAO.delete(t);
	}

	@Override
	public void update(Pay t) {
		payDAO.update(t);
	}

	@Override
	public Pay queryById(Class<?> clazz, Serializable s) {
		return payDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Pay> queryByPager(String beanName, Pager4EasyUI<Pay> pager) {
		return payDAO.queryByPager(beanName, pager);
	}

	@Override
	public List<Pay> queryAll(String beanName) {
		return payDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return payDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		payDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Pay> queryByStuName(Pager4EasyUI<Pay> pager, Serializable stuName, Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager4EasyUI<Pay> queryByEmpName(Pager4EasyUI<Pay> pager, Serializable empName, Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager4EasyUI<Pay> queryByDay(Pager4EasyUI<Pay> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayType queryByType(String typeName) {
		return payDAO.queryByType(typeName);
	}

}
