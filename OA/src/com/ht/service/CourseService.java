package com.ht.service;

import com.ht.bean.Course;
import com.ht.common.bean.Pager4EasyUI;

public interface CourseService extends BaseService<Course>{
	
	public Pager4EasyUI<Course> queryByCourseName(Pager4EasyUI<Course> pager, String courseName);

}
