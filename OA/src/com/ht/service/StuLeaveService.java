package com.ht.service;

import java.io.Serializable;

import com.ht.bean.StuLeave;
import com.ht.common.bean.Pager4EasyUI;

public interface StuLeaveService extends BaseService<StuLeave> {

	/**
	 * 根据学生id分页查询该学生的请假信息
	 * @param pager
	 * @param stuId
	 * @return
	 */
	public Pager4EasyUI<StuLeave> queryByStuId(Pager4EasyUI<StuLeave> pager, Serializable stuId);
	
	/**
	 * 根据时间段查询指定学生的请假信息
	 * @param pager 分页组件
	 * @param stuId 学生id
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return
	 */
	public Pager4EasyUI<StuLeave> queryByDayAndStuId(Pager4EasyUI<StuLeave> pager, Serializable stuId, String startDay, String endDay);
	
	/**
	 * 
	 * 用于班主任和任课老师通过学生的请假申请
	 * @param fieldName 字段的名称，如任课老师传递firstlevel，班主任传递secondlevel
	 * @param stuLeave 请假信息的对象
	 */
	public void updateLeaveStatus(StuLeave stuLeave, String fieldName);
	
}
