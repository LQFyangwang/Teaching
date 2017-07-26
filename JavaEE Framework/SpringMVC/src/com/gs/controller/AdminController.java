package com.gs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gs.bean.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value = "info", method = RequestMethod.GET)
	public ModelAndView info() {
		ModelAndView mav = new ModelAndView("admin/info");
		Admin admin = new Admin();
		admin.setName("admin1");
		admin.setAge(30);
		admin.setPwd("123446");
		mav.addObject("admin", admin);
		return mav;
	}

}
