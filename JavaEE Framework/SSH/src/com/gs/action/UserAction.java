package com.gs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.gs.bean.User;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.web.WebUtil;
import com.gs.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 166587521195770571L;
	
	private Logger logger = Logger.getLogger(UserAction.class);
	
	private HttpServletRequest request;
	
	private UserService userService;
	
	private User user;
	private List<User> rows;
	private long total;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public String id() {
		Subject subject = SecurityUtils.getSubject();
		subject.login(new UsernamePasswordToken("abc@126.com", "123456"));
		System.out.println(subject.getPrincipal());
		
		user = userService.queryById(User.class, "faea0fc8c0d211e6b7bb507b9d3ffd38");
		System.out.println(user);
		return "id";
	}
	
	public String pager() {
		logger.info("分页查找用户");
		Pager4EasyUI<User> pager = new Pager4EasyUI<User>();
		pager.setPageNo(WebUtil.getPageNo(request));
		pager.setPageSize(WebUtil.getPageSize(request));
		pager = userService.queryByPager("User", pager);
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}
	
	public String save() {
		User user = new User();
		user.setEmail("test11@qq.com");
		user.setPwd("123456");
		userService.save(user);
		return "save";
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

}
