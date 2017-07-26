package com.ht.service;

import java.io.Serializable;

import com.ht.bean.info.PayInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface PayInfoService extends BaseService<PayInfo> {

	public Pager4EasyUI<PayInfo> queryPayInfoPage(int status, Pager4EasyUI<PayInfo> pager);
	
	public long countStatus(int status);
	
	public Pager4EasyUI<PayInfo> queryPageName(Pager4EasyUI<PayInfo> pager, String name, String value);

	public long countName(String name, String value);
	
	public Pager4EasyUI<PayInfo> queryPageDesc(Pager4EasyUI<PayInfo> pager, String desc);
	
	public Pager4EasyUI<PayInfo> queryPageDay(Pager4EasyUI<PayInfo> pager, Serializable startDay, Serializable endDay);
}
