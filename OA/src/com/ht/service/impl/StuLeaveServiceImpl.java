package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.StuLeave;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.StuLeaveDAO;
import com.ht.service.StuLeaveService;

public class StuLeaveServiceImpl implements StuLeaveService {

	private StuLeaveDAO stuLeaveDAO;
	
	public StuLeaveDAO getStuLeaveDAO() {
		return stuLeaveDAO;
	}

	public void setStuLeaveDAO(StuLeaveDAO stuLeaveDAO) {
		this.stuLeaveDAO = stuLeaveDAO;
	}

	@Override
	public void save(StuLeave t) {
		stuLeaveDAO.save(t);
	}

	@Override
	public void delete(StuLeave t) {
		stuLeaveDAO.delete(t);
	}

	@Override
	public void update(StuLeave t) {
		stuLeaveDAO.update(t);
	}

	@Override
	public StuLeave queryById(Class<?> clazz, Serializable s) {
		return stuLeaveDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<StuLeave> queryByPager(String beanName, Pager4EasyUI<StuLeave> pager) {
		pager = stuLeaveDAO.queryByPager(beanName, pager);
		pager.setTotal(stuLeaveDAO.count(beanName));
		return pager;
	}

	@Override
	public List<StuLeave> queryAll(String beanName) {
		return stuLeaveDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return stuLeaveDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		stuLeaveDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<StuLeave> queryByStuName(Pager4EasyUI<StuLeave> pager, Serializable stuName,
			Serializable beanObject) {
		return stuLeaveDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<StuLeave> queryByDay(Pager4EasyUI<StuLeave> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return stuLeaveDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<StuLeave> queryByEmpName(Pager4EasyUI<StuLeave> pager, Serializable empName,
			Serializable beanObject) {
		return stuLeaveDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<StuLeave> queryByStuId(Pager4EasyUI<StuLeave> pager, Serializable stuId) {
		pager = stuLeaveDAO.queryByStuId(pager, stuId);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<StuLeave> queryByDayAndStuId(Pager4EasyUI<StuLeave> pager, Serializable stuId, String startDay,
			String endDay) {
		pager = stuLeaveDAO.queryByDayAndStuId(pager, stuId, startDay, endDay);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public void updateLeaveStatus(StuLeave stuLeave, String fieldName) {
		stuLeaveDAO.updateLeaveStatus(stuLeave, fieldName);
	}


}
