package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.EmpFeedBack;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.EmpFeedBackDAO;
import com.ht.service.BaseService;
import com.ht.service.EmpFeedBackService;

public class EmpFeedBackServiceImpl implements BaseService<EmpFeedBack>, EmpFeedBackService{
	
	private EmpFeedBackDAO empfeedbackDAO;

	public EmpFeedBackDAO getEmpfeedbackDAO() {
		return empfeedbackDAO;
	}

	public void setEmpfeedbackDAO(EmpFeedBackDAO empfeedbackDAO) {
		this.empfeedbackDAO = empfeedbackDAO;
	}

	@Override
	public void save(EmpFeedBack t) {
		empfeedbackDAO.save(t);
	}

	@Override
	public void delete(EmpFeedBack t) {
		empfeedbackDAO.delete(t);
	}

	@Override
	public void update(EmpFeedBack t) {
		empfeedbackDAO.update(t);
	}

	@Override
	public EmpFeedBack queryById(Class<?> clazz, Serializable s) {
		return empfeedbackDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<EmpFeedBack> queryByPager(String beanName, Pager4EasyUI<EmpFeedBack> pager) {
		pager = empfeedbackDAO.queryByPager(beanName, pager);
		pager.setTotal(empfeedbackDAO.count(beanName));
		return pager;
	}

	@Override
	public List<EmpFeedBack> queryAll(String beanName) {
		return empfeedbackDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return empfeedbackDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		empfeedbackDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<EmpFeedBack> queryByStuName(Pager4EasyUI<EmpFeedBack> pager, Serializable stuName,
			Serializable beanObject) {
		return empfeedbackDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<EmpFeedBack> queryByDay(Pager4EasyUI<EmpFeedBack> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		return empfeedbackDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<EmpFeedBack> queryByEmpName(Pager4EasyUI<EmpFeedBack> pager, Serializable empName,
			Serializable beanObject) {
		return empfeedbackDAO.queryByEmpName(pager, empName, beanObject);
	}

}
