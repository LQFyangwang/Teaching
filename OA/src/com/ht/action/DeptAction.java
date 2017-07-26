package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.Dept;
import com.ht.bean.info.DeptInfo;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.DeptInfoService;
import com.ht.service.DeptService;

public class DeptAction extends BaseAction {

	private static final long serialVersionUID = 1559309570942742761L;

	private DeptService deptService;
	private AuthorityRoleService authorityRoleService;
	private DeptInfoService deptInfoService;
	private HttpServletRequest req;
	private Dept dept;
	private DeptInfo deptInfo;
	private List<DeptInfo> rows;
	private long total;
	private ControllerResult result;
	private String id;

	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public void setDeptInfoService(DeptInfoService deptInfoService) {
		this.deptInfoService = deptInfoService;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public DeptInfo getDeptInfo() {
		return deptInfo;
	}

	public void setDeptInfo(DeptInfo deptInfo) {
		this.deptInfo = deptInfo;
	}

	public List<DeptInfo> getRows() {
		return rows;
	}

	public ControllerResult getResult() {
		return result;
	}

	public long getTotal() {
		return total;
	}

	public String queryPage() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = DeptAction.class.getName() + ".queryPage";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "pager";
		}
		Pager4EasyUI<DeptInfo> pager = new Pager4EasyUI<DeptInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = deptInfoService.queryByPager(pager);
		rows = pager.getRows();
		total = pager.getRows().size();
		return "pager";
	}

	public String addDept() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = DeptAction.class.getName() + ".addDept";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "add";
		}
		deptService.save(dept);
		result = ControllerResult.setSuccessResult("添加成功");
		return "add";
	}

	public String update() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = DeptAction.class.getName() + ".update";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "update";
		}
		dept.setStatus(1);
		deptService.update(dept);
		result = ControllerResult.setSuccessResult("修改成功");
		return "update";
	}

	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = DeptAction.class.getName() + ".frost";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "status";
		}
		Dept dep = deptService.queryById(Dept.class, id);
		if (dep.getStatus() == 1) {
			result = ControllerResult.setSuccessResult("冻结成功");
			deptService.updateStatus("Dept", "depId", 0, id);
		} else {
			result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
		}
		return "status";
	}

	public String activation() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = DeptAction.class.getName() + ".activation";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "status";
		}
		Dept dep = deptService.queryById(Dept.class, id);
		if (dep.getStatus() == 0) {
			result = ControllerResult.setSuccessResult("激活成功");
			deptService.updateStatus("Dept", "depId", 1, id);
		} else {
			result = ControllerResult.setFailResult("已经激活了，不能再激活");
		}
		return "status";
	}

}
