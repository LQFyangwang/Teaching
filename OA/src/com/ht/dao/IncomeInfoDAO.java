package com.ht.dao;

import java.io.Serializable;

import com.ht.bean.info.IncomeInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface IncomeInfoDAO extends BaseDAO<IncomeInfo> {

	/**
	 * 收入管理分页查看
	 * @param status
	 * @param pager
	 * @return
	 */
	public Pager4EasyUI<IncomeInfo> queryIncomeInfoPage(int status, Pager4EasyUI<IncomeInfo> pager);
	
	/**
	 * 分页计数
	 * @param beanName
	 * @param status
	 * @return
	 */
	public long countStatus(int status);
	
	public Pager4EasyUI<IncomeInfo> queryPageName(Pager4EasyUI<IncomeInfo> pager, String name, String value);
	
	public long countName(String name, String value);
	
	public Pager4EasyUI<IncomeInfo> queryPageDay(Pager4EasyUI<IncomeInfo> pager, Serializable startDay, Serializable endDay);
}
