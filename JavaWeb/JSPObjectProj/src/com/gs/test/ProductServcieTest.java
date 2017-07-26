package com.gs.test;

import org.junit.Test;

import com.gs.service.ProductService;
import com.gs.service.ProductServiceImpl;

public class ProductServcieTest {
	
	@Test
	public void testQueryById() {
		ProductService ps = new ProductServiceImpl();
		System.out.println(ps.queryById(1));
	}

}
