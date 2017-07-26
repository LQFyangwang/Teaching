package com.gs.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gs.bean.User;
import com.gs.service.TestService;
import com.gs.service.UserService;

public class SpringTest {
	
	@Test
	public void testSpring() {
		// ApplicationContext是Spring的上下文
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// context.publishEvent(new ContextClosedEvent(context)); // 事件的注册
		User u = (User) context.getBean("user");
		System.out.println(u.getId() + u.getName());
		List<String> books = u.getBooks();
		for (String book : books) {
			System.out.println(book);
		}
		Map<String, String> bookMap = u.getBookMap();
		Set<String> keySet = bookMap.keySet();
		for (String key : keySet) {
			System.out.println(bookMap.get(key));
		}
		UserService userService = (UserService) context.getBean("userService");
		userService.queryById(1);
		
		TestService ts = (TestService) context.getBean("testService");
		ts.test();
		context.close();
	}

}
