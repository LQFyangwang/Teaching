package com.ht.service;

import java.util.Date;

import com.ht.bean.GoodsUse;
import com.ht.bean.info.GoodsUseInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface GoodsUseService extends BaseService<GoodsUse> {
	public Pager4EasyUI<GoodsUseInfo> queryPayInfoPage(Pager4EasyUI<GoodsUseInfo> pager);
	public void updateReturn(int returnStatus, String id);
	public void updateReturnDay(Date ReturnDay, String id);
	public void updateCheck(int checkStatus, String id);
}
