package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Emp;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.EmpDAO;
import com.ht.service.BaseService;
import com.ht.service.EmpService;

public class EmpServiceImpl implements BaseService<Emp>, EmpService {
	
	private EmpDAO empDAO;

	public EmpDAO getEmpDAO() {
		return empDAO;
	}

	public void setEmpDAO(EmpDAO empDAO) {
		this.empDAO = empDAO;
	}

	@Override
	public void save(Emp t) {
		empDAO.save(t);
	}

	@Override
	public void delete(Emp t) {
		empDAO.delete(t);
	}

	@Override
	public void update(Emp t) {
		empDAO.update(t);
	}

	@Override
	public Emp queryById(Class<?> clazz, Serializable s) {
		return empDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Emp> queryByPager(String beanName, Pager4EasyUI<Emp> pager) {
		pager = empDAO.queryByPager(beanName, pager);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public List<Emp> queryAll(String beanName) {
		return empDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return empDAO.count(beanName);
	}

	@Override
	public Emp queryByLogin(Emp emp) {
		return empDAO.queryByLogin(emp);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		empDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Emp> queryByPager2(Pager4EasyUI<Emp> pager, String depId) {
		return empDAO.queryByPager2(pager, depId);
	}

	@Override
	public Long depIdCount(String depId) {
		return empDAO.depIdCount(depId);
	}

	@Override
	public Pager4EasyUI<Emp> queryByStuName(Pager4EasyUI<Emp> pager, Serializable stuName, Serializable beanObject) {
		return empDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Emp> queryByDay(Pager4EasyUI<Emp> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return empDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Emp> queryByEmpName(Pager4EasyUI<Emp> pager, Serializable empName, Serializable beanObject) {
		return empDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public List<Emp> queryType(int status) {
		return empDAO.queryType(status);
	}
	
	@Override
	public Pager4EasyUI<Emp> queryByEmpIdPager(Pager4EasyUI<Emp> pager, Serializable roleId) {
		pager = empDAO.queryByEmpIdPager(pager, roleId);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public long empCount(String roleId) {
		return empDAO.empCount(roleId);
	}

	@Override
	public Pager4EasyUI<Emp> queryEmpByRoleId(Pager4EasyUI<Emp> pager, String roleId) {
		pager = empDAO.queryEmpByRoleId(pager, roleId);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<Emp> queryByName(Pager4EasyUI<Emp> pager, Serializable empName,Serializable beanObject) {
		return empDAO.queryByName(pager, empName, beanObject);
	}

	@Override
	public boolean queryByEmail(Emp emp) {
		return empDAO.queryByEmail(emp);
	}

	@Override
	public long empRoleIdCount(String roleId) {
		return empDAO.empRoleIdCount(roleId);
	}

	@Override
	public Pager4EasyUI<Emp> queryByPagerEmpId(Pager4EasyUI<Emp> pager, String empId) {
			pager.setTotal(empDAO.count("Emp")-1);
			return empDAO.queryByPagerEmpId( pager, empId);
	}

	@Override
	public Emp QueryEmp(String empId) {
		return empDAO.QueryEmp(empId);
	}

	@Override
	public void updateSalaryInfo(Emp emp) {
		empDAO.updateSalaryInfo(emp);
	}

}
