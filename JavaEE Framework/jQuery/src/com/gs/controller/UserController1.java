package com.gs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gs.bean.User;

public class UserController1 extends HttpServlet {

	private static final long serialVersionUID = 4133479893933382431L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json"); // 设置response对象返回什么样类型的数据
		String name = req.getParameter("name");
		String hobby = req.getParameter("hobby");
		User user = new User();
		user.setName(name);
		user.setAge(20);
		user.setPwd("123456");
		user.setHobby(hobby);
		PrintWriter writer = resp.getWriter();
		String json = "{\"name\":\"" + user.getName() + "\",\"age\":" + user.getAge() + ",\"hobby\":\"" + user.getHobby() + "\",\"pwd\":\"" + user.getPwd() + "\"}";
		System.out.println(json);
		writer.write(json);
	}	
	
}
