package com.ht.dao;

import java.util.List;

import com.ht.bean.Role;

public interface RoleTypeDAO extends BaseDAO<Role>{
	
	public List<Role> queryNameAll();
}
