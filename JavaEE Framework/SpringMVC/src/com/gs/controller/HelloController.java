package com.gs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gs.bean.User;

@Controller
@RequestMapping("/")
public class HelloController {
	
	@RequestMapping("hello")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView("hello");
		mav.addObject("key", "value");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("user")
	public User query() {
		User user = new User();
		user.setId(1001);
		user.setName("aaa");
		return user;
	}
	
	@ResponseBody
	@RequestMapping("all_user")
	public List<User> queryAll() {
		User user = new User();
		user.setId(100);
		user.setName("aaa");
		User user1 = new User();
		user1.setId(101);
		user1.setName("bbbb");
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user);
		return users;
	}

}
