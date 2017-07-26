package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.Emp;
import com.ht.bean.EmpFeedBack;
import com.ht.common.DateUtil;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.EmpFeedBackService;

public class EmpFeedBackAction extends BaseAction{

	private static final long serialVersionUID = 6819998183101004932L;
	
	private EmpFeedBackService empfeedbackService;
	private HttpServletRequest req;
	
	private EmpFeedBack empfeedback;
	private List<EmpFeedBack> rows;
	private long total;
	private ControllerResult result;
	private String id;
	private String empName;
	private String endDay;
	private String startDay;
	
	private AuthorityRoleService authorityRoleService;
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public void setEmpfeedbackService(EmpFeedBackService empfeedbackService) {
		this.empfeedbackService = empfeedbackService;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public EmpFeedBack getEmpfeedback() {
		return empfeedback;
	}

	public void setEmpfeedback(EmpFeedBack empfeedback) {
		this.empfeedback = empfeedback;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setResult(ControllerResult result) {
		this.result = result;
	}

	public List<EmpFeedBack> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String pager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpFeedBackAction.class.getName() + ".pager";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "pager";
		}
		Pager4EasyUI<EmpFeedBack> pager = new Pager4EasyUI<EmpFeedBack>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = empfeedbackService.queryByPager("EmpFeedBack", pager);
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}
	
	public String add(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpFeedBackAction.class.getName() + ".add";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "add";
		}
		String id = req.getParameter("empfeedback.emp.empId");
		Emp ef = new Emp();
		ef.setEmpId(id);
		empfeedback.setEmp(ef);
		empfeedback.setStatus(1);
		empfeedback.setFeedBackDay(DateUtil.getDate());
		empfeedbackService.save(empfeedback);
		result = ControllerResult.setSuccessResult("添加成功");
		return "add";
	}
	
	public String update() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpFeedBackAction.class.getName() + ".update";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "update";
		}
		empfeedback.setStatus(1);
		empfeedbackService.update(empfeedback);
		result = ControllerResult.setSuccessResult("修改成功");
		return "update";
	}
	
	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpFeedBackAction.class.getName() + ".frost";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "status";
		}
		EmpFeedBack empfeedback = empfeedbackService.queryById(EmpFeedBack.class, id);
		if (empfeedback.getStatus() == 1) {
			result = ControllerResult.setSuccessResult("冻结成功");
			empfeedbackService.updateStatus("EmpFeedBack", "feedBackId", 0, id);
		} else {
			result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
		}
		return "status";
	}
	
	public String activation() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpFeedBackAction.class.getName() + ".activation";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "status";
		}
		EmpFeedBack empfeedback = empfeedbackService.queryById(EmpFeedBack.class, id);
		if (empfeedback.getStatus() == 0) {
			result = ControllerResult.setSuccessResult("激活成功");
			empfeedbackService.updateStatus("EmpFeedBack", "feedBackId", 1, id);
		} else {
			result = ControllerResult.setFailResult("已经激活了，不能再激活");
		}
		return "status";
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	
	public String pagerByEmpName() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpFeedBackAction.class.getName() + ".pagerByEmpName";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "pagerByEmpName";
		}
		Pager4EasyUI<EmpFeedBack> pager = new Pager4EasyUI<EmpFeedBack>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = empfeedbackService.queryByEmpName(pager, empName, "EmpFeedBack");
		rows = pager.getRows();
		total = pager.getTotal();
		if (rows != null) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
		return "pagerByEmpName";
	}
	
	public String pagerByDay() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpFeedBackAction.class.getName() + ".pagerByDay";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "pagerByDay";
		}
		Pager4EasyUI<EmpFeedBack> pager = new Pager4EasyUI<EmpFeedBack>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = empfeedbackService.queryByDay(pager, startDay, endDay, "EmpFeedBack", "feedbackDay");
		rows = pager.getRows();
		total = pager.getTotal();
		if (rows != null) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
		return "pagerByDay";
	}
}
