package com.ht.service.impl;

import java.io.Serializable;

import com.ht.bean.info.SalaryInfo1;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.SalaryInfoDAO1;
import com.ht.service.SalaryInfoService1;

public class SalaryInfoServiceImpl1 implements SalaryInfoService1 {

	private SalaryInfoDAO1 salaryInfoDAO1;
	
	public SalaryInfoDAO1 getSalaryInfoDAO1() {
		return salaryInfoDAO1;
	}

	public void setSalaryInfoDAO1(SalaryInfoDAO1 salaryInfoDAO1) {
		this.salaryInfoDAO1 = salaryInfoDAO1;
	}

	@Override
	public Pager4EasyUI<SalaryInfo1> queryByPager(Pager4EasyUI<SalaryInfo1> pager) {
		pager = salaryInfoDAO1.queryByPager(pager);
		pager.setTotal(salaryInfoDAO1.count());
		return pager;
	}

	@Override
	public long count() {
		return salaryInfoDAO1.count();
	}

	@Override
	public Pager4EasyUI<SalaryInfo1> queryByDay(Pager4EasyUI<SalaryInfo1> pager, Serializable startDay,
			Serializable endDay) {
		pager = salaryInfoDAO1.queryByDay(pager, startDay, endDay);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<SalaryInfo1> queryByName(Pager4EasyUI<SalaryInfo1> pager, Serializable empName) {
		return salaryInfoDAO1.queryByName(pager, empName);
	}

	@Override
	public Pager4EasyUI<SalaryInfo1> queryPageEmpId(Pager4EasyUI<SalaryInfo1> pager, Serializable empId) {
		pager = salaryInfoDAO1.queryPageEmpId(pager, empId);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<SalaryInfo1> queryByDayAndEmpId(Pager4EasyUI<SalaryInfo1> pager, Serializable startDay,
			Serializable endDay, Serializable empId) {
		return salaryInfoDAO1.queryByDayAndEmpId(pager, startDay, endDay, empId);
	}

}
