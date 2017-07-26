package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Report;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.ReportDAO;
import com.ht.service.ReportService;

public class ReportServiceImpl implements ReportService {

	private ReportDAO reportDAO;
	
	public ReportDAO getReportDAO() {
		return reportDAO;
	}

	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}
	
	@Override
	public void save(Report t) {
		reportDAO.save(t);
	}

	@Override
	public void delete(Report t) {
		reportDAO.delete(t);
	}

	@Override
	public void update(Report t) {
		reportDAO.update(t);
	}

	@Override
	public Report queryById(Class<?> clazz, Serializable s) {
		return reportDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Report> queryByPager(String beanName, Pager4EasyUI<Report> pager) {
		pager = reportDAO.queryByPager(beanName, pager);
		pager.setTotal(reportDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Report> queryAll(String beanName) {
		return reportDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return reportDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		reportDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Report> queryByStuName(Pager4EasyUI<Report> pager, Serializable stuName,
			Serializable beanObject) {
		return reportDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Report> queryByEmpName(Pager4EasyUI<Report> pager, Serializable empName,
			Serializable beanObject) {
		return reportDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<Report> queryByDay(Pager4EasyUI<Report> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return reportDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}
}
