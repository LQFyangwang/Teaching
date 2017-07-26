package com.ht.service;

import java.util.List;

import com.ht.bean.PayType;
import com.ht.common.bean.Pager4EasyUI;

public interface PayTypeService extends BaseService<PayType> {

	public Pager4EasyUI<PayType> queryFreeze(String name, int status, Pager4EasyUI<PayType> pager);

	public long countStatus(String beanName, int status);
	
	public Pager4EasyUI<PayType> queryPayTypeName(Pager4EasyUI<PayType> pager, String name, String value);
	
	public List<PayType> queryType(int status);
	
	public long countName(String name, String value);
}
