package com.ht.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.Notice;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.NoticeDAO;

public class NoticeDAOImpl extends AbstractBaseDAO<Notice> implements NoticeDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> queryNewNotice() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Notice order by noticeDay desc");
		query.setFirstResult(0);
		query.setMaxResults(3);
		List<Notice> notices = query.list();
		return notices;
	}

}
