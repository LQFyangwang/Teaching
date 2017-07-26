package com.gs.test;

import org.junit.Test;

import com.gs.bean.Order;
import com.gs.bean.OrderProduct;
import com.gs.bean.Product;
import com.gs.bean.User;
import com.gs.dao.OrderDAO;
import com.gs.dao.OrderDAOImpl;
import com.gs.dao.OrderProductDAO;
import com.gs.dao.OrderProductDAOImpl;

import junit.framework.TestCase;

public class OrderProductDAOTest extends TestCase {

	private OrderProductDAO opDAO;
	private OrderDAO oDAO;
	
	@Override
	protected void setUp() throws Exception {
		opDAO = new OrderProductDAOImpl();
		oDAO = new OrderDAOImpl();
	}

	@Test
	public void testSave() {
		OrderProduct op = new OrderProduct();
		Order orderr = new Order();
		User user = new User();
		user.setId(2);
		orderr.setUser(user);
		oDAO.save(orderr); // 先保存Order信息，只有保存了Order信息后，才能够从这里拿信息放到t_order_product
		op.setOrderr(orderr);
		Product product = new Product();
		product.setId(1);
		op.setProduct(product);
		opDAO.save(op);
	}

}
