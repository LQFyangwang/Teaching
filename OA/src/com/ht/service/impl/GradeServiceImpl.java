package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Grade;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.GradeDAO;
import com.ht.service.GradeService;

public class GradeServiceImpl implements GradeService {
	
	private GradeDAO gradeDAO;

	public GradeDAO getGradeDAO() {
		return gradeDAO;
	}

	public void setGradeDAO(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
	}

	@Override
	public void save(Grade t) {
		gradeDAO.save(t);
	}

	@Override
	public void delete(Grade t) {
		gradeDAO.delete(t);
	}

	@Override
	public void update(Grade t) {
		gradeDAO.update(t);
	}

	@Override
	public Grade queryById(Class<?> clazz, Serializable s) {
		return gradeDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Grade> queryByPager(String beanName, Pager4EasyUI<Grade> pager) {
		pager = gradeDAO.queryByPager(beanName, pager);
		pager.setTotal(gradeDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Grade> queryAll(String beanName) {
		return gradeDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return gradeDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		gradeDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Grade> queryByStuName(Pager4EasyUI<Grade> pager, Serializable stuName,
			Serializable beanObject) {
		return gradeDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Grade> queryByDay(Pager4EasyUI<Grade> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return gradeDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Grade> queryByEmpName(Pager4EasyUI<Grade> pager, Serializable empName,
			Serializable beanObject) {
		return gradeDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<Grade> queryByGradeName(Pager4EasyUI<Grade> pager, String gradeName) {
		return gradeDAO.queryByGradeName(pager, gradeName);
	}

}
