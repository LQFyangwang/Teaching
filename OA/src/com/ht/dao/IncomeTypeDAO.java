package com.ht.dao;

import java.util.List;

import com.ht.bean.IncomeType;
import com.ht.common.bean.Pager4EasyUI;

public interface IncomeTypeDAO extends BaseDAO<IncomeType> {
	
	/**
	 * 查看是否被冻结
	 * @param name
	 * @param status
	 * @param pager
	 * @return
	 */
	public Pager4EasyUI<IncomeType> queryFreeze(String name, int status, Pager4EasyUI<IncomeType> pager);
	

	/**
	 * 分页计数
	 * @param beanName
	 * @return
	 */
	public long countStatus(String beanName, int status);
	
	/**
	 * 按条件查询
	 * @param pager
	 * @param name
	 * @param value
	 * @param status
	 * @return
	 */
	public Pager4EasyUI<IncomeType> queryIncomeTypeName(Pager4EasyUI<IncomeType> pager, String name, String value);
	
	public List<IncomeType> queryType(int status);
	
	public long countName(String name, String value);
	
	/**
	 * 根据名称查询收入类型id
	 * @param name
	 * @return
	 */
	public String queryByName(String name);
	
}
