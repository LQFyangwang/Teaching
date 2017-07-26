package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Course;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.CourseDAO;
import com.ht.dao.impl.CourseDAOImpl;
import com.ht.service.BaseService;
import com.ht.service.CourseService;

public class CourseServiceImpl implements BaseService<Course>, CourseService{
	
	private CourseDAO courseDAO;
	
	public CourseServiceImpl() {
		courseDAO = new CourseDAOImpl();
	}
	
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Override
	public void save(Course t) {
		courseDAO.save(t);
	}

	@Override
	public void delete(Course t) {
		courseDAO.delete(t);
	}

	@Override
	public void update(Course t) {
		courseDAO.update(t);
	}

	@Override
	public Course queryById(Class<?> clazz, Serializable s) {
		return courseDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Course> queryByPager(String beanName, Pager4EasyUI<Course> pager) {
		pager = courseDAO.queryByPager(beanName, pager);
		pager.setTotal(courseDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Course> queryAll(String beanName) {
		return courseDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return courseDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		courseDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Course> queryByStuName(Pager4EasyUI<Course> pager, Serializable stuName,
			Serializable beanObject) {
		return courseDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Course> queryByDay(Pager4EasyUI<Course> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return courseDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Course> queryByEmpName(Pager4EasyUI<Course> pager, Serializable empName,
			Serializable beanObject) {
		return courseDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<Course> queryByCourseName(Pager4EasyUI<Course> pager, String courseName) {
		return courseDAO.queryByCourseName(pager, courseName);
	}

}
