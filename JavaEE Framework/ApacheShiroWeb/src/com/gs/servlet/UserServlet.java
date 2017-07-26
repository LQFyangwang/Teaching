package com.gs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = -5968241979925876711L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("login")) {
			String username = req.getParameter("name");
			String pwd = req.getParameter("pwd");
			Subject subject = SecurityUtils.getSubject();  
	        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);  
	        try {  
	            subject.login(token);
	            System.out.println(subject.getPrincipal().toString());
	            Session session =  subject.getSession();
	            System.out.println("ssss");
	            // 根据用户的名称去查找用户的其他信息
	            session.setAttribute("user", username); // 本质上用得是HttpSession
	            resp.sendRedirect("home");
	        } catch (UnknownAccountException e) { 
	        	e.printStackTrace();
	        } catch (IncorrectCredentialsException e) {  
	        	e.printStackTrace();
	        } catch (AuthenticationException e) { 
	        	e.printStackTrace();
	        }  
		} else if (uri.contains("logout")) {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
		} else if (uri.contains("home")) {
			req.getRequestDispatcher("/home.jsp").forward(req, resp);
		}
	}
	
}
