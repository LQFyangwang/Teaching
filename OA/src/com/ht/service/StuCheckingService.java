package com.ht.service;

import java.io.Serializable;

import com.ht.bean.StuChecking;
import com.ht.common.bean.Pager4EasyUI;

public interface StuCheckingService extends BaseService<StuChecking> {

	/**
	 * 根据班级的Id查询所有学生考勤信息
	 * @param pager 分页对象
	 * @param gradeId 班级的id
	 * @return
	 */
	public Pager4EasyUI<StuChecking> queryPagerByGradeId(Pager4EasyUI<StuChecking> pager, Serializable gradeId, int month);
}
