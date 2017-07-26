package com.gs.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroINITest {
	
	/**
	 * 读取ini配置文件的方式实际上是把ini文件内的数据当作数据源：
	 * org.apache.shiro.realm.text.IniRealm
	 */
	@Test  
	public void testShiro() {  
	    //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager 
		// classpath:开头表示在src根目录下
	    Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");  
	    //2、得到SecurityManager实例 并绑定给SecurityUtils  
	    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("test1", "1232");  
	    try {  
	        //4、登录，即身份验证  
	        subject.login(token);  
	        System.out.println(subject.getPrincipal()); // 获取身份（用户，邮箱，手机号）
	    } catch (UnknownAccountException e) { // 未知的账户异常
	    	System.out.println("不存在该账户");
	    } catch (IncorrectCredentialsException e) { // 不正确的凭证异常
	    	System.out.println("密码错误");
	    } catch (AuthenticationException e) {
	        //5、身份验证异常
	    	System.out.println("身份验证失败，账号或密码错误");
	    } 
	    //6、退出  
	    subject.logout();  
	}  
	
}
