package com.ht.service;

import com.ht.bean.SalaryInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface SalaryInfoService extends BaseService<SalaryInfo> {

	public Pager4EasyUI<SalaryInfo> querySalaryInfoPage(String id, Pager4EasyUI<SalaryInfo> pager);
	
	public long countEmp(String id);
}
