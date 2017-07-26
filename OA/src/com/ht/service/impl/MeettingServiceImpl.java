package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Meetting;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.MeettingDAO;
import com.ht.service.BaseService;
import com.ht.service.MeettingService;

public class MeettingServiceImpl implements BaseService<Meetting>, MeettingService{
	
	private MeettingDAO meettingDAO;
	
	public MeettingDAO getMeettingDAO() {
		return meettingDAO;
	}

	public void setMeettingDao(MeettingDAO meettingDAO) {
		this.meettingDAO = meettingDAO;
	}

	@Override
	public void save(Meetting t) {
		meettingDAO.save(t);
		
	}

	@Override
	public void delete(Meetting t) {
		meettingDAO.delete(t);
	}

	@Override
	public void update(Meetting t) {
		meettingDAO.update(t);
	}

	@Override
	public Meetting queryById(Class<?> clazz, Serializable s) {
		return meettingDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Meetting> queryByPager(String beanName, Pager4EasyUI<Meetting> pager) {
		pager = meettingDAO.queryByPager(beanName, pager);
		pager.setTotal(meettingDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Meetting> queryAll(String beanName) {
		return meettingDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return meettingDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		meettingDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Meetting> queryByStuName(Pager4EasyUI<Meetting> pager, Serializable stuName,
			Serializable beanObject) {
		return meettingDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Meetting> queryByDay(Pager4EasyUI<Meetting> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return meettingDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Meetting> queryByEmpName(Pager4EasyUI<Meetting> pager, Serializable empName,
			Serializable beanObject) {
		return meettingDAO.queryByEmpName(pager, empName, beanObject);
	}

}
