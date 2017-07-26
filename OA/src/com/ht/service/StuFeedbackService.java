package com.ht.service;

import java.io.Serializable;

import com.ht.bean.StuFeedback;
import com.ht.common.bean.Pager4EasyUI;

public interface StuFeedbackService extends BaseService<StuFeedback> {

	/**
	 * 根据学生id分页查询该学生的反馈信息
	 * @param pager 分页组件
	 * @param stuId 学生id
	 * @return
	 */
	public Pager4EasyUI<StuFeedback> queryByStuId(Pager4EasyUI<StuFeedback> pager, Serializable stuId);
	
	/**
	 * 根据时间段查询指定学生的反馈信息
	 * @param pager 分页组件
	 * @param stuId 学生id
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return
	 */
	public Pager4EasyUI<StuFeedback> queryByDayAndStuId(Pager4EasyUI<StuFeedback> pager, Serializable stuId, String startDay, String endDay);
	
}
