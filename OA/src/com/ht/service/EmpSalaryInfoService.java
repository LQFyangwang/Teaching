package com.ht.service;

import com.ht.bean.info.EmpSalaryInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface EmpSalaryInfoService extends BaseService<EmpSalaryInfo> {

	public Pager4EasyUI<EmpSalaryInfo> queryEmpSalaryInfoPage(int status, Pager4EasyUI<EmpSalaryInfo> pager);
	
	public long countStatus(int status);
	
	public Pager4EasyUI<EmpSalaryInfo> queryEmpSalaryInfoName(int status, Pager4EasyUI<EmpSalaryInfo> pager, String name, String value);
	
	public long countName(int status, String name, String value);
}
