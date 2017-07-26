package com.ht.dao;

import com.ht.bean.Course;
import com.ht.common.bean.Pager4EasyUI;

public interface CourseDAO extends BaseDAO<Course>{
	
	public Pager4EasyUI<Course> queryByCourseName(Pager4EasyUI<Course> pager, String courseName);

}
