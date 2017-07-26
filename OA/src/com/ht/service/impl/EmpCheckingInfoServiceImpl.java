package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.EmpCheckingInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.EmpCheckingInfoDAO;
import com.ht.service.EmpCheckingInfoService;

public class EmpCheckingInfoServiceImpl implements EmpCheckingInfoService{

	private EmpCheckingInfoDAO empCheckingInfoDAO;
	
	public EmpCheckingInfoDAO getEmpCheckingInfoDAO() {
		return empCheckingInfoDAO;
	}

	public void setEmpCheckingInfoDAO(EmpCheckingInfoDAO empCheckingInfoDAO) {
		this.empCheckingInfoDAO = empCheckingInfoDAO;
	}

	@Override
	public void save(EmpCheckingInfo t) {
		empCheckingInfoDAO.save(t);
	}

	@Override
	public void delete(EmpCheckingInfo t) {
		empCheckingInfoDAO.delete(t);
	}

	@Override
	public void update(EmpCheckingInfo t) {
		empCheckingInfoDAO.update(t);
	}

	@Override
	public EmpCheckingInfo queryById(Class<?> clazz, Serializable s) {
		return empCheckingInfoDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<EmpCheckingInfo> queryByPager(String beanName, Pager4EasyUI<EmpCheckingInfo> pager) {
		pager = empCheckingInfoDAO.queryByPager(beanName, pager);
		pager.setTotal(empCheckingInfoDAO.count(beanName));
		return pager;
	}

	@Override
	public List<EmpCheckingInfo> queryAll(String beanName) {
		return empCheckingInfoDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return empCheckingInfoDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		empCheckingInfoDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<EmpCheckingInfo> queryByStuName(Pager4EasyUI<EmpCheckingInfo> pager, Serializable stuName,
			Serializable beanObject) {
		return empCheckingInfoDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<EmpCheckingInfo> queryByDay(Pager4EasyUI<EmpCheckingInfo> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		return empCheckingInfoDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<EmpCheckingInfo> queryByEmpName(Pager4EasyUI<EmpCheckingInfo> pager, Serializable empName,
			Serializable beanObject) {
		return empCheckingInfoDAO.queryByEmpName(pager, empName, beanObject);
	}

}
