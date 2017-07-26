package com.ht.service;

import java.io.Serializable;

import com.ht.bean.info.SalaryInfo1;
import com.ht.common.bean.Pager4EasyUI;

public interface SalaryInfoService1 {

	/**
	 * 分页查询工资的发放情况
	 * @param pager
	 * @return
	 */
	public Pager4EasyUI<SalaryInfo1> queryByPager(Pager4EasyUI<SalaryInfo1> pager);
	
	/**
	 * 计算个数
	 * @return
	 */
	public long count();
	
	/**
	 * 查询指定时间段的数据
	 * @param pager 分页
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return
	 */
	public Pager4EasyUI<SalaryInfo1> queryByDay(Pager4EasyUI<SalaryInfo1> pager, Serializable startDay, Serializable endDay);
	
	/**
	 * 根据员工姓名模糊查询
	 * @param pager 分页
	 * @param empName 员工姓名
	 * @return
	 */
	public Pager4EasyUI<SalaryInfo1> queryByName(Pager4EasyUI<SalaryInfo1> pager, Serializable empName);
	
	/**
	 * 查询指定时间段的数据，查询指定员工的信息
	 * @param pager 分页
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * * @param empId 员工id
	 * @return
	 */
	public Pager4EasyUI<SalaryInfo1> queryByDayAndEmpId(Pager4EasyUI<SalaryInfo1> pager, Serializable startDay, Serializable endDay, Serializable empId);
	
	public Pager4EasyUI<SalaryInfo1> queryPageEmpId(Pager4EasyUI<SalaryInfo1> pager, Serializable empId);
}
