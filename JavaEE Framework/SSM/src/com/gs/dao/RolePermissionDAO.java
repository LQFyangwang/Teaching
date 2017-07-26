package com.gs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 根据角色名称获取权限字符串
 *
 */
@Repository
public interface RolePermissionDAO {
	
	public List<String> queryAllPermissionByRoleName(String roleName);

}
