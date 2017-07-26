package com.ht.action;

import java.util.List;

import com.ht.bean.Grade;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.GradeService;

public class GradeAction extends BaseAction {

	private static final long serialVersionUID = 7376172995568166768L;

	private GradeService gradeService;
	private AuthorityRoleService authorityRoleService;
	private Grade grade;
	private List<Grade> rows;
	private long total;
	private ControllerResult result;
	private String id;
	
	private String name;
	private String value;
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public List<Grade> getRows() {
		return rows;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String pager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = GradeAction.class.getName() + ".pager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Grade> pager = new Pager4EasyUI<Grade>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			pager = gradeService.queryByPager("Grade", pager);
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "pager";
	}
	
	public String save() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = GradeAction.class.getName() + ".save";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			String name = req.getParameter("grade.name");
			String empId1 = req.getParameter("grade.empId1");
			String empId2 = req.getParameter("grade.empId2");
			String empId3 = req.getParameter("grade.empId3");
			String des = req.getParameter("grade.des");
			String quantityStr = req.getParameter("grade.quantity");
			grade.setName(name);
			grade.setEmpId1(empId1);
			grade.setEmpId2(empId2);
			grade.setEmpId3(empId3);
			grade.setDes(des);
			try {
				int quantity = Integer.valueOf(quantityStr);
				grade.setQuantity(quantity);
			}catch (NumberFormatException e) {
			}
			grade.setStatus(1);
			gradeService.save(grade);
			result = ControllerResult.setSuccessResult("添加成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "save";
	}
	
	public String update() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = GradeAction.class.getName() + ".update";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			String gradeId = req.getParameter("grade.gradeId");
			String name = req.getParameter("grade.name");
			String empId1 = req.getParameter("grade.empId1");
			String empId2 = req.getParameter("grade.empId2");
			String empId3 = req.getParameter("grade.empId3");
			String des = req.getParameter("grade.des");
			String quantityStr = req.getParameter("grade.quantity");
			grade.setGradeId(gradeId);
			grade.setName(name);
			grade.setEmpId1(empId1);
			grade.setEmpId2(empId2);
			grade.setEmpId3(empId3);
			grade.setDes(des);
			try {
				int quantity = Integer.valueOf(quantityStr);
				grade.setQuantity(quantity);
			}catch (NumberFormatException e) {
			}
			grade.setStatus(1);
			gradeService.update(grade);
			result = ControllerResult.setSuccessResult("更新成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "update";
	}
	
	public String id() {
		grade = gradeService.queryById(Grade.class, id);
		return "id";
	}
	
	public String all() {
		gradeService.queryAll("Grade");
		return "all";
	}
	
	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = GradeAction.class.getName() + ".frost";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Grade grade = gradeService.queryById(Grade.class, id);
			if (grade.getStatus() == 1) {
				result = ControllerResult.setSuccessResult("冻结成功");
				gradeService.updateStatus("Grade", "gradeId", 0, id);
			} else {
				result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "status";
	}
	
	public String activation() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = GradeAction.class.getName() + ".activation";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Grade grade = gradeService.queryById(Grade.class, id);
			if (grade.getStatus() == 0) {
				result = ControllerResult.setSuccessResult("激活成功");
				gradeService.updateStatus("Grade", "gradeId", 1, id);
			} else {
				result = ControllerResult.setFailResult("已经激活了，不能再激活");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "status";
	}
	
	/**
	 * 模糊搜索
	 * @return
	 */
	public String fuzzySearch() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = GradeAction.class.getName() + ".fuzzySearch";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Grade> pager = new Pager4EasyUI<Grade>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			if (name.equals("gradeName")) {
				pager = gradeService.queryByGradeName(pager, value);
			}
			rows = pager.getRows();
			total = pager.getTotal();
			if (rows != null) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("没有记录");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "fuzzySearch";
	}
	
}
