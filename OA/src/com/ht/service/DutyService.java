 package com.ht.service;

import java.util.List;

import com.ht.bean.Duty;
import com.ht.bean.info.TodayDutyInfo;
import com.ht.common.bean.Pager4EasyUI;

/**
 * 值班管理Service
 * @author xiaoqiang
 *
 */
public interface DutyService extends BaseService<Duty> {

	public Pager4EasyUI<Duty> queryByPager2(Pager4EasyUI<Duty> pager);
	
	/**
	 * 查询今天的值班信息
	 * @return
	 */
	public List<TodayDutyInfo> queryByToday();
}
