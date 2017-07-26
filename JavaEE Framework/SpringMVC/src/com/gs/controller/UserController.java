package com.gs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value = "reg_page", method = RequestMethod.GET)
	public ModelAndView regPage(HttpSession session) {
		session.setAttribute("key", "value");
		return new ModelAndView("user/reg");
	}
	
	@ResponseBody
	@RequestMapping(value = "reg/{a}/{b}", method = RequestMethod.POST)
	public ControllerResult reg(@PathVariable("a") String a, @PathVariable("b") int b, @RequestParam("param")String param, String conPwd, User user) {
		System.out.println(a + b);
		System.out.println(param);
		System.out.println(user.getName() + ", " + user.getPwd() + ", " + conPwd);
		return ControllerResult.getSuccessResult("注册成功");
	}
	
	@RequestMapping(value = "edit_page", method = RequestMethod.GET)
	public ModelAndView editPage(HttpServletRequest request) {
		User user = new User();
		user.setName("aaaa");
		user.setPwd("123456");
		request.setAttribute("key", "value");
		ModelAndView mav = new ModelAndView("user/edit");
		mav.addObject("user", user);
		return mav;
	}
	
}
