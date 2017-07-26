package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.Duty;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.DutyService;

public class DutyAction extends BaseAction {

	private static final long serialVersionUID = 8134256082774843578L;

	private DutyService dutyService;
	private Duty duty;
	private HttpServletRequest req;
	private List<Duty> rows;
	private long total;
	private ControllerResult result;
	private AuthorityRoleService authorityRoleService;
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}
	
	public Duty getDuty() {
		return duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	public List<Duty> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setDutyService(DutyService dutyService) {
		this.dutyService = dutyService;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public String pager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = DutyAction.class.getName() + ".pager";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "pager";
		}
		Pager4EasyUI<Duty> pager = new Pager4EasyUI<Duty>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = dutyService.queryByPager2(pager);
		rows = pager.getRows();
		total = pager.getRows().size();
		return "pager";
	}
	
	public String update() {
		/*duty.setEmpId1(duty.getEmp1().getEmpId());
		duty.setEmpId2(duty.getEmp2().getEmpId());
		duty.setEmpId3(duty.getEmp3().getEmpId());
		duty.setEmpId4(duty.getEmp4().getEmpId());
		duty.setEmpId5(duty.getEmp5().getEmpId());
		duty.setEmpId6(duty.getEmp6().getEmpId());
		duty.setEmpId7(duty.getEmp7().getEmpId());
		duty.setEmpId8(duty.getEmp8().getEmpId());*/
		String roleId = WebUtil.getRoleId(req);
		String methodName = DutyAction.class.getName() + ".update";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "update";
		}
		if (duty.getEmp1().getEmpId().equals("")) {
			duty.setEmp1(null);
			duty.setAdd1(null);
		} 
		if (duty.getEmp2().getEmpId().equals("")) {
			duty.setEmp2(null);
			duty.setAdd2(null);
		}
		if (duty.getEmp3().getEmpId().equals("")) {
			duty.setEmp3(null);
			duty.setAdd3(null);
		}
		if (duty.getEmp4().getEmpId().equals("")) {
			duty.setEmp4(null);
			duty.setAdd4(null);
		}
		if (duty.getEmp5().getEmpId().equals("")) {
			duty.setEmp5(null);
			duty.setAdd5(null);
		}
		if (duty.getEmp6().getEmpId().equals("")) {
			duty.setEmp6(null);
			duty.setAdd6(null);
		}
		if (duty.getEmp7().getEmpId().equals("")) {
			duty.setEmp7(null);
			duty.setAdd7(null);
		}
		if (duty.getEmp8().getEmpId().equals("")) {
			duty.setEmp8(null);
			duty.setAdd8(null);
		}
		// invokeSet(duty, 8);
		dutyService.update(duty);
		result = ControllerResult.setSuccessResult("修改成功");
		return "update";
	}
	
	/*private void invokeSet(Duty duty, int index) {
		Method[] methods = duty.getClass().getMethods();
		for (int i = 0; i <= methods.length; i++) {
			String name = methods[i].getName();
			if (name.startsWith("getEmp")) {
				try {
					Emp emp = (Emp) methods[i].invoke(duty, new Object[]{});
					if (emp.getEmpId().equals("")) {
						int idx = Integer.valueOf(name.substring("getEmp".length()));
						Method method = duty.getClass().getMethod("setEmp" + idx, Emp.class);
						Method method1 = duty.getClass().getMethod("setAdd" + idx, String.class);
						if (method != null) {
							method.invoke(duty, new Object[]{null});
						}
						if (method1 != null) {
							method1.invoke(duty, new Object[]{null});
						}
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}*/
}
