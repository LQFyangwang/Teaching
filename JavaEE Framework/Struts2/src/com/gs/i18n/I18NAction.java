package com.gs.i18n;

import com.opensymphony.xwork2.ActionSupport;

public class I18NAction extends ActionSupport {
	
	private static final long serialVersionUID = -6273088310620080686L;

	public String showPage() {
		System.out.println(getText("test")); // 可以在Action里面通过getText的方法去获取国际化文件中配置的内容
		return "show_page";
	}

}
