package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Duty;
import com.ht.bean.info.TodayDutyInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.DutyDAO;
import com.ht.service.DutyService;

public class DutyServiceImpl implements DutyService {
	private DutyDAO dutyDAO;
	
	public DutyDAO getDutyDAO() {
		return dutyDAO;
	}

	public void setDutyDAO(DutyDAO dutyDAO) {
		this.dutyDAO = dutyDAO;
	}
	
	@Override
	public void save(Duty t) {
		dutyDAO.save(t);
	}

	@Override
	public void delete(Duty t) {
		dutyDAO.delete(t);
	}

	@Override
	public void update(Duty t) {
		dutyDAO.update(t);
	}

	@Override
	public Duty queryById(Class<?> clazz, Serializable s) {
		return dutyDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Duty> queryByPager(String beanName, Pager4EasyUI<Duty> pager) {
		pager = dutyDAO.queryByPager(beanName, pager);
		pager.setTotal(dutyDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Duty> queryAll(String beanName) {
		return dutyDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return dutyDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		dutyDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Duty> queryByPager2(Pager4EasyUI<Duty> pager) {
		return dutyDAO.queryByPager2(pager);
	}

	@Override
	public Pager4EasyUI<Duty> queryByStuName(Pager4EasyUI<Duty> pager, Serializable stuName, Serializable beanObject) {
		return dutyDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Duty> queryByDay(Pager4EasyUI<Duty> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return dutyDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Duty> queryByEmpName(Pager4EasyUI<Duty> pager, Serializable empName, Serializable beanObject) {
		return dutyDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public List<TodayDutyInfo> queryByToday() {
		return dutyDAO.queryByToday();
	}

}
