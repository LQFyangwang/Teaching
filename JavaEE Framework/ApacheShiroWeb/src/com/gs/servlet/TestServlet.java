package com.gs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TestServlet() {

    }

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.getPrincipal());
		System.out.println(subject.hasRole("admin"));
		if (subject.isPermitted("customer:add")) {
			System.out.println("放行");
		} else {
			System.out.println("返回没有权限的消息");
		}
	}

	

}
