package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.info.PayInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.PayInfoDAO;
import com.ht.service.PayInfoService;

public class PayInfoServiceImpl implements PayInfoService {

	private PayInfoDAO piDAO;
	public PayInfoDAO getPiDAO() {
		return piDAO;
	}

	public void setPiDAO(PayInfoDAO piDAO) {
		this.piDAO = piDAO;
	}

	@Override
	public void save(PayInfo t) {
		piDAO.save(t);
	}

	@Override
	public void delete(PayInfo t) {
		piDAO.delete(t);
	}

	@Override
	public void update(PayInfo t) {
		piDAO.update(t);
	}

	@Override
	public PayInfo queryById(Class<?> clazz, Serializable s) {
		return piDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<PayInfo> queryByPager(String beanName, Pager4EasyUI<PayInfo> pager) {
		return piDAO.queryByPager(beanName, pager);
	}

	@Override
	public List<PayInfo> queryAll(String beanName) {
		return piDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return piDAO.count(beanName);
	}

	@Override
	public Pager4EasyUI<PayInfo> queryPayInfoPage(int status, Pager4EasyUI<PayInfo> pager) {
		return piDAO.queryPayInfoPage(status, pager);
	}

	@Override
	public long countStatus(int status) {
		return piDAO.countStatus(status);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		piDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<PayInfo> queryByStuName(Pager4EasyUI<PayInfo> pager, Serializable stuName,
			Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager4EasyUI<PayInfo> queryByEmpName(Pager4EasyUI<PayInfo> pager, Serializable empName,
			Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager4EasyUI<PayInfo> queryByDay(Pager4EasyUI<PayInfo> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return piDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<PayInfo> queryPageName(Pager4EasyUI<PayInfo> pager, String name, String value) {
		return piDAO.queryPageName(pager, name, value);
	}

	@Override
	public long countName(String name, String value) {
		return piDAO.countName(name, value);
	}

	@Override
	public Pager4EasyUI<PayInfo> queryPageDesc(Pager4EasyUI<PayInfo> pager, String desc) {
		return piDAO.queryPageDesc(pager, desc);
	}

	@Override
	public Pager4EasyUI<PayInfo> queryPageDay(Pager4EasyUI<PayInfo> pager, Serializable startDay, Serializable endDay) {
		return piDAO.queryPageDay(pager, startDay, endDay);
	}

}
