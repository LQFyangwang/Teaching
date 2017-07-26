package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.info.EmpSalaryInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.EmpSalaryInfoDAO;
import com.ht.service.EmpSalaryInfoService;

public class EmpSalaryInfoServiceImpl implements EmpSalaryInfoService {
	
	private EmpSalaryInfoDAO esiDAO;

	public EmpSalaryInfoDAO getEsiDAO() {
		return esiDAO;
	}
	public void setEsiDAO(EmpSalaryInfoDAO esiDAO) {
		this.esiDAO = esiDAO;
	}

	@Override
	public void save(EmpSalaryInfo t) {
		esiDAO.save(t);
	}

	@Override
	public void delete(EmpSalaryInfo t) {
		esiDAO.delete(t);
	}

	@Override
	public void update(EmpSalaryInfo t) {
		esiDAO.update(t);
	}

	@Override
	public EmpSalaryInfo queryById(Class<?> clazz, Serializable s) {
		return esiDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<EmpSalaryInfo> queryByPager(String beanName, Pager4EasyUI<EmpSalaryInfo> pager) {
		return esiDAO.queryByPager(beanName, pager);
	}

	@Override
	public List<EmpSalaryInfo> queryAll(String beanName) {
		return esiDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return esiDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		esiDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<EmpSalaryInfo> queryByStuName(Pager4EasyUI<EmpSalaryInfo> pager, Serializable stuName,
			Serializable beanObject) {
		return esiDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<EmpSalaryInfo> queryByEmpName(Pager4EasyUI<EmpSalaryInfo> pager, Serializable empName,
			Serializable beanObject) {
		return esiDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<EmpSalaryInfo> queryByDay(Pager4EasyUI<EmpSalaryInfo> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		return esiDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}
	@Override
	public Pager4EasyUI<EmpSalaryInfo> queryEmpSalaryInfoPage(int status, Pager4EasyUI<EmpSalaryInfo> pager) {
		return esiDAO.queryEmpSalaryInfoPage(status, pager);
	}
	
	@Override
	public long countStatus(int status) {
		return esiDAO.countStatus(status);
	}
	@Override
	public Pager4EasyUI<EmpSalaryInfo> queryEmpSalaryInfoName(int status, Pager4EasyUI<EmpSalaryInfo> pager,
			String name, String value) {
		return esiDAO.queryEmpSalaryInfoName(status, pager, name, value);
	}
	@Override
	public long countName(int status, String name, String value) {
		return esiDAO.countName(status, name, value);
	}

}
