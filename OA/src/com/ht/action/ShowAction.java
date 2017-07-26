package com.ht.action;

import javax.servlet.http.HttpSession;

import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;

public class ShowAction extends BaseAction {

	private static final long serialVersionUID = 3580826678550039591L;

	private HttpSession session;
	private AuthorityRoleService authorityRoleService;
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public String empLogin() {
		session = req.getSession();
		if (session.getAttribute("emp") != null) {
			return "home";
		}
		return "empLogin";
	}

	public String talk() {
		return "talk";
	}

	public String exit() {
		session = req.getSession();
		if (session.getAttribute("emp") != null) {
			session.removeAttribute("emp");
		}
		return "empLogin";
	}

	public String refresh() {
		session = req.getSession();
		if (session.getAttribute("emp") != null) {
			return "refresh";
		}
		return "empLogin";
	}

	public String showDepts() {
		return "depts";
	}

	public String grade() {
		return "grade";
	}

	public String room() {
		return "room";
	}
	
	public String showCourse(){
		return "course";
	}
	
	public String Meetting(){
		return "meetting";
	}
	
	public String EmpFeedBack(){
		return "empfeedback";
	}
	
	public String Progress(){
		return "progress";
	}
	
	public String intentionStu() {
		return "intention";
	}
	
	public String reserveStu() {
		return "reserve";
	}
	
	public String officialStu() {
		return "official";
	}

	public String empPage() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".empPage";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "emp";
		}
		return ERROR;
	}
	
	public String noticeType() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".noticeType";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "noticeType";
		}
		return ERROR;
	}
	
	public String feedback() {
		return "feedback";
	}
	
	public String job() {
		return "job";
	}
	
	public String showEmpLeaves() {
		return "empLeaves";
	}
	
	public String checking() {
		return "checking";
	}
	
	public String stuLeave() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".stuLeave";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "stuLeave";
		}
		return ERROR;
	}

	public String notice(){
		return "notice";
	}
	
	public String showDutys() {
		return "dutys";
	}
	
	public String empCheckingInfo(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".empCheckingInfo";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "empCheckingInfo";
		}
		return ERROR;
	}
	
	
	public String score() {
		return "score";
	}
	public String goodsType(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".goodsType";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "goodsTypePage";
		}
		return ERROR;
	}
	
	public String role(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".role";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "role";
		}
		return ERROR;
	}
	
	public String updatePwd() {
		return "updatePwd";
	}
	
	public String goods(){
		return "goods";
	}
	
	public String goodsApp(){
		return "goodsApp";
	}
	
	public String showChecks() {
		return "checks";
	}
	
	public String showGoodsUse(){
		return "use";
	}
	
	public String showGoodsReturn(){
		return "goodsReturn";
	}
	public String summary() {
		return "summary";
	}

	public String paytype() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".paytype";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "paytype";
		}
		return ERROR;
	}
	
	public String incometype() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".incometype";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "incometype";
		}
		return ERROR;
	}
	
	public String pay() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".pay";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "pay";
		}
		return ERROR;
	}
	
	public String income() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".income";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "income";
		}
		return ERROR;
	}
	
	public String salaryinfo() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = ShowAction.class.getName() + ".salaryinfo";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			return "salaryinfo";
		}
		return ERROR;
	}
	
	public String showReports() {
		return "reports";
	}
	
	public String todayDuty() {
		return "todayDuty";
	}
	
	public String EmpInformation(){
		return "information";
	}
	
	public String empSalaryInfo() {
		return "empSalaryInfo";
	}
	
	public String stuLogin() {
		session = req.getSession();
		if (session.getAttribute("stu") != null) {
			return "stuHome";
		}
		return "stuLogin";
	}
	
	public String stuExit() {
		session = req.getSession();
		if (session.getAttribute("stu") != null) {
			session.removeAttribute("stu");;
		}
		return "stuLogin";
	}

	public String stuRefresh() {
		session = req.getSession();
		if (session.getAttribute("stu") != null) {
			return "stuRefresh";
		}
		return "stuLogin";
	}
	
	//Ñ§Éú
	public String student_score(){
		return "student_score";
	}
	public String student_leave(){
		return "student_leave";
	}
	public String student_feedback(){
		return "student_feedback";
	}
	public String student_job(){
		return "student_job";
	}
	public String student_talk(){
		return "student_talk";
	}
	public String student_checking(){
		return "student_checking";
	}
	public String student_summary(){
		return "student_summary";
	}
	
	public String stuUpdatePwd() {
		return "stuUpdatePwd";
	}
	
	public String empCheckings() {
		return "empCheckings";
	}
}
