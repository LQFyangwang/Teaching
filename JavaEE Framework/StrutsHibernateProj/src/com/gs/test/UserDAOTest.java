package com.gs.test;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.gs.bean.Order;
import com.gs.bean.OrderProduct;
import com.gs.bean.User;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.dao.UserDAO;
import com.gs.dao.UserDAOImpl;

import junit.framework.TestCase;

public class UserDAOTest extends TestCase {
	
	private UserDAO ud;
	
	@Override
	public void setUp() {
		ud = new UserDAOImpl();
	}
	
	@Test
	public void testSave() {
		User u = new User();
		u.setEmail("abc@126.com");
		u.setPwd("123456");
		u.setPhone("18888888888");
		ud.save(u);
	}
	
	@Test
	public void testUpdate() {
		User u = new User();
		u.setId(1);
		u.setEmail("abc@126.com");
		u.setPwd("123456");
		u.setPhone("13666666666");
		ud.update(u);
	}
	
	@Test
	public void testQueryById() {
		User user = ud.queryById(1);
		System.out.println(user.getEmail());
		Set<Order> orders = user.getOrders();
		for (Order o : orders) {
			System.out.println(o.getId() + ", " + o.getoTime());
			Set<OrderProduct> ops = o.getOps();
			for (OrderProduct op : ops) {
				System.out.println(op.getProduct().getTitle());
			}
		}
	}
	
	@Test
	public void testAll() {
		List<User> users = ud.queryAll();
		for (User u : users) {
			System.out.println(u.getEmail());
		}
	}
	
	@Test
	public void testPager() {
		Pager4EasyUI<User> pager = new Pager4EasyUI<>();
		pager.setPageNo(2);
		pager.setPageSize(2);
		pager = ud.queryByPager(pager);
		for (User u : pager.getRows()) {
			System.out.println(u.getEmail());
		}
	}

}
