package com.ht.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.Income;
import com.ht.bean.info.IncomeInfo;
import com.ht.common.DateUtil;
import com.ht.common.DayUtil;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.IncomeInfoService;
import com.ht.service.IncomeService;

public class IncomeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest req;
	private IncomeInfoService iiService;
	private IncomeInfo ii;
	private IncomeService incomeService;
	private List<IncomeInfo> rows;
	private long total;
	private ControllerResult result;
	private Income income;
	
	
	public IncomeInfo getIi() {
		return ii;
	}
	public void setIi(IncomeInfo ii) {
		this.ii = ii;
	}
	public Income getIncome() {
		return income;
	}
	public void setIncome(Income income) {
		this.income = income;
	}
	public List<IncomeInfo> getRows() {
		return rows;
	}
	public void setIiService(IncomeInfoService iiService) {
		this.iiService = iiService;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.req = arg0;
	}
	public void setIncomeService(IncomeService incomeService) {
		this.incomeService = incomeService;
	}
	public long getTotal() {
		return total;
	}
	public ControllerResult getResult() {
		return result;
	}
	public String query_page() {
		Pager4EasyUI<IncomeInfo> pager = new Pager4EasyUI<IncomeInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = iiService.queryIncomeInfoPage(1, pager);
		pager.setTotal(iiService.countStatus(1));
		rows = pager.getRows();
		total = pager.getTotal();
		return "query_page";
	}

	public String save() {
		income.setIncomeDay(new Date());
		incomeService.save(income);
		result = ControllerResult.setSuccessResult("添加收入记录成功");
		return "save";
	}
	
	public String update() {
		incomeService.update(income);
		result = ControllerResult.setSuccessResult("更新收入记录成功");
		return "update";
	}
	
	public String query_name() {
		String value = req.getParameter("stuName");
		String value2 = req.getParameter("incomeTypeName");
		String value3 = req.getParameter("incomeName");
		String value4 = req.getParameter("des");
		Pager4EasyUI<IncomeInfo> pager = new Pager4EasyUI<IncomeInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if(value != null) {
			pager = iiService.queryPageName(pager, "s.name", value);
			pager.setTotal(iiService.countName("s.name", value3));
			rows = pager.getRows();
			total = pager.getTotal();
			if (pager.getRows().size() > 0 && rows != null) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("查无记录");
			}
		} else if(value2 != null) {
			pager = iiService.queryPageName(pager, "it.name", value2);
			pager.setTotal(iiService.countName("it.name", value3));
			rows = pager.getRows();
			total = pager.getTotal();
			if (pager.getRows().size() > 0 && rows != null) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("查无记录");
			}
		} else if(value3 != null) {
			pager = iiService.queryPageName(pager, "i.income", value3);
			pager.setTotal(iiService.countName("i.income", value3));
			rows = pager.getRows();
			total = pager.getTotal();
			if (pager.getRows().size() > 0 && rows != null) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("查无记录");
			}
		} else if(value4 != null) {
			pager = iiService.queryPageName(pager, "i.des", value4);
			pager.setTotal(iiService.countName("i.des", value4));
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
	
	public String query_day() {
		Pager4EasyUI<IncomeInfo> pager = new Pager4EasyUI<IncomeInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = iiService.queryPageDay(pager, DayUtil.getCurrentMonday(), DayUtil.getPreviousSunday());
		rows = pager.getRows();
		total = rows.size();
		return "query_day";
	}
	
	public String query_month() {
		Pager4EasyUI<IncomeInfo> pager = new Pager4EasyUI<IncomeInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = iiService.queryPageDay(pager, DayUtil.getMinMonthDate(DateUtil.getDateStr(DateUtil.getDate())), DayUtil.getMaxMonthDate(DateUtil.getDateStr(DateUtil.getDate())));
		rows = pager.getRows();
		total = rows.size();
		return "query_month";
	}
}
