package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.info.IncomeInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.IncomeInfoDAO;
import com.ht.service.IncomeInfoService;

public class IncomeInfoServiceImpl implements IncomeInfoService {

	private IncomeInfoDAO iiDAO;
	public IncomeInfoDAO getIiDAO() {
		return iiDAO;
	}
	public void setIiDAO(IncomeInfoDAO iiDAO) {
		this.iiDAO = iiDAO;
	}

	@Override
	public void save(IncomeInfo t) {
		iiDAO.save(t);
	}

	@Override
	public void delete(IncomeInfo t) {
		iiDAO.delete(t);
	}

	@Override
	public void update(IncomeInfo t) {
		iiDAO.update(t);
	}

	@Override
	public IncomeInfo queryById(Class<?> clazz, Serializable s) {
		return iiDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<IncomeInfo> queryByPager(String beanName, Pager4EasyUI<IncomeInfo> pager) {
		return iiDAO.queryByPager(beanName, pager);
	}

	@Override
	public List<IncomeInfo> queryAll(String beanName) {
		return iiDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return iiDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		iiDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<IncomeInfo> queryIncomeInfoPage(int status, Pager4EasyUI<IncomeInfo> pager) {
		return iiDAO.queryIncomeInfoPage(status, pager);
	}

	@Override
	public long countStatus(int status) {
		return iiDAO.countStatus(status);
	}
	@Override
	public Pager4EasyUI<IncomeInfo> queryByStuName(Pager4EasyUI<IncomeInfo> pager, Serializable stuName,
			Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pager4EasyUI<IncomeInfo> queryByEmpName(Pager4EasyUI<IncomeInfo> pager, Serializable empName,
			Serializable beanObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pager4EasyUI<IncomeInfo> queryByDay(Pager4EasyUI<IncomeInfo> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pager4EasyUI<IncomeInfo> queryPageName(Pager4EasyUI<IncomeInfo> pager, String name, String value) {
		return iiDAO.queryPageName(pager, name, value);
	}
	@Override
	public long countName(String name, String value) {
		return iiDAO.countName(name, value);
	}
	@Override
	public Pager4EasyUI<IncomeInfo> queryPageDay(Pager4EasyUI<IncomeInfo> pager, Serializable startDay,
			Serializable endDay) {
		return iiDAO.queryPageDay(pager, startDay, endDay);
	}

}
