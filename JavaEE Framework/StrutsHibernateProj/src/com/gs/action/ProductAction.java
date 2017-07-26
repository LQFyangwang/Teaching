package com.gs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gs.bean.Product;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.web.WebUtil;
import com.gs.service.ProductService;
import com.gs.service.ProductServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = -2837543750268792387L;
	
	private ProductService productService;
	private HttpServletRequest request;
	
	public List<Product> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	private List<Product> rows;
	private long total;
	
	public ProductAction() {
		productService = new ProductServiceImpl();
	}
	
	public String pager() {
		Pager4EasyUI<Product> pager = new Pager4EasyUI<>();
		pager.setPageNo(WebUtil.getPageNo(request));
		pager.setPageSize(WebUtil.getPageSize(request));
		pager = productService.queryByPager(pager);
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

}
