package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.StuChecking;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.StuCheckingDAO;
import com.ht.service.StuCheckingService;

public class StuCheckingServiceImpl implements StuCheckingService {

	private StuCheckingDAO stuCheckingDAO;
	
	public StuCheckingDAO getStuCheckingDAO() {
		return stuCheckingDAO;
	}

	public void setStuCheckingDAO(StuCheckingDAO stuCheckingDAO) {
		this.stuCheckingDAO = stuCheckingDAO;
	}

	@Override
	public void save(StuChecking t) {
		stuCheckingDAO.save(t);
	}

	@Override
	public void delete(StuChecking t) {
		stuCheckingDAO.delete(t);
	}

	@Override
	public void update(StuChecking t) {
		stuCheckingDAO.update(t);
	}

	@Override
	public StuChecking queryById(Class<?> clazz, Serializable s) {
		return stuCheckingDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<StuChecking> queryByPager(String beanName, Pager4EasyUI<StuChecking> pager) {
		pager = stuCheckingDAO.queryByPager(beanName, pager);
		pager.setTotal(stuCheckingDAO.count(beanName));
		return pager;
	}

	@Override
	public List<StuChecking> queryAll(String beanName) {
		return stuCheckingDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return stuCheckingDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		stuCheckingDAO.updateStatus(beanName, idName, status, id);;
	}

	@Override
	public Pager4EasyUI<StuChecking> queryPagerByGradeId(Pager4EasyUI<StuChecking> pager, Serializable gradeId, int month) {
		pager = stuCheckingDAO.queryPagerByGradeId(pager, gradeId, month);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<StuChecking> queryByStuName(Pager4EasyUI<StuChecking> pager, Serializable stuName,
			Serializable beanObject) {
		return stuCheckingDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<StuChecking> queryByDay(Pager4EasyUI<StuChecking> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		return stuCheckingDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<StuChecking> queryByEmpName(Pager4EasyUI<StuChecking> pager, Serializable empName,
			Serializable beanObject) {
		return stuCheckingDAO.queryByEmpName(pager, empName, beanObject);
	}

}
