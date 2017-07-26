package com.ht.service;

import java.io.Serializable;

import com.ht.bean.info.IncomeInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface IncomeInfoService extends BaseService<IncomeInfo> {

	public Pager4EasyUI<IncomeInfo> queryIncomeInfoPage(int status, Pager4EasyUI<IncomeInfo> pager);
	
	public long countStatus(int status);
	
	public Pager4EasyUI<IncomeInfo> queryPageName(Pager4EasyUI<IncomeInfo> pager, String name, String value);
	
	public long countName(String name, String value);
	
	public Pager4EasyUI<IncomeInfo> queryPageDay(Pager4EasyUI<IncomeInfo> pager, Serializable startDay, Serializable endDay);
}
