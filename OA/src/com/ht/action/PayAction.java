package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.Pay;
import com.ht.bean.info.PayInfo;
import com.ht.common.DateUtil;
import com.ht.common.DayUtil;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.PayInfoService;
import com.ht.service.PayService;

public class PayAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest req;
	private PayInfoService piService;
	private PayService payService;
	private List<PayInfo> rows;
	private long total;
	private ControllerResult result;
	private Pay pay;
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.req = arg0;
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
	public Pay getPay() {
		return pay;
	}
	public void setPay(Pay pay) {
		this.pay = pay;
	}
	public void setPayService(PayService payService) {
		this.payService = payService;
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

	public String save() {
		payService.save(pay);
		result = ControllerResult.setSuccessResult("添加支出记录成功");
		return "save";
	}
	
	public String update() {
		payService.update(pay);
		result = ControllerResult.setSuccessResult("更新支出记录成功");
		return "update";
	}
	
	public String query_name() {
		String value = req.getParameter("empPhone");
		String value2 = req.getParameter("payTypeName");
		String value3 = req.getParameter("toName");
		String value4 = req.getParameter("payName");
		Pager4EasyUI<PayInfo> pager = new Pager4EasyUI<PayInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if(value != null) {
			pager = piService.queryPageName(pager, "p.toPhone", value);
			pager.setTotal(piService.countName("p.toPhone", value));
			rows = pager.getRows();
			total = pager.getTotal();
			if (pager.getRows().size() > 0 && rows != null) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("查无记录");
			}
		} else if(value2 != null) {
			pager = piService.queryPageName(pager, "pt.name", value2);
			pager.setTotal(piService.countName("pt.name", value2));
			rows = pager.getRows();
			total = pager.getTotal();
			if (pager.getRows().size() > 0 && rows != null) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("查无记录");
			}
		} else if(value3 != null) {
			pager = piService.queryPageName(pager, "p.toname", value3);
			pager.setTotal(piService.countName("p.toname", value3));
			rows = pager.getRows();
			total = pager.getTotal();
			if (pager.getRows().size() > 0 && rows != null) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("查无记录");
			}
		} else if(value4 != null) {
			pager = piService.queryPageName(pager, "p.pay", value4);
			pager.setTotal(piService.countName("p.pay", value4));
			rows = pager.getRows();
			total = pager.getTotal();
			if (pager.getRows().size() > 0 && rows != null) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("查无记录");
			}
		}
		return "query_name";
	}
	
	public String query_desc() {
		Pager4EasyUI<PayInfo> pager = new Pager4EasyUI<PayInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = piService.queryPageDesc(pager, "desc");
		pager.setTotal(piService.countStatus(1));
		rows = pager.getRows();
		total = pager.getTotal();
		return "query_desc";
	}
	
	public String query_nodesc() {
		Pager4EasyUI<PayInfo> pager = new Pager4EasyUI<PayInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = piService.queryPageDesc(pager, "");
		pager.setTotal(piService.countStatus(1));
		rows = pager.getRows();
		total = pager.getTotal();
		return "query_nodesc";
	}
	
	public String query_day() {
		Pager4EasyUI<PayInfo> pager = new Pager4EasyUI<PayInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = piService.queryPageDay(pager, DayUtil.getCurrentMonday(), DayUtil.getPreviousSunday());
		pager.setTotal(piService.countStatus(1));
		rows = pager.getRows();
		total = pager.getTotal();
		return "query_day";
	}
	
	public String query_month() {
		Pager4EasyUI<PayInfo> pager = new Pager4EasyUI<PayInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = piService.queryPageDay(pager, DayUtil.getMinMonthDate(DateUtil.getDateStr(DateUtil.getDate())), DayUtil.getMaxMonthDate(DateUtil.getDateStr(DateUtil.getDate())));
		rows = pager.getRows();
		total = rows.size();
		return "query_month";
	}
	
}
