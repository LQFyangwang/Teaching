package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.info.PayInfo;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.PayInfoService;

public class PayInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest req;
	private PayInfoService piService;
	private PayInfo pi;
	private List<PayInfo> rows;
	private long total;
	private ControllerResult result;

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.req = arg0;
	}
	public PayInfo getPi() {
		return pi;
	}
	public void setPi(PayInfo pi) {
		this.pi = pi;
	}
	public List<PayInfo> getRows() {
		return rows;
	}
	public long getTotal() {
		return total;
	}
	public ControllerResult getResult() {
		return result;
	}
	public void setPiService(PayInfoService piService) {
		this.piService = piService;
	}
	
	public String query_page() {
		Pager4EasyUI<PayInfo> pager = new Pager4EasyUI<PayInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = piService.queryPayInfoPage(1, pager);
		pager.setTotal(piService.countStatus(1));
		rows = pager.getRows();
		total = pager.getTotal();
		return "query_page";
	}

}
