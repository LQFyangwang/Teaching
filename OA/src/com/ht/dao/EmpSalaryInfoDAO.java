package com.ht.dao;

import com.ht.bean.info.EmpSalaryInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface EmpSalaryInfoDAO extends BaseDAO<EmpSalaryInfo> {
	
	/**
	 * 工资基本信息进行分布查看
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager4EasyUI<EmpSalaryInfo> queryEmpSalaryInfoPage(int status, Pager4EasyUI<EmpSalaryInfo> pager);
	
	/**
	 * 分页计数
	 * @param beanName
	 * @param status
	 * @return
	 */
	public long countStatus(int status);
	
	public Pager4EasyUI<EmpSalaryInfo> queryEmpSalaryInfoName(int status, Pager4EasyUI<EmpSalaryInfo> pager, String name, String value);
	
	public long countName(int status, String name, String value);
}
