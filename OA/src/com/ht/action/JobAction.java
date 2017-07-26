package com.ht.action;

import java.util.List;

import com.ht.bean.Job;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.JobService;

/**
 * 学生就业的Action
 * @author Administrator
 *
 */
public class JobAction extends BaseAction {

	private static final long serialVersionUID = 1678637851026890172L;
	
	private JobService jobService;
	private AuthorityRoleService authorityRoleService;
	
	private Job job;
	private List<Job> rows;
	private long total;
	private ControllerResult result;
	
	private String id;
	private String stuName;
	private String startDay;
	private String endDay;

	public void setJob(Job job) {
		this.job = job;
	}
	
	public Job getJob() {
		return job;
	}

	public List<Job> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
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

	public String pager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = JobAction.class.getName() + ".pager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager("pager");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "pager";
	}
	
	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = JobAction.class.getName() + ".frost";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Job job = jobService.queryById(Job.class, id);
			if (job.getStatus() == 1) {
				result = ControllerResult.setSuccessResult("冻结成功");
				jobService.updateStatus("Job", "jobId", 0, id);
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
		String methodName = JobAction.class.getName() + ".activation";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Job job = jobService.queryById(Job.class, id);
			if (job.getStatus() == 0) {
				result = ControllerResult.setSuccessResult("激活成功");
				jobService.updateStatus("Job", "jobId", 1, id);
			} else {
				result = ControllerResult.setFailResult("已经激活了，不能再激活");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "status";
	}
	
	public String add() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = JobAction.class.getName() + ".add";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			job.setStatus(1);
			jobService.save(job);
			result = ControllerResult.setSuccessResult("添加成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "add";
	}
	
	public String edit() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = JobAction.class.getName() + ".edit";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			jobService.update(job);
			result = ControllerResult.setSuccessResult("修改成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "edit";
	}
	
	public String pagerByStuName() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = JobAction.class.getName() + ".pagerByStuName";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager("paerByStuName");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "pagerByStuName";
	}
	
	public String pagerByDay() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = JobAction.class.getName() + ".pagerByDay";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager("pagerByDay");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "pagerByDay";
	}
	
	private void setPager(String method) {
		String stuId = WebUtil.getStuId(req);
		Pager4EasyUI<Job> pager = new Pager4EasyUI<Job>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if (method.equals("pager")) {
			pager = jobService.queryByPager("Job", pager);
			rows = pager.getRows();
		} else {
			if (method.equals("pagerByStuName")) {
				pager = jobService.queryByStuName(pager, stuName, "Job");
			} else if (method.equals("pagerByDay")) {
				pager = jobService.queryByDay(pager, startDay, endDay, "Job", "hireDay");
			} else if (method.equals("pagerByStuId")) {
				pager = jobService.queryByStuId(pager, stuId);
			} else if (method.equals("pagerByDay1")) {
				pager = jobService.queryByDayAndStuId(pager, stuId, startDay, endDay);
			}
			rows = pager.getRows();
			if (rows != null && rows.size() > 0) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("没有记录");
			}
		}
		total = pager.getTotal();
		
	}
	
	public String pagerByStuId() {
		setPager("pagerByStuId");
		return "pagerByStuId";
	}
	
	public String pagerByDay1() {
		setPager("pagerByDay1");
		return "pagerByDay1";
	}


}
