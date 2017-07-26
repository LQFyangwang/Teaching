package com.gs.test;

import org.junit.Test;

import com.gs.bean.OrderInfo;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.dao.OrderInfoDAO;
import com.gs.dao.OrderInfoDAOImpl;

import junit.framework.TestCase;

public class OrderInfoDAOTest extends TestCase {
	
private OrderInfoDAO od;
	
	@Override
	public void setUp() {
		od = new OrderInfoDAOImpl();
	}
	
	@Test
	public void testPager() {
		Pager4EasyUI<OrderInfo> pager = new Pager4EasyUI<>();
		pager.setPageNo(1);
		pager.setPageSize(2);
		pager = od.queryByPager(pager);
		pager.setTotal(od.count());
	}

}
