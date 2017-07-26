package com.gs.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gs.action.LoginAction;

public class SpringTest {
	
	@Test
	public void testSpring() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		LoginAction action = (LoginAction) context.getBean("loginAction");
		action.execute();
		context.close();
	}

}
