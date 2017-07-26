package com.gs.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroPermissionRealmTest {
	
	@Test
	public void testPermission() {
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission-jdbcrealm.ini");  
	    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("abc@126.com", "123456");  
	    try {  
	        subject.login(token);  
	        System.out.println(subject.hasRole("admin"));
	        subject.checkPermission("customer:add");
	        if (subject.isPermitted("customer:add")) {
	        	System.out.println("有customer:add权限");
	        }
	        // subject.checkPermission("customer:add");
	    } catch (AuthorizationException e) {
	    	e.printStackTrace();
	    	System.out.println("没有此权限");
	    } catch (UnknownAccountException e) { // 未知的账户异常
	    	e.printStackTrace();
	    	System.out.println("不存在该账户");
	    } catch (IncorrectCredentialsException e) { // 不正确的凭证异常
	    	e.printStackTrace();
	    	System.out.println("密码错误");
	    } catch (AuthenticationException e) {
	    	e.printStackTrace();
	    	System.out.println("身份验证失败，账号或密码错误");
	    } 
	    Session session = subject.getSession(); // 会话
	    session.setAttribute("key", "value");
	    System.out.println(session.getAttribute("key"));
	    subject.logout();  
	}

}
