package com.ht.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.ht.bean.PayType;
import com.ht.common.bean.ComboBox4EasyUI;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.PayTypeService;

public class PayTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest req;
	private PayTypeService ptService;
	private PayType pt;
	private List<PayType> rows;
	private long total;
	private String id;
	private ControllerResult result;
	private List<ComboBox4EasyUI> cobox;
	
	public List<ComboBox4EasyUI> getCobox() {
		return cobox;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.req = arg0;
	}
	public PayType getPt() {
		return pt;
	}
	public void setPt(PayType pt) {
		this.pt = pt;
	}
	public void setPtService(PayTypeService ptService) {
		this.ptService = ptService;
	}
	public List<PayType> getRows() {
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
	public String query_page() {
		Pager4EasyUI<PayType> pager = new Pager4EasyUI<PayType>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = ptService.queryFreeze("PayType", 1, pager);
		pager.setTotal(ptService.countStatus("PayType", 1));
		rows = pager.getRows();
		total = pager.getTotal();
		return "query_page";
	}
	
	public String query_status() {
		Pager4EasyUI<PayType> pager = new Pager4EasyUI<PayType>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = ptService.queryFreeze("PayType", 0, pager);
		pager.setTotal(ptService.countStatus("PayType", 0));
		rows = pager.getRows();
		total = pager.getTotal();
		return "query_page";
	}
	
	public String save() {
		pt.setStatus(1);
		ptService.save(pt);
		result = ControllerResult.setSuccessResult("添加成功");
		return "save";
	}
	
	public String update() {
		ptService.update(pt);
		result = ControllerResult.setSuccessResult("更新成功");
		return "update";
	}
	
	public String query_name() {
		String value = req.getParameter("name");
		String value2 = req.getParameter("des");
		Pager4EasyUI<PayType> pager = new Pager4EasyUI<PayType>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if(value != null) {
			pager = ptService.queryPayTypeName(pager, "name", value);
			pager.setTotal(ptService.countName("name", value));
			rows = pager.getRows();
			total = pager.getTotal();
		} else if(value2 != null) {
			pager = ptService.queryPayTypeName(pager, "des", value2);
			pager.setTotal(ptService.countName("des", value2));
			rows = pager.getRows();
			total = pager.getTotal();
		}
		return "query_name";
	}
	
	public String frost() {
		PayType pt = ptService.queryById(PayType.class, id);
		if (pt.getStatus() == 1) {
			result = ControllerResult.setSuccessResult("冻结成功");
			ptService.updateStatus("PayType", "payTypeId", 0, id);
		} else {
			result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
		}
		return "status";
	}
	
	public String activation() {
		PayType pt = ptService.queryById(PayType.class, id);
		if (pt.getStatus() == 0) {
			result = ControllerResult.setSuccessResult("激活成功");
			ptService.updateStatus("PayType", "payTypeId", 1, id);
		} else {
			result = ControllerResult.setFailResult("已经激活了，不能再激活");
		}
		return "status";
	}
	
	public String query_type() {
		List<PayType> rows = ptService.queryType(1);
		cobox = new ArrayList<ComboBox4EasyUI>();
		for(PayType p : rows) {
			ComboBox4EasyUI co = new ComboBox4EasyUI();
			co.setId(p.getPayTypeId());
			co.setText(p.getName());
			String ptId = req.getParameter("ptId");
			if(p.getPayTypeId().equals(ptId)) {
				co.setSelected(true);
			}
			cobox.add(co);
		}
		return "query_type";
	}
}
