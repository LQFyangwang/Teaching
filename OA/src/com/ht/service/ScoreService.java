package com.ht.service;

import java.io.Serializable;

import com.ht.bean.Score;
import com.ht.common.bean.Pager4EasyUI;

public interface ScoreService extends BaseService<Score> {

	/**
	 * 根据班级的Id查询该班级所有学生指定课程的成绩
	 * @param pager 分页对象
	 * @param gradeId 班级的id
	 * @param courseId 课程的id
	 * @return
	 */
	public Pager4EasyUI<Score> queryPagerByGradeId(Pager4EasyUI<Score> pager, Serializable gradeId, Serializable courseId);

	/**
	 * 根据学生id分页查询该学生的成绩
	 * @param pager
	 * @param stuId
	 * @return
	 */
	public Pager4EasyUI<Score> queryByStuId(Pager4EasyUI<Score> pager, Serializable stuId);
	
	/**
	 * 根据时间段查询指定学生的成绩
	 * @param pager 分页组件
	 * @param stuId 学生id
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return
	 */
	public Pager4EasyUI<Score> queryByDayAndStuId(Pager4EasyUI<Score> pager, Serializable stuId, String startDay, String endDay);
	
}
