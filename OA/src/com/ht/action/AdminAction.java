package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ht.bean.Emp;
import com.ht.bean.Notice;
import com.ht.bean.Stu;
import com.ht.bean.info.TodayDutyInfo;
import com.ht.common.EncryptUtil;
import com.ht.common.bean.ControllerResult;
import com.ht.service.DutyService;
import com.ht.service.EmpService;
import com.ht.service.NoticeService;

public class AdminAction extends BaseAction {

	private static final long serialVersionUID = 7188278820097137232L;
	
	private HttpSession session;
	
	private Emp emp;
	private EmpService empService;
	private DutyService dutyService;
	private NoticeService noticeService;
	private ControllerResult result;
	private String ordPwd;
	private String newPwd;
	
	private List<Notice> notices;
	
	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public void setDutyService(DutyService dutyService) {
		this.dutyService = dutyService;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setOrdPwd(String ordPwd) {
		this.ordPwd = ordPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public String login() {
		session = req.getSession();
		Stu stu = (Stu) session.getAttribute("stu");
		if (stu != null) {
			session.removeAttribute("stu");
		}
		String pwd = emp.getPwd();
		emp.setPwd(EncryptUtil.encrypt(pwd));
		emp = empService.queryByLogin(emp);
		if (emp != null) {
			List<TodayDutyInfo> tdis = dutyService.queryByToday();
			notices = noticeService.queryNewNotice();
			session.setAttribute("emp", emp);
			session.setAttribute("notices", notices);
			session.setAttribute("tdis", tdis);
			return "login";
		}
		return "login_fail";
	}
	
	public String updatePwd() {
		session = req.getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		if (emp != null) {
			if (EncryptUtil.encrypt(ordPwd).equals(emp.getPwd())) {
				emp.setPwd(EncryptUtil.encrypt(newPwd));
				empService.update(emp);
				result = ControllerResult.setSuccessResult("修改成功，下次登入请使用新密码");
			} else {
				result = ControllerResult.setFailResult("修改失败,旧密码不一致");
			}
		} else {
			result = ControllerResult.setFailResult("当前用户已失效，请重新重新登入");
		}
		return "updatePwd";
	}
	
	public String home() {
		return SUCCESS;
	}

}
