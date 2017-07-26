package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.Meetting;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.MeettingService;

public class MeettingAction extends BaseAction{

	private static final long serialVersionUID = -8537896104939230374L;

	private MeettingService meettingService;
	
	private HttpServletRequest req;
	
	private Meetting meetting;
	private List<Meetting> rows;
	private long total;
	private ControllerResult result;
	private String startDay;
	private String endDay;
	private String value;
	private String id;
	
	private AuthorityRoleService authorityRoleService;
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public void setMeettingService(MeettingService meettingService) {
		this.meettingService = meettingService;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public Meetting getMeetting() {
		return meetting;
	}

	public void setMeetting(Meetting meetting) {
		this.meetting = meetting;
	}

	public List<Meetting> getRows() {
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
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String pager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = MeettingAction.class.getName() + ".pager";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "pager";
		}
		Pager4EasyUI<Meetting> pager = new Pager4EasyUI<Meetting>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = meettingService.queryByPager("Meetting", pager);
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}
	
	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = MeettingAction.class.getName() + ".frost";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "status";
		}
		Meetting meetting = meettingService.queryById(Meetting.class, id);
		if (meetting.getStatus() == 1) {
			result = ControllerResult.setSuccessResult("冻结成功");
			meettingService.updateStatus("Meetting", "meettingId", 0, id);
		} else {
			result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
		}
		return "status";
	}
	
	public String activation() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = MeettingAction.class.getName() + ".activation";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "status";
		}
		Meetting meetting = meettingService.queryById(Meetting.class, id);
		if (meetting.getStatus() == 0) {
			result = ControllerResult.setSuccessResult("激活成功");
			meettingService.updateStatus("Meetting", "meettingId", 1, id);
		} else {
			result = ControllerResult.setFailResult("已经激活了，不能再激活");
		}
		return "status";
	}
	
	public String add() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = MeettingAction.class.getName() + ".add";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "add";
		}
		meetting.setStatus(1);
		meettingService.save(meetting);
		result = ControllerResult.setSuccessResult("添加成功");
		return "add";
	}
	
	public String update() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = MeettingAction.class.getName() + ".update";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "update";
		}
		meettingService.update(meetting);
		result = ControllerResult.setSuccessResult("修改成功");
		return "update";
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	
	public String pagerByDay() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = MeettingAction.class.getName() + ".pagerByDay";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "pagerByDay";
		}
		Pager4EasyUI<Meetting> pager = new Pager4EasyUI<Meetting>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = meettingService.queryByDay(pager, startDay, endDay, "Meetting", "meettingDay");
		rows = pager.getRows();
		if (rows != null) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
		return "pagerByDay";
	}

	public String pagerByName(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = MeettingAction.class.getName() + ".pagerByName";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "pagerByName";
		}
		Pager4EasyUI<Meetting> pager = new Pager4EasyUI<Meetting>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = meettingService.queryByEmpName(pager, value, "Meetting");
		rows = pager.getRows();
		if (rows != null) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
		return "pagerByName";
	}
}
