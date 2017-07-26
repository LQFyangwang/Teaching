package com.ht.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.ht.bean.Role;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.RoleDAO;

public class RoleDAOImpl extends AbstractBaseDAO<Role> implements RoleDAO{

	@Override
	public String queryRoleIdByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select roleid from t_role where name = '" + name + "'");
		return (String) query.uniqueResult();
	}

	@Override
	public Pager4EasyUI<Role> queryByroleName(Pager4EasyUI<Role> pager, String roleName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role where name like ?");
		query.setParameter(0, "%" + roleName + "%");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Role> list = query.list();
		if (list.size() > 0) {
			pager.setRows(list);
		}
		return pager;
	}


}
