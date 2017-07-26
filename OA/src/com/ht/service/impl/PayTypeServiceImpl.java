package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.PayType;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.PayTypeDAO;
import com.ht.service.PayTypeService;

public class PayTypeServiceImpl implements PayTypeService {
	
	private PayTypeDAO ptDAO;
	public PayTypeDAO getPtDAO() {
		return ptDAO;
	}
	public void setPtDAO(PayTypeDAO ptDAO) {
		this.ptDAO = ptDAO;
	}

	@Override
	public void save(PayType t) {
		ptDAO.save(t);
		
	}

	@Override
	public void delete(PayType t) {
		ptDAO.delete(t);
	}

	@Override
	public void update(PayType t) {
		ptDAO.update(t);
	}

	@Override
	public PayType queryById(Class<?> clazz, Serializable s) {
		return ptDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<PayType> queryByPager(String beanName, Pager4EasyUI<PayType> pager) {
		return ptDAO.queryByPager(beanName, pager);
	}

	@Override
	public List<PayType> queryAll(String beanName) {
		return ptDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return ptDAO.count(beanName);
	}
	
	@Override
	public Pager4EasyUI<PayType> queryFreeze(String name, int status, Pager4EasyUI<PayType> pager) {
		return ptDAO.queryFreeze(name, status, pager);
	}
	@Override
	public long countStatus(String beanName, int status) {
		return ptDAO.countStatus(beanName, status);
	}
	@Override
	public Pager4EasyUI<PayType> queryPayTypeName(Pager4EasyUI<PayType> pager, String name, String value) {
		return ptDAO.queryPayTypeName(pager, name, value);
	}
	
	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		ptDAO.updateStatus(beanName, idName, status, id);
	}
	@Override
	public List<PayType> queryType(int status) {
		return ptDAO.queryType(status);
	}
	@Override
	public Pager4EasyUI<PayType> queryByStuName(Pager4EasyUI<PayType> pager, Serializable stuName,
			Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pager4EasyUI<PayType> queryByEmpName(Pager4EasyUI<PayType> pager, Serializable empName,
			Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pager4EasyUI<PayType> queryByDay(Pager4EasyUI<PayType> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long countName(String name, String value) {
		return ptDAO.countName(name, value);
	}

}
