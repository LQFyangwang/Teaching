package com.ht.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.NoticeType;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.NoticeTypeDAO;

public class NoticeTypeDAOImpl extends AbstractBaseDAO<NoticeType> implements NoticeTypeDAO{

	@Override
	public Pager4EasyUI<NoticeType> queryBynoticeTypeName(Pager4EasyUI<NoticeType> pager, String noticeTypeName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from NoticeType where name like ?");
		query.setParameter(0, "%" + noticeTypeName + "%");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<NoticeType> list = query.list();
		if (list.size() > 0) {
			pager.setRows(list);
		}
		return pager;
	}

}
