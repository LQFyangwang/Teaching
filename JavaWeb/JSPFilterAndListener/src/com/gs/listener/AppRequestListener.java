package com.gs.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class AppRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("请求销毁....");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("请求初始化....");
		arg0.getServletRequest();
	}

}
