package com.ht.dao;

import java.io.Serializable;

import com.ht.bean.PayType;
import com.ht.bean.info.PayInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface PayInfoDAO extends BaseDAO<PayInfo> {

	/**
	 * 支出管理分页查看
	 * @param status
	 * @param pager
	 * @return
	 */
	public Pager4EasyUI<PayInfo> queryPayInfoPage(int status, Pager4EasyUI<PayInfo> pager);
	
	/**
	 * 分页计数
	 * @param beanName
	 * @param status
	 * @return
	 */
	public long countStatus(int status);
	
	public Pager4EasyUI<PayInfo> queryPageName(Pager4EasyUI<PayInfo> pager, String name, String value);
	
	public long countName(String name, String value);
	
	public Pager4EasyUI<PayInfo> queryPageDesc(Pager4EasyUI<PayInfo> pager, String desc);
	
	public Pager4EasyUI<PayInfo> queryPageDay(Pager4EasyUI<PayInfo> pager, Serializable startDay,Serializable endDay);
}
