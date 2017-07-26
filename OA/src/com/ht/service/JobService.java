package com.ht.service;

import java.io.Serializable;

import com.ht.bean.Job;
import com.ht.common.bean.Pager4EasyUI;

public interface JobService extends BaseService<Job> {

	/**
	 * 根据学生id分页查询该学生的就业信息
	 * @param pager
	 * @param stuId
	 * @return
	 */
	public Pager4EasyUI<Job> queryByStuId(Pager4EasyUI<Job> pager, Serializable stuId);
	
	/**
	 * 根据时间段查询指定学生的就业信息
	 * @param pager 分页组件
	 * @param stuId 学生id
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return
	 */
	public Pager4EasyUI<Job> queryByDayAndStuId(Pager4EasyUI<Job> pager, Serializable stuId, String startDay, String endDay);
}
