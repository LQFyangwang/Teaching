package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Summary;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.SummaryDAO;
import com.ht.service.SummaryService;

public class SummaryServiceImpl implements SummaryService {

	private SummaryDAO summaryDAO;
	
	public SummaryDAO getSummaryDAO() {
		return summaryDAO;
	}

	public void setSummaryDAO(SummaryDAO summaryDAO) {
		this.summaryDAO = summaryDAO;
	}

	@Override
	public void save(Summary t) {
		summaryDAO.save(t);
	}

	@Override
	public void delete(Summary t) {
		summaryDAO.delete(t);
	}

	@Override
	public void update(Summary t) {
		summaryDAO.update(t);
	}

	@Override
	public Summary queryById(Class<?> clazz, Serializable s) {
		return summaryDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Summary> queryByPager(String beanName, Pager4EasyUI<Summary> pager) {
		pager = summaryDAO.queryByPager(beanName, pager);
		pager.setTotal(summaryDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Summary> queryAll(String beanName) {
		return summaryDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return summaryDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		summaryDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Summary> queryByStuName(Pager4EasyUI<Summary> pager, Serializable stuName,
			Serializable beanObject) {
		return summaryDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Summary> queryByEmpName(Pager4EasyUI<Summary> pager, Serializable empName,
			Serializable beanObject) {
		return summaryDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<Summary> queryByDay(Pager4EasyUI<Summary> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return summaryDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Summary> queryByStuId(Pager4EasyUI<Summary> pager, Serializable stuId) {
		pager = summaryDAO.queryByStuId(pager, stuId);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<Summary> queryByDayAndStuId(Pager4EasyUI<Summary> pager, Serializable stuId, String startDay,
			String endDay) {
		pager = summaryDAO.queryByDayAndStuId(pager, stuId, startDay, endDay);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

}
