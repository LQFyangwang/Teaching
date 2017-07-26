package com.ht.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.Role;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.RoleTypeDAO;

public class RoleTypeDAOImpl extends AbstractBaseDAO<Role> implements RoleTypeDAO {

	@Override
	public List<Role> queryNameAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role");
		@SuppressWarnings("unchecked")
		List<Role> role = query.list();
		return role;
	}

}
