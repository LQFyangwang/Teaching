package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.EmpCheckingInfo;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.EmpCheckingInfoService;

public class EmpCheckingInfoAction extends BaseAction{

	private static final long serialVersionUID = -7729023950940034742L;

	private EmpCheckingInfoService empCheckingInfoService;
	private HttpServletRequest req;
	private EmpCheckingInfo empCheckingInfo;
	private List<EmpCheckingInfo> rows; // 返回给easyui的结果
	private long total; // 返回给easyui的总页数
	private ControllerResult result;
	
	public void setEmpCheckingInfoService(EmpCheckingInfoService empCheckingInfoService) {
		this.empCheckingInfoService = empCheckingInfoService;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public EmpCheckingInfo getEmpCheckingInfo() {
		return empCheckingInfo;
	}

	public void setEmpCheckingInfo(EmpCheckingInfo empCheckingInfo) {
		this.empCheckingInfo = empCheckingInfo;
	}
	
	public List<EmpCheckingInfo> getRows() {
		return rows;
	}

	public ControllerResult getResult() {
		return result;
	}
	
	public long getTotal() {
		return total;
	}
	
	public String query() {
		System.out.println("进入EmpCheckingInfoAction -- queryPage...");
		Pager4EasyUI<EmpCheckingInfo> pager = new Pager4EasyUI<EmpCheckingInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = empCheckingInfoService.queryByPager("EmpCheckingInfo", pager);
		rows = pager.getRows();
		total = pager.getTotal();
		return "query";
	}
	
	public String add() {
		System.out.println("进入 EmpCheckingInfoAction --  add方法...");
		empCheckingInfoService.save(empCheckingInfo);
		result = ControllerResult.setSuccessResult("添加成功");
		return "add";
	}
	
	public String update() {
		System.out.println("进入EmpCheckingInfoAction --  update方法...");
		empCheckingInfoService.update(empCheckingInfo);
		result = ControllerResult.setSuccessResult("修改成功");
		return "update";
	}
	
}
