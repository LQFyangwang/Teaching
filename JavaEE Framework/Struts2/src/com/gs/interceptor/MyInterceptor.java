package com.gs.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyInterceptor extends  AbstractInterceptor {

	private static final long serialVersionUID = 4280873003624264212L;

	@Override
	public void destroy() {
		System.out.println("拦截器销毁");
	}

	@Override
	public void init() {
		System.out.println("拦截器正在初始化"); // WEB窗口初始化整个struts时进行初始化
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// ActionInvocation是用于执行Action类里的方法的一个对象
		Map<String, Object> map = invocation.getInvocationContext().getParameters();
		System.out.println(((String[])map.get("name"))[0]);
		System.out.println("MyInterceptor...");
		return invocation.invoke();
		
	}

}
