package com.gs.shiro;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroRoleTest {
	
	@Test
	public void testShiroRole() {
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-role.ini");  
	    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("test1", "aaa");  
	    try {  
	        subject.login(token);  
	        System.out.println(subject.hasRole("role2"));
	        boolean[] roles = subject.hasRoles(Arrays.asList("role1", "role3"));
	        for (boolean r : roles) {
	        	System.out.println(r);
	        }
	        subject.checkRole("role3");
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
	    subject.logout();  
	}

}
