package com.ht.service.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import com.ht.bean.Stu;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.StuDAO;
import com.ht.service.StuService;

public class StuServiceImpl implements StuService {

	private StuDAO stuDAO;
	
	public StuDAO getStuDAO() {
		return stuDAO;
	}

	public void setStuDAO(StuDAO stuDAO) {
		this.stuDAO = stuDAO;
	}

	@Override
	public void save(Stu t) {
		stuDAO.save(t);
	}

	@Override
	public void delete(Stu t) {
		stuDAO.delete(t);
	}

	@Override
	public void update(Stu t) {
		stuDAO.update(t);
	}

	@Override
	public Stu queryById(Class<?> clazz, Serializable s) {
		return stuDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Stu> queryByPager(String beanName, Pager4EasyUI<Stu> pager) {
		pager = stuDAO.queryByPager(beanName, pager);
		pager.setTotal(stuDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Stu> queryAll(String beanName) {
		return stuDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return stuDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		stuDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Stu> queryByGradeIdPager(Pager4EasyUI<Stu> pager, Serializable gradeId) {
		pager = stuDAO.queryByGradeIdPager(pager, gradeId);
		pager.setTotal(stuDAO.gradeCount(gradeId));
		return pager;
	}

	@Override
	public Pager4EasyUI<Stu> queryByGradePager(Pager4EasyUI<Stu> pager, Serializable gradeId) {
		pager = stuDAO.queryByGradePager(pager, gradeId);
		pager.setTotal(stuDAO.gradeCount1(gradeId));
		return pager;
	}
	
	@Override
	public Pager4EasyUI<Stu> stuStatusByPager(Pager4EasyUI<Stu> pager, String stuStatus) {
		return stuDAO.stuStatusByPager(pager, stuStatus);
	}
	
	public Pager4EasyUI<Stu> stuStatusByPager1(Pager4EasyUI<Stu> pager, String stuStatus) {
		return stuDAO.stuStatusByPager1(pager, stuStatus);
	}

	@Override
	public long gradeCount(String gradeId) {
		return stuDAO.gradeCount(gradeId);
	}
	
	@Override
	public long gradeCount1(String gradeId) {
		return stuDAO.gradeCount1(gradeId);
	}

	@Override
	public Pager4EasyUI<Stu> queryByRoomIdPager(Pager4EasyUI<Stu> pager, Serializable roomId) {
		return stuDAO.queryByRoomIdPager(pager, roomId);
	}
	
	@Override
	public void addStusToGrade(String gradeId, String[] stuIds) {
		stuDAO.addStusToGrade(gradeId, stuIds);
	}

	@Override
	public void addStusToRoom(String roomId, String[] stuIds) {
		stuDAO.addStusToRoom(roomId, stuIds);
	}

	@Override
	public Pager4EasyUI<Stu> queryByStuName(Pager4EasyUI<Stu> pager, Serializable stuName, Serializable beanObject) {
		return stuDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Stu> queryByDay(Pager4EasyUI<Stu> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return stuDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public long stuStatusCount(String stuStatus) {
		return stuDAO.stuStatusCount(stuStatus);
	}
	
	@Override
	public long stuStatusCount1(String stuStatus) {
		return stuDAO.stuStatusCount1(stuStatus);
	}

	@Override
	public Pager4EasyUI<Stu> queryByEmpName(Pager4EasyUI<Stu> pager, Serializable empName, Serializable beanObject) {
		return stuDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public List<Stu> queryType(int status) {
		return stuDAO.queryType(status);
	}
	@Override
	public Pager4EasyUI<Stu> queryByStuName(Pager4EasyUI<Stu> pager, String stuName, String stuStatus) {
		return stuDAO.queryByStuName(pager, stuName, stuStatus);
	}

	@Override
	public void updateStuStatus(Stu stu) {
		stuDAO.updateStuStatus(stu);
	}
	
	@Override
	public int getRoomStuCount(String roomId) {
		return stuDAO.getRoomStuCount(roomId);
	}
	
	@Override
	public int getGradeStuCount(String gradeId) {
		return stuDAO.getGradeStuCount(gradeId);
	}
	
	@Override
	public String generateStuNo() {
		String maxStuNo = stuDAO.getMaxStuNo();
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		if (maxStuNo != null) {
			if (maxStuNo.startsWith(year)) {
				int no = Integer.valueOf(maxStuNo.substring(year.length()));
				return year + (no + 1);
			} else {
				return year + "1001";
			}
		} else {
			return year + "1001";
		}
				
	}

	@Override
	public Pager4EasyUI<Stu> queryBySelf(Pager4EasyUI<Stu> pager, String stuStatus, String empId) {
		return stuDAO.queryBySelf(pager, stuStatus, empId);
	}

	@Override
	public long stuStatusCount(String stuStatus, String empId) {
		return stuDAO.stuStatusCount(stuStatus, empId);
	}

	@Override
	public Stu queryByLogin(Stu stu) {
		return stuDAO.queryByLogin(stu);
	}

}
