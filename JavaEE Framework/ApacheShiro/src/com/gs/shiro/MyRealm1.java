package com.gs.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

	/**
	 * 用于返回验证的情况
	 * AuthenticationToken内部包含有身份和凭证
	 */
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
 		String password = String.valueOf((char[])token.getCredentials());
		if (username.equals("aaa") && password.equals("aaa")) {
			return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
		}
		return null;
	}

	@Override
	public String getName() {
		return "MyRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// return true; // 表示任何一种token都能支持
		return token instanceof UsernamePasswordToken; // 只支持UsernamePasswordToken
	}

}
