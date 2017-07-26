package com.gs.test;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.gs.bean.Order;
import com.gs.bean.OrderProduct;
import com.gs.bean.User;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.dao.OrderDAO;
import com.gs.dao.OrderDAOImpl;

import junit.framework.TestCase;

public class OrderDAOTest extends TestCase {
	
	private OrderDAO od;
	
	@Override
	public void setUp() {
		od = new OrderDAOImpl();
	}
	
	@Test
	public void testSave() {
		Order order = new Order();
		User user = new User();
		user.setId(2);
		order.setUser(user);
		order.setoTime(Calendar.getInstance().getTime());
		od.save(order);
	}
	
	@Test
	public void testUpdate() {
		Order order = new Order();
		User user = new User();
		user.setId(2);
		order.setUser(user);
		order.setId(2);
		order.setoTime(Calendar.getInstance().getTime());
		od.update(order);
	}
	
	@Test
	public void testQueryById() {
		Order order = od.queryById(1);
		System.out.println(order.getoTime());
		System.out.println(order.getUser().getEmail());
		Set<OrderProduct> ops = order.getOps();
		for (OrderProduct op : ops) {
			System.out.println(op.getProduct().getTitle() + ", " + op.getProduct().getPrice());
		}
	}
	
	@Test
	public void testAll() {
		List<Order> orders = od.queryAll();
		for (Order o : orders) {
			System.out.println(o.getoTime() + o.getUser().getEmail());
		}
	}
	
	@Test
	public void testPager() {
		Pager4EasyUI<Order> pager = new Pager4EasyUI<>();
		pager.setPageNo(1);
		pager.setPageSize(2);
		pager = od.queryByPager(pager);
		for (Order o : pager.getRows()) {
			System.out.println(o.getoTime() + ", " + o.getUser().getEmail());
		}
	}

}
