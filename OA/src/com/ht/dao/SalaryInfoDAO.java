package com.ht.dao;

import com.ht.bean.SalaryInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface SalaryInfoDAO extends BaseDAO<SalaryInfo> {

	public Pager4EasyUI<SalaryInfo> querySalaryInfoPage(String id, Pager4EasyUI<SalaryInfo> pager);
	
	public long countEmp(String id);
	
}
