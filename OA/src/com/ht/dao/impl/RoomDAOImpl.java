package com.ht.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.Room;
import com.ht.bean.Stu;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.RoomDAO;

public class RoomDAOImpl extends AbstractBaseDAO<Room> implements RoomDAO {
	
	public Pager4EasyUI<Room> queryByPager(String beanName, Pager4EasyUI<Room> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + beanName);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Room> list = query.list();
		for (Room r : list) {
			String stuId = r.getStuId();
			Query q = session.createQuery("from Stu where id = ?");
			q.setParameter(0, stuId);
			r.setStu((Stu) q.uniqueResult());
		}
		pager.setRows(list);
		return pager;
	}

	@Override
	public Pager4EasyUI<Room> queryByRoomName(Pager4EasyUI<Room> pager, String roomName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Room where name like ?");
		query.setParameter(0, "%" + roomName + "%");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Room> list = query.list();
		if (list.size() > 0) {
			pager.setRows(list);
		}
		return pager;
	}
	
}
