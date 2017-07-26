package com.gs.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.gs.bean.OrderInfo;
import com.gs.common.bean.Pager4EasyUI;

public class OrderInfoDAOImpl implements OrderInfoDAO {

	@Override
	public OrderInfo save(OrderInfo t) {
		return null;
	}

	@Override
	public void update(OrderInfo t) {
	}

	@Override
	public OrderInfo queryById(Serializable id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager4EasyUI<OrderInfo> queryByPager(Pager4EasyUI<OrderInfo> pager) {
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery("select o.id, u.email, p.title, p.price, o.o_time from"
				+ " t_order o, t_user u, t_product p, t_order_product op"
				+ " where o.user_id = u.id and o.id = op.order_id and op.product_id = p.id");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		List<Object[]> list = query.list(); // 每一个Object[]存放的是每一行记录的所有字段
		List<OrderInfo> orderInfos = new ArrayList<>();
		for (Object[] objs : list) {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setId((Integer) objs[0]);
			orderInfo.setUserEmail((String) objs[1]); 
			orderInfo.setProductTitle((String) objs[2]);
			orderInfo.setProductPrice((Double) objs[3]);
			orderInfo.setOrderTime((Timestamp) objs[4]);
			orderInfos.add(orderInfo);
		}
		pager.setRows(orderInfos);
		return pager;
	}

	@Override
	public List<OrderInfo> queryAll() {
		return null;
	}

	@Override
	public long count() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select count(id) from Order");
		Long count = (Long) query.uniqueResult();
		session.close();
		return count;
	}

	
}
