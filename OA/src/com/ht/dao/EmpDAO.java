package com.ht.dao;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Emp;
import com.ht.common.bean.Pager4EasyUI;

public interface EmpDAO extends BaseDAO<Emp> {

	/**
	 * 进行登入验证
	 * @param emp
	 * @return
	 */
	public Emp queryByLogin(Emp emp);
	
	/**
	 * 通过部门ID去查员工分页
	 * @param pager
	 * @param depId
	 * @return
	 */
	public Pager4EasyUI<Emp> queryByPager2(Pager4EasyUI<Emp> pager, String depId);
	/**
	 * 通过部门ID去统计有多少个员工
	 * @param depId
	 * @return
	 */
	public Long depIdCount(String depId);
	
	/**
	 * 查询是否激活的员工
	 * @param status
	 * @return
	 */
	public List<Emp> queryType(int status);
	
	/**
	 * 根据角色名称Id去查询属于该角色名称的所有员工
	 * @param pager分页查询
	 * @param empId员工Id
	 * @return
	 */
	public Pager4EasyUI<Emp> queryByEmpIdPager(Pager4EasyUI<Emp> pager, Serializable roleId);
	
	/**
	 * 根据员工id查询员工总个数
	 * @param empId
	 * @return
	 */
	public long empCount(Serializable roleId);
	
	/**
	 * 根据角色id查员工
	 * @param pager
	 * @param roleId
	 * @return
	 */
	public Pager4EasyUI<Emp> queryEmpByRoleId(Pager4EasyUI<Emp> pager, String roleId);
	
	/**
	 * 根据传递的roleId计数
	 * @param roleId
	 * @return
	 */
	public long empRoleIdCount(String roleId);
	
	
	/**
	 * 根据员工姓名查员工
	 * @param pager
	 * @param empName
	 * @param beanObject
	 * @return
	 */
	public Pager4EasyUI<Emp> queryByName(Pager4EasyUI<Emp> pager, Serializable empName,Serializable beanObject);
	
	/**
	 * 添加员工之前，验证邮箱，查询数据库是否已经存在
	 * @param emp
	 * @return
	 */
	public boolean queryByEmail(Emp emp);
	/**
	 * 剔除登入员工的信息分页
	 * @param beanName
	 * @param pager
	 * @param empId
	 * @return
	 */
	public Pager4EasyUI<Emp> queryByPagerEmpId(Pager4EasyUI<Emp> pager,String empId);
	
	public Emp QueryEmp(String empId);
	
	public void updateSalaryInfo(Emp emp);
}
