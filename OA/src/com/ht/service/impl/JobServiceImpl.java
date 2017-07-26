package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Job;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.JobDAO;
import com.ht.service.BaseService;
import com.ht.service.JobService;

public class JobServiceImpl implements BaseService<Job>, JobService {

	private JobDAO jobDAO;
	
	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	@Override
	public void save(Job t) {
		jobDAO.save(t);
	}

	@Override
	public void delete(Job t) {
		jobDAO.delete(t);
	}

	@Override
	public void update(Job t) {
		jobDAO.update(t);
	}

	@Override
	public Job queryById(Class<?> clazz, Serializable s) {
		return jobDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Job> queryByPager(String beanName, Pager4EasyUI<Job> pager) {
		pager = jobDAO.queryByPager(beanName, pager);
		pager.setTotal(jobDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Job> queryAll(String beanName) {
		return jobDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return jobDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		jobDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Job> queryByStuName(Pager4EasyUI<Job> pager, Serializable stuName, Serializable beanObject) {
		return jobDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Job> queryByDay(Pager4EasyUI<Job> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return jobDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Job> queryByEmpName(Pager4EasyUI<Job> pager, Serializable empName, Serializable beanObject) {
		return jobDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<Job> queryByStuId(Pager4EasyUI<Job> pager, Serializable stuId) {
		pager = jobDAO.queryByStuId(pager, stuId);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<Job> queryByDayAndStuId(Pager4EasyUI<Job> pager, Serializable stuId, String startDay,
			String endDay) {
		pager = jobDAO.queryByDayAndStuId(pager, stuId, startDay, endDay);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

}
