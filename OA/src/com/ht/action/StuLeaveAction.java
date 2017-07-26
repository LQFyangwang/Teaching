package com.ht.action;

import java.util.List;

import com.ht.bean.Stu;
import com.ht.bean.StuLeave;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.StuLeaveService;

/**
 * 学生请假的Action
 * @author Administrator
 *
 */
public class StuLeaveAction extends BaseAction {

	private static final long serialVersionUID = 5836873784346808166L;
	
	private StuLeaveService stuLeaveService;
	private AuthorityRoleService authorityRoleService;
	private List<StuLeave> rows;
	private long total;
	private ControllerResult result;
	
	private String id;
	private String stuName;
	private String startDay;
	private String endDay;
	
	private StuLeave stuLeave;
	private String stuLeaveId;
	private String firstLevel;
	private String secondLevel;

	public void setStuLeaveService(StuLeaveService stuLeaveService) {
		this.stuLeaveService = stuLeaveService;
	}

	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public List<StuLeave> getRows() {
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
	
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public StuLeave getStuLeave() {
		return stuLeave;
	}

	public void setStuLeave(StuLeave stuLeave) {
		this.stuLeave = stuLeave;
	}

	public void setStuLeaveId(String stuLeaveId) {
		this.stuLeaveId = stuLeaveId;
	}

	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}

	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}

	public String pager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuLeaveAction.class.getName() + ".pager";
		Pager4EasyUI<StuLeave> pager = new Pager4EasyUI<StuLeave>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			pager = stuLeaveService.queryByPager("StuLeave", pager);
			rows = pager.getRows();
			total = pager.getTotal();
			result = ControllerResult.setSuccessResult("查询成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "pager";
	}
	
	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuLeaveAction.class.getName() + ".frost";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			StuLeave stuLeave = stuLeaveService.queryById(StuLeave.class, id);
			if (stuLeave.getStatus() == 1) {
				result = ControllerResult.setSuccessResult("冻结成功");
				stuLeaveService.updateStatus("StuLeave", "leaveId", 0, id);
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
		String methodName = StuLeaveAction.class.getName() + ".activation";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			StuLeave stuLeave = stuLeaveService.queryById(StuLeave.class, id);
			if (stuLeave.getStatus() == 0) {
				result = ControllerResult.setSuccessResult("激活成功");
				stuLeaveService.updateStatus("StuLeave", "leaveId", 1, id);
			} else {
				result = ControllerResult.setFailResult("已经激活了，不能再激活");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "status";
	}
	
	public String pagerByStuName() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuLeaveAction.class.getName() + ".pagerByStuName";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager("pagerByStuName");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "pagerByStuName";
	}
	
	public String pagerByLeaveDay() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuLeaveAction.class.getName() + ".pagerByLeaveDay";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager("pagerByLeaveDay");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "pagerByLeaveDay";
	}
	
	private void setPager(String method) {
		String stuId = WebUtil.getStuId(req);
		Pager4EasyUI<StuLeave> pager = new Pager4EasyUI<StuLeave>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if (method.equals("pagerByStuName")) {
			pager = stuLeaveService.queryByStuName(pager, stuName, "StuLeave");
		} else if (method.equals("pagerByLeaveDay")) {
			pager = stuLeaveService.queryByDay(pager, startDay, endDay, "StuLeave", "leaveDay");
		} else if (method.equals("pagerByStuId")) {
			pager = stuLeaveService.queryByStuId(pager, stuId);
		} else if (method.equals("pagerByDay1")) {
			pager = stuLeaveService.queryByDayAndStuId(pager, stuId, startDay, endDay);
		}
		rows = pager.getRows();
		total = pager.getTotal();
		if (rows != null && rows.size() > 0) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
	}
	
	public String pagerByStuId() {
		setPager("pagerByStuId");
		return "pagerByStuId";
	}
	
	public String pagerByDay1() {
		setPager("pagerByDay1");
		return "pagerByDay1";
	}
	
	public String add() {
		Stu stu = new Stu();
		stu.setStuId(WebUtil.getStuId(req));
		stuLeave.setStu(stu);
		stuLeave.setStatus(1);
		stuLeaveService.save(stuLeave);
		result = ControllerResult.setSuccessResult("添加申请成功,请等待任课老师和班主任审核");
		return "add";
	}
	
	public String edit() {
		Stu stu = new Stu();
		stu.setStuId(WebUtil.getStuId(req));
		stuLeave.setStu(stu);
		stuLeave.setStatus(1);
		stuLeaveService.update(stuLeave);
		result = ControllerResult.setSuccessResult("修改成功");
		return "edit";
	}
	
	public String allow() {
		String role = WebUtil.getRole(req);
		StuLeave sl = new StuLeave();
		sl.setLeaveId(stuLeaveId);
		if (role.equals("班主任")) {
			stuLeaveService.updateLeaveStatus(sl, "secondlevel");
			sl.setFirstLevel(Integer.valueOf(firstLevel));
			if (sl.getFirstLevel() == 1) {
				stuLeaveService.updateLeaveStatus(sl, "pass");
			}
		} else if (role.equals("任课老师")) {
			stuLeaveService.updateLeaveStatus(sl, "firstlevel");
			sl.setSecondLevel(Integer.valueOf(secondLevel));
			if (sl.getSecondLevel() == 1) {
				stuLeaveService.updateLeaveStatus(sl, "pass");
			}
		}
		result = ControllerResult.setSuccessResult("允许通过");
		return "allow";
	}

}
