package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Role;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.RoleDAO;
import com.ht.service.RoleService;

public class RoleServiceImpl implements RoleService{

	private RoleDAO roleDAO;
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public void save(Role t) {
		roleDAO.save(t);
	}

	@Override
	public void delete(Role t) {
		roleDAO.delete(t);
	}

	@Override
	public void update(Role t) {
		roleDAO.update(t);
	}

	@Override
	public Role queryById(Class<?> clazz, Serializable s) {
		return roleDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Role> queryByPager(String beanName, Pager4EasyUI<Role> pager) {
		pager = roleDAO.queryByPager(beanName, pager);
		pager.setTotal(roleDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Role> queryAll(String beanName) {
		return roleDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return roleDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		roleDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Role> queryByStuName(Pager4EasyUI<Role> pager, Serializable stuName, Serializable beanObject) {
		return roleDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Role> queryByDay(Pager4EasyUI<Role> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return roleDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Role> queryByEmpName(Pager4EasyUI<Role> pager, Serializable empName, Serializable beanObject) {
		return roleDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public String queryRoleIdByName(String name) {
		return roleDAO.queryRoleIdByName(name);
	}

	@Override
	public Pager4EasyUI<Role> queryByroleName(Pager4EasyUI<Role> pager, String roleName) {
		return roleDAO.queryByroleName(pager, roleName);
	}


}
