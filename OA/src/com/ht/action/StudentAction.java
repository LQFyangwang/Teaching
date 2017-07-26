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
import com.ht.service.NoticeService;
import com.ht.service.StuService;

public class StudentAction extends BaseAction {

	private static final long serialVersionUID = -6301118296204552775L;
	
	private Stu stu;
	private HttpSession session;
	private StuService stuService;
	private DutyService dutyService;
	private NoticeService noticeService;
	private ControllerResult result;
	
	private String ordPwd;
	private String newPwd;

	public Stu getStu() {
		return stu;
	}

	public void setStu(Stu stu) {
		this.stu = stu;
	}
	
	public void setStuService(StuService stuService) {
		this.stuService = stuService;
	}

	public void setDutyService(DutyService dutyService) {
		this.dutyService = dutyService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setOrdPwd(String ordPwd) {
		this.ordPwd = ordPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public ControllerResult getResult() {
		return result;
	}

	public String login() {
		session = req.getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		if (emp != null) {
			session.removeAttribute("emp");
		}
		String pwd = stu.getPwd();
		stu.setPwd(EncryptUtil.encrypt(pwd));
		stu = stuService.queryByLogin(stu);
		if (stu != null) {
			List<TodayDutyInfo> tdis = dutyService.queryByToday();
			List<Notice> notices = noticeService.queryNewNotice();
			session.setAttribute("stu", stu);
			session.setAttribute("notices", notices);
			session.setAttribute("tdis", tdis);
			return "login";
		}
		return "login_fail";
	}
	
	public String updatePwd() {
		session = req.getSession();
		Stu stu = (Stu) session.getAttribute("stu");
		if (stu != null) {
			if (EncryptUtil.encrypt(ordPwd).equals(stu.getPwd())) {
				stu.setPwd(EncryptUtil.encrypt(newPwd));
				stuService.update(stu);
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
