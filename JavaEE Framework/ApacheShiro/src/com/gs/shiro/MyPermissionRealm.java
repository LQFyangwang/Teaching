package com.gs.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.gs.bean.User;
import com.gs.service.UserService;
import com.gs.service.UserServiceImpl;

public class MyPermissionRealm extends AuthorizingRealm {

	/**
	 * 权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection col) {
		// 创建一个简单授权信息类
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("role1");
		authorizationInfo.addObjectPermission(new WildcardPermission("user:*"));
		authorizationInfo.addStringPermission("user:query");
		authorizationInfo.addStringPermission("user2:*");
		return authorizationInfo;  
	}

	/**
	 * 身份认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
 		String password = String.valueOf((char[])token.getCredentials());
		UserService userService = new UserServiceImpl();
 		User user = userService.queryByEmailAndPwd(username, password);
 		if (user != null) {
 			return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
 		}
		return null;
	}

}
