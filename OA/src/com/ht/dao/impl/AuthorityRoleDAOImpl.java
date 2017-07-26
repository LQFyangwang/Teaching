package com.ht.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.AuthorityRole;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.AuthorityRoleDAO;

public class AuthorityRoleDAOImpl extends AbstractBaseDAO<AuthorityRole> implements AuthorityRoleDAO {

	@SuppressWarnings("unchecked")
	@Override
	public boolean queryByRole(String methodName, String roleId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from AuthorityRole where authority.name = ? and role.roleId = ?");
		query.setParameter(0, methodName);
		query.setParameter(1, roleId);
		List<AuthorityRole> ars = query.list();
		if (ars != null && ars.size() > 0) {
			return true;
		}
		return false;
	}

}
