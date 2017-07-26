package com.gs.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class RolePermissionDAOImpl extends AbstractBaseDAO<String> implements RolePermissionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryAllPermissionByRoleName(String roleName) {
		Session session = getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("select permission from roles_permissions where role_name = ?");
		query.setString(0, roleName);
		return query.list();
	}

}
