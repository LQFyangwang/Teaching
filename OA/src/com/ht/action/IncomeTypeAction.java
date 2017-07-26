package com.ht.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.IncomeType;
import com.ht.common.bean.ComboBox4EasyUI;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.IncomeTypeService;

public class IncomeTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest req;
	private IncomeTypeService itService;
	private IncomeType it;
	private List<IncomeType> rows;
	private long total;
	private String id;
	private ControllerResult result;
	private List<ComboBox4EasyUI> cobox;
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.req = arg0;
	}

	public IncomeType getIt() {
		return it;
	}

	public void setIt(IncomeType it) {
		this.it = it;
	}

	public List<IncomeType> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setItService(IncomeTypeService itService) {
		this.itService = itService;
	}

	public List<ComboBox4EasyUI> getCobox() {
		return cobox;
	}

	public String query_page() {
		Pager4EasyUI<IncomeType> pager = new Pager4EasyUI<IncomeType>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = itService.queryFreeze("IncomeType", 1, pager);
		pager.setTotal(itService.countStatus("IncomeType", 1));
		rows = pager.getRows();
		total = pager.getTotal();
		return "query_page";
	}
	
	public String query_status() {
		Pager4EasyUI<IncomeType> pager = new Pager4EasyUI<IncomeType>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = itService.queryFreeze("IncomeType", 0, pager);
		pager.setTotal(itService.countStatus("IncomeType", 0));
		rows = pager.getRows();
		total = pager.getTotal();
		return "query_page";
	}
	
	public String save() {
		it.setStatus(1);
		itService.save(it);
		result = ControllerResult.setSuccessResult("添加成功");
		return "save";
	}
	
	public String update() {
		itService.update(it);
		result = ControllerResult.setSuccessResult("更新成功");
		return "update";
	}
	
	public String query_name() {
		String value = req.getParameter("name");
		String value2 = req.getParameter("des");
		Pager4EasyUI<IncomeType> pager = new Pager4EasyUI<IncomeType>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if(value != null) {
			pager = itService.queryIncomeTypeName(pager, "name", value);
			pager.setTotal(itService.countName("name", value));
			rows = pager.getRows();
			total = pager.getTotal();
		} else if(value2 != null) {
			pager = itService.queryIncomeTypeName(pager, "des", value2);
			pager.setTotal(itService.countName("des", value2));
			rows = pager.getRows();
			total = pager.getTotal();
		}
		return "query_name";
	}
	
	public String frost() {
		IncomeType pt = itService.queryById(IncomeType.class, id);
		if (pt.getStatus() == 1) {
			result = ControllerResult.setSuccessResult("冻结成功");
			itService.updateStatus("IncomeType", "incomeTypeId", 0, id);
		} else {
			result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
		}
		return "status";
	}
	
	public String activation() {
		IncomeType pt = itService.queryById(IncomeType.class, id);
		if (pt.getStatus() == 0) {
			result = ControllerResult.setSuccessResult("激活成功");
			itService.updateStatus("IncomeType", "incomeTypeId", 1, id);
		} else {
			result = ControllerResult.setFailResult("已经激活了，不能再激活");
		}
		return "status";
	}
	
	public String query_type() {
		List<IncomeType> rows = itService.queryType(1);
		cobox = new ArrayList<ComboBox4EasyUI>();
		for(IncomeType p : rows) {
			ComboBox4EasyUI co = new ComboBox4EasyUI();
			co.setId(p.getIncomeTypeId());
			co.setText(p.getName());
			String incometypeId = req.getParameter("itId");
			if(p.getIncomeTypeId().equals(incometypeId)) {
				co.setSelected(true);
			}
			cobox.add(co);
		}
		return "query_type";
	}

}
