package com.gs.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TestAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		// mi实际上是由spring的动态代理所生成的一个代理对象，这个代理对象代理了TestServiceImpl的test方法
		System.out.println("你好，执行前");
		Object obj = mi.proceed(); // TestServiceImpl的对象去执行test方法
		System.out.println("执行后");
		return obj;
	}

}
