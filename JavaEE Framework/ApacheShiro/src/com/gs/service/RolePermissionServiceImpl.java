package com.gs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

import com.gs.dao.RolePermissionDAO;
import com.gs.dao.RolePermissionDAOImpl;

public class RolePermissionServiceImpl implements RolePermissionService {

	private RolePermissionDAO dao = new RolePermissionDAOImpl();

	@Override
	public Collection<Permission> queryAllPermissionByRoleName(String roleName) {
		List<String> p = dao.queryAllPermissionByRoleName(roleName);
		List<Permission> permissions = new ArrayList<Permission>();
		for (String s : p) {
			Permission per = new WildcardPermission(s);
			permissions.add(per);
		}
		return permissions;
	}
	
}
