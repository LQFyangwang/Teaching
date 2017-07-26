package com.gs.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class MyMethodFilterInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -8191718246663477243L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println("method interceptor...");
		return invocation.invoke();
	}

}
