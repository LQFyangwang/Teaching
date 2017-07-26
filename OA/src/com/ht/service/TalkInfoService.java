package com.ht.service;

import java.io.Serializable;

import com.ht.bean.info.TalkInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface TalkInfoService {

	/**
	 * 分页查询指定的字段
	 * @param pager
	 * @return
	 */
	public Pager4EasyUI<TalkInfo> queryByPager(Pager4EasyUI<TalkInfo> pager);
	
	/**
	 * 计算个数
	 * @return
	 */
	public long count();
	
	/**
	 * 根据学生id查询该学生的谈心记录 
	 * @param pager 分页组件
	 * @param stuId 学生id
	 * @return
	 */
	public Pager4EasyUI<TalkInfo> queryByStuId(Pager4EasyUI<TalkInfo> pager, Serializable stuId);
	
	/**
	 * 根据学生id和指定时间段查询该学生的谈心记录
	 * @param pager
	 * @param stuId
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	public Pager4EasyUI<TalkInfo> queryByDayAndStuId(Pager4EasyUI<TalkInfo> pager, Serializable stuId, String startDay, String endDay);

}
