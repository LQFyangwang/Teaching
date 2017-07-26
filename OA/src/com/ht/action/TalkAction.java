package com.ht.action;

import java.util.ArrayList;
import java.util.List;

import com.ht.bean.Emp;
import com.ht.bean.Stu;
import com.ht.bean.Talk;
import com.ht.bean.info.TalkInfo;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.TalkInfoService;
import com.ht.service.TalkService;

/**
 * 学生谈心管理
 * @author Administrator
 *
 */
public class TalkAction extends BaseAction {

	private static final long serialVersionUID = 7188278820097137232L;
	
	private TalkInfo talkInfo;
	private TalkService talkService;
	private AuthorityRoleService authorityRoleService;
	private TalkInfoService tis;
	private List<TalkInfo> rows;
	private long total;
	private ControllerResult result;
	
	private String id;
	private String startDay; // 开始时间
	private String endDay; // 结束时间
	private String name; // 判断是什么名称
	private String value; // 模糊搜索的值

	public TalkInfo getTalkInfo() {
		return talkInfo;
	}

	public void setTalkInfo(TalkInfo talkInfo) {
		this.talkInfo = talkInfo;
	}

	public List<TalkInfo> getRows() {
		return rows;
	}

	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public long getTotal() {
		return total;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setTalkService(TalkService talkService) {
		this.talkService = talkService;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String pager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = TalkAction.class.getName() + ".pager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager1("pager");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "pager";
	}
	
	public void setTis(TalkInfoService tis) {
		this.tis = tis;
	}

	/**
	 * 设置Talk的公共方法
	 * @param talk
	 * @return
	 */
	private Talk setTalk(Talk talk) {
		Emp e = WebUtil.getEmp(req);
		Emp emp = new Emp();
		emp.setEmpId(e.getEmpId());
		talk.setEmp(emp);
		Stu stu = new Stu();
		stu.setStuId(talkInfo.getStuId());
		talk.setStu(stu);
		talk.setDes(talkInfo.getDes());
		talk.setTalkDay(talkInfo.getTalkDay());
		talk.setStatus(1);
		return talk;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String add() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = TalkAction.class.getName() + ".add";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Talk talk = new Talk();
			talkService.save(setTalk(talk)); // 保存
			result = ControllerResult.setSuccessResult("添加成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "add";
	}
	
	public String edit() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = TalkAction.class.getName() + ".edit";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Talk talk = new Talk();
			talk.setTalkId(talkInfo.getTalkId());
			setTalk(talk);
			talkService.update(talk); // 更新
			result = ControllerResult.setSuccessResult("修改成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "edit";
	}
	
	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = TalkAction.class.getName() + ".frost";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Talk talk = talkService.queryById(Talk.class, id);
			if (talk.getStatus() == 1) {
				result = ControllerResult.setSuccessResult("冻结成功");
				talkService.updateStatus("Talk", "talkId", 0, id);
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
		String methodName = TalkAction.class.getName() + ".activation";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Talk talk = talkService.queryById(Talk.class, id);
			if (talk.getStatus() == 0) {
				result = ControllerResult.setSuccessResult("激活成功");
				talkService.updateStatus("Talk", "talkId", 1, id);
			} else {
				result = ControllerResult.setFailResult("已经激活了，不能再激活");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "status";
	}
	
	/**
	 * 封装TalkInfo对象
	 * @param pager
	 * @param talks
	 */
	private void setTalkInfo(Pager4EasyUI<Talk> pager, List<Talk> talks) {
		if (talks != null && talks.size() > 0) {
			rows = new ArrayList<TalkInfo>();
			for (Talk talk : talks) {
				TalkInfo ti = new TalkInfo();
				ti.setTalkId(talk.getTalkId());
				ti.setTalkDay(talk.getTalkDay());
				ti.setDes(talk.getDes());
				ti.setStatus(talk.getStatus());
				ti.setEmpId(talk.getEmp().getEmpId());
				ti.setEmpName(talk.getEmp().getName());
				ti.setStuId(talk.getStu().getStuId());
				ti.setStuName(talk.getStu().getName());
				rows.add(ti);
			}
			total = pager.getTotal();
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
	}
	
	/**
	 * 根据姓名模糊搜索
	 * @return
	 */
	public String pagerByName() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = TalkAction.class.getName() + ".pagerByName";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager("pagerByName");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "pagerByName";
	}
	
	/**
	 * 根据时间段模糊搜索
	 * @return
	 */
	public String pagerByDay() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = TalkAction.class.getName() + ".pagerByDay";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager("pagerByDay");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "pagerByDay";
	}
	
	private void setPager(String method) {
		Pager4EasyUI<Talk> pager = new Pager4EasyUI<Talk>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if (method.equals("pagerByName")) {
			if (name.equals("stuName")) {
				pager = talkService.queryByStuName(pager, value, "Talk");
			} else if (name.equals("empName")) {
				pager = talkService.queryByEmpName(pager, value, "Talk");
			}
		} else if (method.equals("pagerByDay")) {
			pager = talkService.queryByDay(pager, startDay, endDay, "Talk", "talkDay");
		}
		List<Talk> talks = pager.getRows();
		setTalkInfo(pager, talks);
	}
	
	private void setPager1(String method) {
		String stuId = WebUtil.getStuId(req);
		Pager4EasyUI<TalkInfo> pager = new Pager4EasyUI<TalkInfo>();;
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if (method.equals("pager")) {
			pager = tis.queryByPager(pager);
		} else if (method.equals("pagerByStuId")) {
			pager = tis.queryByStuId(pager, stuId);
		} else if (method.equals("pagerByDay1")) {
			pager = tis.queryByDayAndStuId(pager, stuId, startDay, endDay);
		}
		
		rows = pager.getRows();
		if (rows != null && rows.size() > 0) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
		total = pager.getTotal();
	}
	
	public String pagerByStuId() {
		setPager1("pagerByStuId");
		return "pagerByStuId";
	}
	
	public String pagerByDay1() {
		setPager1("pagerByDay1");
		return "pagerByDay1";
	}
	
	
}
