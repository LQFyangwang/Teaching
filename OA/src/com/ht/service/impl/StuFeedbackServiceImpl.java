package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.StuFeedback;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.StuFeedbackDAO;
import com.ht.service.StuFeedbackService;

public class StuFeedbackServiceImpl implements StuFeedbackService {

	private StuFeedbackDAO stuFeedbackDAO;
	
	public StuFeedbackDAO getStuFeedbackDAO() {
		return stuFeedbackDAO;
	}

	public void setStuFeedbackDAO(StuFeedbackDAO stuFeedbackDAO) {
		this.stuFeedbackDAO = stuFeedbackDAO;
	}

	@Override
	public void save(StuFeedback t) {
		stuFeedbackDAO.save(t);
	}

	@Override
	public void delete(StuFeedback t) {
		stuFeedbackDAO.delete(t);
	}

	@Override
	public void update(StuFeedback t) {
		stuFeedbackDAO.update(t);
	}

	@Override
	public StuFeedback queryById(Class<?> clazz, Serializable s) {
		return stuFeedbackDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<StuFeedback> queryByPager(String beanName, Pager4EasyUI<StuFeedback> pager) {
		pager = stuFeedbackDAO.queryByPager(beanName, pager);
		pager.setTotal(stuFeedbackDAO.count(beanName));
		return pager;
	}

	@Override
	public List<StuFeedback> queryAll(String beanName) {
		return stuFeedbackDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return stuFeedbackDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		stuFeedbackDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<StuFeedback> queryByStuName(Pager4EasyUI<StuFeedback> pager, Serializable stuName,
			Serializable beanObject) {
		return stuFeedbackDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<StuFeedback> queryByDay(Pager4EasyUI<StuFeedback> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		return stuFeedbackDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<StuFeedback> queryByEmpName(Pager4EasyUI<StuFeedback> pager, Serializable empName,
			Serializable beanObject) {
		return stuFeedbackDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<StuFeedback> queryByStuId(Pager4EasyUI<StuFeedback> pager, Serializable stuId) {
		pager = stuFeedbackDAO.queryByStuId(pager, stuId);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<StuFeedback> queryByDayAndStuId(Pager4EasyUI<StuFeedback> pager, Serializable stuId,
			String startDay, String endDay) {
		pager = stuFeedbackDAO.queryByDayAndStuId(pager, stuId, startDay, endDay);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

}
