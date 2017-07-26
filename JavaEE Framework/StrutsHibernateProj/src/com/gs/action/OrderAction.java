package com.gs.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gs.bean.Order;
import com.gs.bean.OrderInfo;
import com.gs.bean.OrderProduct;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.web.WebUtil;
import com.gs.service.OrderService;
import com.gs.service.OrderServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = -2837543750268792387L;
	
	private List<OrderInfo> rows;
	private long total;
	
	private OrderService orderService;
	private HttpServletRequest request;
	
	public OrderAction() {
		orderService = new OrderServiceImpl();
	}
	
	public List<OrderInfo> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public String pager() {
		Pager4EasyUI<Order> pager = new Pager4EasyUI<>();
		pager.setPageNo(WebUtil.getPageNo(request));
		pager.setPageSize(WebUtil.getPageSize(request));
		pager = orderService.queryByPager(pager);
		List<Order> orders = pager.getRows();
		rows = new ArrayList<OrderInfo>();
		for (Order o : orders) {
			Set<OrderProduct> ops = o.getOps();
			for (OrderProduct op : ops) {
				OrderInfo oi = new OrderInfo();
				oi.setId(o.getId());
				oi.setUserEmail(o.getUser().getEmail());
				oi.setOrderTime(o.getoTime());
				oi.setProductTitle(op.getProduct().getTitle());
				oi.setProductPrice(op.getProduct().getPrice());
				rows.add(oi);
			}
		}
		total = pager.getTotal();
		return "pager";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
