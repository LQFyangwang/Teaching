package com.ht.service.impl;

import java.util.List;

import com.ht.bean.Role;
import com.ht.dao.RoleTypeDAO;
import com.ht.service.RoleTypeService;

public class RoleTypeServiceImpl implements RoleTypeService{
	private RoleTypeDAO roleTypeDAO;
	
	public RoleTypeDAO getRoleTypeDAO() {
		return roleTypeDAO;
	}

	public void setRoleTypeDAO(RoleTypeDAO roleTypeDAO) {
		this.roleTypeDAO = roleTypeDAO;
	}

	@Override
	public List<Role> queryNameAll() {
		return roleTypeDAO.queryNameAll();
	}

}
