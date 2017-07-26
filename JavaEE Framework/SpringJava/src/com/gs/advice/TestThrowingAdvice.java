package com.gs.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class TestThrowingAdvice implements ThrowsAdvice {

	public void afterThrowing(Method m, Object[] arg1, Object target, Exception e) {
		System.out.println(m.getName() + ", " + e.getMessage() + "*******************");
		System.out.println(target);
	}
}
