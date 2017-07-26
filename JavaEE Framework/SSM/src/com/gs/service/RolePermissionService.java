package com.gs.service;

import java.util.Collection;

import org.apache.shiro.authz.Permission;

/**
 * 根据角色名称获取权限字符串
 *
 */
public interface RolePermissionService {
	
	public Collection<Permission> queryAllPermissionByRoleName(String roleName);

}
