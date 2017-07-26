package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Progress;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.ProgressDAO;
import com.ht.service.BaseService;
import com.ht.service.ProgressService;

public class ProgressServiceImpl implements  BaseService<Progress>, ProgressService{
	
	private ProgressDAO progressDAO;

	public ProgressDAO getProgressDAO() {
		return progressDAO;
	}

	public void setProgressDAO(ProgressDAO progressDAO) {
		this.progressDAO = progressDAO;
	}

	@Override
	public void save(Progress t) {
			progressDAO.save(t);
	}

	@Override
	public void delete(Progress t) {
			progressDAO.delete(t);
	}

	@Override
	public void update(Progress t) {
			progressDAO.update(t);
	}

	@Override
	public Progress queryById(Class<?> clazz, Serializable s) {
		return progressDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Progress> queryByPager(String beanName, Pager4EasyUI<Progress> pager) {
		pager = progressDAO.queryByPager(beanName, pager);
		pager.setTotal(progressDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Progress> queryAll(String beanName) {
		return progressDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return progressDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
			progressDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Progress> queryByStuName(Pager4EasyUI<Progress> pager, Serializable stuName,
			Serializable beanObject) {
		return progressDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Progress> queryByEmpName(Pager4EasyUI<Progress> pager, Serializable empName,
			Serializable beanObject) {
		return progressDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<Progress> queryByDay(Pager4EasyUI<Progress> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return progressDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

}
