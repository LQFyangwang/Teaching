package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.Emp;
import com.ht.bean.Grade;
import com.ht.bean.Progress;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.ProgressService;

public class ProgressAction extends BaseAction{

	private static final long serialVersionUID = -6345469299629753278L;
	
	private ProgressService progressService;
	
	private HttpServletRequest req;
	
	private Progress progress;
	private List<Progress> rows;
	private long total;
	private ControllerResult result;
	private String id;
	private String empName;
	
	private AuthorityRoleService authorityRoleService;
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public void setProgressService(ProgressService progressService) {
		this.progressService = progressService;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public List<Progress> getRows() {
		return rows;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public long getTotal() {
		return total;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String save(){
		System.out.println("进入save");
		String roleId = WebUtil.getRoleId(req);
		String methodName = ProgressAction.class.getName() + ".save";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "save";
		}
		String id = req.getParameter("gradeid");
		Grade gr = new Grade();
		gr.setGradeId(id);
		String ids = req.getParameter("progress.emp.empId");
		Emp emp = new Emp();
		emp.setEmpId(ids);
		progress.setEmp(emp);
		progress.setGrade(gr);
		progress.setStatus(1);
		System.out.println(progress);
		progressService.save(progress);
		result = ControllerResult.setSuccessResult("添加成功");
		return "save";
	}
	
	public String update(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = ProgressAction.class.getName() + ".update";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "update";
		}
		progressService.update(progress);
		result = ControllerResult.setSuccessResult("修改成功");
		return "update";
	}

	public String pager(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = ProgressAction.class.getName() + ".pager";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "pager";
		}
		System.out.println("进入pager啊");
		Pager4EasyUI<Progress> pager = new Pager4EasyUI<Progress>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = progressService.queryByPager("Progress", pager);
		rows = pager.getRows();
		System.out.println(rows.size());
		total = pager.getTotal();
		return "pager";
	}
	
	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ProgressAction.class.getName() + ".frost";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "status";
		}
		Progress progress = progressService.queryById(Progress.class, id);
		if (progress.getStatus() == 1) {
			result = ControllerResult.setSuccessResult("冻结成功");
			progressService.updateStatus("Progress", "progressId", 0, id);
		} else {
			result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
		}
		return "status";
	}
	
	public String activation() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ProgressAction.class.getName() + ".activation";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "status";
		}
		Progress progress = progressService.queryById(Progress.class, id);
		if (progress.getStatus() == 0) {
			result = ControllerResult.setSuccessResult("激活成功");
			progressService.updateStatus("Progress", "progressId", 1, id);
		} else {
			result = ControllerResult.setFailResult("已经激活了，不能再激活");
		}
		return "status";
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public String pagerByEmpName() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ProgressAction.class.getName() + ".pagerByEmpName";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "pagerByEmpName";
		}
		Pager4EasyUI<Progress> pager = new Pager4EasyUI<Progress>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = progressService.queryByEmpName(pager, empName, "Progress");
		rows = pager.getRows();
		total = pager.getTotal();
		if (rows != null) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
		return "pagerByEmpName";
	}
	
	
}
