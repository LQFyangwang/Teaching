package com.gs.shiro;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.springframework.stereotype.Component;

import com.gs.service.RolePermissionService;

@Component(value = "rolePermissionRelover")
public class MyRolePermissionResolver implements RolePermissionResolver{
	
		@Resource
		private RolePermissionService rolePermissionService;
	
		/**
		 * 用来返回某个角色所对应的所有权限字符串
		 */
	    @Override
	    public Collection<Permission> resolvePermissionsInRole(String roleName) {
	    	return rolePermissionService.queryAllPermissionByRoleName(roleName);
	    }
}
