package com.ht.dao;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Stu;
import com.ht.common.bean.Pager4EasyUI;

public interface StuDAO extends BaseDAO<Stu> {

	/**
	 * 根据班级的Id分页查询该班级的所有学生
	 * @param pager 分页查询
	 * @param gradeId 班级Id
	 * @return
	 */
	public Pager4EasyUI<Stu> queryByGradeIdPager(Pager4EasyUI<Stu> pager, Serializable gradeId);
	
	public Pager4EasyUI<Stu> queryByGradePager(Pager4EasyUI<Stu> pager, Serializable gradeId);
	
	/**
	 * 根据班级id查询班级的学生个数
	 * @param gradeId
	 * @return
	 */
	public long gradeCount(Serializable gradeId);
	
	public long gradeCount1(Serializable gradeId);
	
	/**
	 * 根据学生状态（意向，预定， 正式）查询学生
	 * @param pager
	 * @param stuStatus 学生状态
	 * @return
	 */
	public Pager4EasyUI<Stu> stuStatusByPager(Pager4EasyUI<Stu> pager, String stuStatus);
	
	public Pager4EasyUI<Stu> stuStatusByPager1(Pager4EasyUI<Stu> pager, String stuStatus);
	
	/**
	 * 根据学生状态查询自己的学生
	 * @param pager
	 * @param stuStatus
	 * @param empId
	 * @return
	 */
	public Pager4EasyUI<Stu> queryBySelf(Pager4EasyUI<Stu> pager, String stuStatus, String empId);
	
	/**
	 * 根据宿舍id分页查询该宿舍的所有学生
	 * @param pager
	 * @param roomId
	 * @return
	 */
	public Pager4EasyUI<Stu> queryByRoomIdPager(Pager4EasyUI<Stu> pager, Serializable roomId);
	
	/**
	 * 根据班级id和需要添加的学生的id进行批量添加
	 * @param gradeId
	 * @param stuIds
	 */
	public void addStusToGrade(String gradeId, String[] stuIds);
	
	/**
	 * 根据宿舍id和需要添加的学生的id进行批量添加
	 * @param roomId
	 * @param stuIds
	 */
	public void addStusToRoom(String roomId, String[] stuIds);
	
	/**
	 * 根据学生的状态查询学生的个数
	 * @param stuStatus
	 * @return
	 */
	public long stuStatusCount(String stuStatus);
	
	public long stuStatusCount1(String stuStatus);
	
	public long stuStatusCount(String stuStatus, String empId);
	
	public List<Stu> queryType(int status);
	
	/**
	 * 根据学生名字模糊搜索
	 * @param pager
	 * @param stuName
	 * @param stuStatus
	 * @return
	 */
	public Pager4EasyUI<Stu> queryByStuName(Pager4EasyUI<Stu> pager, String stuName, String stuStatus);
	
	/**
	 * 根据Stu更新学生状态
	 * @param t
	 */
	public void updateStuStatus(Stu stu);
	
	/**
	 * 获取该宿舍已经有多少人
	 * @param roomId
	 * @return
	 */
	public int getRoomStuCount(String roomId);
	
	/**
	 * 获取改班级已经有多少人
	 * @param gradeId
	 * @return
	 */
	public int getGradeStuCount(String gradeId);
	
	/**
	 * 获取最大学号
	 * @return
	 */
	public String getMaxStuNo(); // 获取最大学号
	
	/**
	 * 学生进行登入验证
	 * @param stu
	 * @return
	 */
	public Stu queryByLogin(Stu stu);
}
