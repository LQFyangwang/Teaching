package com.ht.service;

import java.util.List;

import com.ht.bean.IncomeType;
import com.ht.common.bean.Pager4EasyUI;

public interface IncomeTypeService extends BaseService<IncomeType> {

	public Pager4EasyUI<IncomeType> queryFreeze(String name, int status, Pager4EasyUI<IncomeType> pager);

	public long countStatus(String beanName, int status);
	
	public Pager4EasyUI<IncomeType> queryIncomeTypeName(Pager4EasyUI<IncomeType> pager, String name, String value);
	
	public List<IncomeType> queryType(int status);
	
	public long countName(String name, String value);
	
	/**
	 * 根据名称查询收入类型id
	 * @param name
	 * @return
	 */
	public String queryByName(String name) ;
}
