package com.gs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gs.bean.OrderInfo;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.web.WebUtil;
import com.gs.service.OrderInfoService;
import com.gs.service.OrderInfoServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class OrderInfoAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = -2837543750268792387L;
	
	private List<OrderInfo> rows;
	private long total;
	
	private OrderInfoService orderInfoService;
	private HttpServletRequest request;
	
	public OrderInfoAction() {
		orderInfoService = new OrderInfoServiceImpl();
	}
	
	public List<OrderInfo> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public String pager() {
		Pager4EasyUI<OrderInfo> pager = new Pager4EasyUI<>();
		pager.setPageNo(WebUtil.getPageNo(request));
		pager.setPageSize(WebUtil.getPageSize(request));
		pager = orderInfoService.queryByPager(pager);
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
