package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.SalaryInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.SalaryInfoDAO;
import com.ht.service.SalaryInfoService;

public class SalaryInfoServiceImpl implements SalaryInfoService {
	
	private SalaryInfoDAO siDAO;
	public SalaryInfoDAO getSiDAO() {
		return siDAO;
	}
	public void setSiDAO(SalaryInfoDAO siDAO) {
		this.siDAO = siDAO;
	}

	@Override
	public void save(SalaryInfo t) {
		siDAO.save(t);
	}

	@Override
	public void delete(SalaryInfo t) {
		siDAO.delete(t);
	}

	@Override
	public void update(SalaryInfo t) {
		siDAO.update(t);
	}

	@Override
	public SalaryInfo queryById(Class<?> clazz, Serializable s) {
		return siDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<SalaryInfo> queryByPager(String beanName, Pager4EasyUI<SalaryInfo> pager) {
		return siDAO.queryByPager(beanName, pager);
	}

	@Override
	public List<SalaryInfo> queryAll(String beanName) {
		return siDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return siDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		siDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<SalaryInfo> queryByStuName(Pager4EasyUI<SalaryInfo> pager, Serializable stuName,
			Serializable beanObject) {
		return siDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<SalaryInfo> queryByEmpName(Pager4EasyUI<SalaryInfo> pager, Serializable empName,
			Serializable beanObject) {
		return siDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<SalaryInfo> queryByDay(Pager4EasyUI<SalaryInfo> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		return siDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}
	@Override
	public Pager4EasyUI<SalaryInfo> querySalaryInfoPage(String id, Pager4EasyUI<SalaryInfo> pager) {
		return siDAO.querySalaryInfoPage(id, pager);
	}
	@Override
	public long countEmp(String id) {
		return siDAO.countEmp(id);
	}

}
