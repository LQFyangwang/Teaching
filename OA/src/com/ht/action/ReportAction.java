package com.ht.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.Report;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.ReportService;

public class ReportAction extends BaseAction {

	private static final long serialVersionUID = -8800619177828973772L;

	private ReportService reportService;
	private Report report;
	private HttpServletRequest req;
	private List<Report> rows;
	private String startDay;
	private String endDay;
	private String value;
	private String id;
	private AuthorityRoleService authorityRoleService;
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}
	public void setId(String id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public List<Report> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public ControllerResult getResult() {
		return result;
	}

	private long total;
	private ControllerResult result;

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public String pager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ReportAction.class.getName() + ".pager";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "pager";
		}
		Pager4EasyUI<Report> pager = new Pager4EasyUI<Report>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = reportService.queryByPager("Report", pager);
		List<Report> reports = new ArrayList<Report>();
		if (
				!WebUtil.getRole(req).equals("总经理") && !WebUtil.getRole(req).equals("校长") &&!WebUtil.getRole(req).equals("后勤主任") &&
				!WebUtil.getRole(req).equals("财务主任") && !WebUtil.getRole(req).equals("招生主任") &&!WebUtil.getRole(req).equals("教务主任")) {
			for (Report r : pager.getRows()) {
				if (WebUtil.getEmp(req).getEmpId().equals(r.getEmp().getEmpId())) {
					reports.add(r);
				}
			}
			pager.setRows(reports);
		}
		rows = pager.getRows();
		total =  pager.getRows().size();
		return "pager";
	}

	public String add() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ReportAction.class.getName() + ".add";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "add";
		}
		report.setStatus(1);
		reportService.save(report);
		result = ControllerResult.setSuccessResult("添加成功");
		return "add";
	}

	public String update() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ReportAction.class.getName() + ".update";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "update";
		}
		report.setStatus(1);
		reportService.update(report);
		result = ControllerResult.setSuccessResult("修改成功");
		return "update";
	}

	public String pagerByDay() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ReportAction.class.getName() + ".pagerByDay";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "pagerByDay";
		}
		Pager4EasyUI<Report> pager = new Pager4EasyUI<Report>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = reportService.queryByDay(pager, startDay, endDay, "Report", "reportday");
		rows = pager.getRows();
		if (rows != null) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
		return "pagerByDay";
	}

	public String pagerByName() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ReportAction.class.getName() + ".pagerByName";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "pagerByName";
		}
		Pager4EasyUI<Report> pager = new Pager4EasyUI<Report>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = reportService.queryByEmpName(pager, value, "Report");
		rows = pager.getRows();
		if (rows != null) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
		return "pagerByName";
	}

	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ReportAction.class.getName() + ".frost";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "status";
		}
		Report report = reportService.queryById(Report.class, id);
		if (report.getStatus() == 1) {
			result = ControllerResult.setSuccessResult("冻结成功");
			reportService.updateStatus("Report", "reportId", 0, id);
		} else {
			result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
		}
		return "status";
	}

	public String activation() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ReportAction.class.getName() + ".activation";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "status";
		}
		Report report = reportService.queryById(Report.class, id);
		if (report.getStatus() == 0) {
			result = ControllerResult.setSuccessResult("激活成功");
			reportService.updateStatus("Report", "reportId", 1, id);
		} else {
			result = ControllerResult.setFailResult("已经激活了，不能再激活");
		}
		return "status";
	}

}
