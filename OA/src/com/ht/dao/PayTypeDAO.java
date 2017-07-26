package com.ht.dao;

import java.util.List;

import com.ht.bean.PayType;
import com.ht.common.bean.Pager4EasyUI;

public interface PayTypeDAO extends BaseDAO<PayType> {
	
	/**
	 * 查看是否被冻结
	 * @param name
	 * @param status
	 * @param pager
	 * @return
	 */
	public Pager4EasyUI<PayType> queryFreeze(String name, int status, Pager4EasyUI<PayType> pager);
	

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
	public Pager4EasyUI<PayType> queryPayTypeName(Pager4EasyUI<PayType> pager, String name, String value);


	public List<PayType> queryType(int status);
	
	public long countName(String name, String value);
}
